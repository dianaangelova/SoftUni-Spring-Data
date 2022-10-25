import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E03_GetMinionNames {
    private static final String GET_VILLAIN_NAME_SQL = "SELECT villains.name AS villain_name" +
            " FROM villains" +
            " where villains.id = ?";

    private static final String GET_MINION_NAMES_SQL = "SELECT minions.name AS minion_name, minions.age AS minion_age" +
            " FROM minions JOIN minions_villains" +
            " ON minions.id = minions_villains.minion_id" +
            " WHERE minions_villains.villain_id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        System.out.print("Enter villain ID: ");
        int villainID = Integer.parseInt(scanner.nextLine());

        PreparedStatement preparedStatementVillain = connection.prepareStatement(GET_VILLAIN_NAME_SQL);

        preparedStatementVillain.setInt(1, villainID);


        ResultSet resultVillain = preparedStatementVillain.executeQuery();

        int counter = 1;

        if (resultVillain.next()) {
            String db_villain_name = resultVillain.getString("villain_name");
            System.out.printf("Villain: %s%n", db_villain_name);
        } else {
            System.out.printf("No villain with ID %d exists in the database.", villainID);
        }

        PreparedStatement preparedStatementMinions = connection.prepareStatement(GET_MINION_NAMES_SQL);

        preparedStatementMinions.setInt(1, villainID);

        ResultSet resultMinions = preparedStatementMinions.executeQuery();

        while (resultMinions.next()) {
            String db_minion_name = resultMinions.getString("minion_name");
            int db_minion_age = resultMinions.getInt("minion_age");
            System.out.printf("%d. %s %d%n", counter, db_minion_name, db_minion_age);
            counter++;
        }
        connection.close();
    }
}
