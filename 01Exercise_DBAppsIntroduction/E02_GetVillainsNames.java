import java.sql.*;
import java.util.Properties;

public class E02_GetVillainsNames {
    private static final String GET_VILLIAINS_SQL = "SELECT " +
            " villains.name villains_name, COUNT(DISTINCT minion_id) AS number_minions" +
            " FROM" +
            " minions_villains" +
            " JOIN" +
            " villains ON minions_villains.villain_id = villains.id" +
            " GROUP BY villains.id" +
            " HAVING number_minions > 15" +
            " ORDER BY number_minions DESC";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_VILLIAINS_SQL);

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            String dBvillains_name = result.getString("villains_name");
            int dBnumber_minions = result.getInt("number_minions");
            System.out.printf("%s %d%n", dBvillains_name, dBnumber_minions);
        }

        connection.close();
    }
}
