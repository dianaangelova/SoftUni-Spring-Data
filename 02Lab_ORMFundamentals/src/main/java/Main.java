
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        MyConnector.createConnection("root", "konche", "soft_uni");
        Connection connection = MyConnector.getConnection();

        EntityManager<User> userManager = new EntityManager<>(connection);
//
//        User user = new User("pencho", 15, LocalDate.now());
//
//        userManager.persist(user);
//
        User pencho = userManager.findFirst(User.class);

        System.out.println(pencho.getId() + " " + pencho.getUsername());

        User pencho1 = userManager.findFirst(User.class, "user_name = 'pencho1'");

        System.out.println(pencho1.getId() + " " + pencho1.getUsername());

        Iterable<User> userList = userManager.find(User.class, "age>'5' and registration_date>'2022-01-01'");

        for (User u : userList) {
            System.out.println("Found: " + u.getId() + " " + u.getUsername());
        }
    }
}
