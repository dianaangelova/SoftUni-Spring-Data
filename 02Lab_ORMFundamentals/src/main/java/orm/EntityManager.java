package orm;

import orm.annotaions.Column;
import orm.annotaions.Entity;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException {
        String tableName = this.getTableName(entity);
        String fieldList = this.getDBFieldsWithoutID(entity);
        String valueList = this.getValuesWithoutID(entity);

        String sql = String.format("insert into %s (%s) values (%s)", tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
    }

    private String getValuesWithoutID(E entity) {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        
    }

    private String getTableName(E entity) {
        Entity annotation = entity.getClass().getAnnotation(Entity.class);

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
    public Iterable<E> find() {
        return null;
    }

    @Override
    public Iterable<E> find(String where) {
        return null;
    }

    @Override
    public Iterable<E> findFirst() {
        return null;
    }

    @Override
    public Iterable<E> findFirst(String where) {
        return null;
    }
}
