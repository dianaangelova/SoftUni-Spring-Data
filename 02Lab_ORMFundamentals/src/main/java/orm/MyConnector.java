package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {

    private static Connection connection;

    private static String PARAMETERS_FOR_DATABASE = "?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC";

    private static final String connectionString = "jdbc:mysql://localhost:3306/%s%s";

    public static void createConnection (String user, String password, String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        String formattedJDBC = String.format(connectionString, dbName, PARAMETERS_FOR_DATABASE);

        connection = DriverManager.getConnection(formattedJDBC, properties);

    }

    public static Connection getConnection(){
        return connection;
    }
}
