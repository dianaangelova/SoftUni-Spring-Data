import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E09_IncreaseAgeStoredProcedure {

    private static final String CALL_STORED_PROCEDURE = "call usp_get_older(?)";
    private static final String SELECT_MINION = "SELECT name, age FROM minions where id=?";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Minion ID: ");

        int inputID = Integer.parseInt(scanner.nextLine());

        Connection connection = Utils.getSQLConnection();

        PreparedStatement preparedStatementCallProcedure = connection.prepareStatement(CALL_STORED_PROCEDURE);
        preparedStatementCallProcedure.setInt(1, inputID);

       preparedStatementCallProcedure.executeQuery();

        PreparedStatement preparedStatementSelect = connection.prepareStatement(SELECT_MINION);
        preparedStatementSelect.setInt(1, inputID);

        ResultSet resultMinion = preparedStatementSelect.executeQuery();

        while (resultMinion.next()) {
            String dbMinionName = resultMinion.getString("name");
            int dbMinionAge = resultMinion.getInt("age");
            System.out.printf("%s %d", dbMinionName, dbMinionAge);
        }

        connection.close();
    }
}
