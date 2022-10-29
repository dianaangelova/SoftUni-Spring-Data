
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyConnector.createConnection("root", "konche", "soft_uni");
        Connection connection = MyConnector.getConnection();

        EntityManager<User> userManager = new EntityManager<>(connection);

        User user = new User("pencho", 15, LocalDate.now());

        userManager.persist(user);
     }
}
