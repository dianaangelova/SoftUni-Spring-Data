package orm;

import orm.annotaions.Column;
import orm.annotaions.Entity;
import orm.annotaions.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldsWithoutID(entity);
        String valueList = this.getValuesWithoutID(entity);

        String sql = String.format("insert into %s (%s) values (%s)", tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
    }

    private String getValuesWithoutID(E entity) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();

        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Column.class) == null) {
                continue;
            }
            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add("\"" + value.toString() + "\"");
        }
        return String.join(",", result);
    }

    private String getTableName(Class<?> clazz) {
        Entity annotation = clazz.getAnnotation(Entity.class);

        if (annotation == null) {
            throw new ORMException("Provided class does not have entity annotation.");
        }

        return annotation.name();
    }

    private String getDBFieldsWithoutID(E entity) {
        return Arrays.stream(
                        entity
                                .getClass()
                                .getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(","));
    }

    @Override
    public E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(entityType, " ");
    }

    @Override
    public E findFirst(Class<E> entityType, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = "select * from %s %s limit 1";
        String sqlFormatted = String.format(sql, tableName, where.equals(" ") ? " " : "where " + where);

        ResultSet resultSet = this.connection.prepareStatement(sqlFormatted).executeQuery();

        return this.createEntity(entityType, resultSet);

    }

    private E createEntity(Class<E> entityType, ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!resultSet.next()) {
            return null;
        }

        E entity = entityType.getDeclaredConstructor().newInstance();

        Field[] declaredFields = entityType.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(Column.class) && !declaredField.isAnnotationPresent(Id.class)) {
                continue;
            }

            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            String fieldName = columnAnnotation == null ? declaredField.getName() : columnAnnotation.name();

            String value = resultSet.getString(fieldName);
            entity = this.fillData(entity, declaredField, value);
        }
        return entity;
    }

    private E fillData(E entity, Field field, String value) throws IllegalAccessException {
        field.setAccessible(true);

        if (field.getType() == long.class || field.getType() == Long.class) {
            field.setLong(entity, Long.parseLong(value));
        } else if (field.getType() == int.class || field.getType() == Integer.class) {
            field.setInt(entity, Integer.parseInt(value));
        } else if (field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(value));
        } else if (field.getType() == String.class) {
            field.set(entity, value);
        } else throw new ORMException("Unsupported field type " + field.getType());

        return entity;
    }

    @Override
    public Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(entityType, null);
    }

    @Override
    public Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = "select * from %s %s";
        String sqlFormatted = String.format(sql, tableName, where.equals(" ") ? " " : "where " + where);

        ResultSet resultSet = this.connection.prepareStatement(sqlFormatted).executeQuery();

        List<E> result = new ArrayList<>();

        E entity = this.createEntity(entityType, resultSet);

        while (entity != null) {
            result.add(entity);
            entity = this.createEntity(entityType, resultSet);
        }
        return result;
    }

}
