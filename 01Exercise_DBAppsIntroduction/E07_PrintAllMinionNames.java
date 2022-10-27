import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class E07_PrintAllMinionNames {

    private static final String GET_MINION_NAMES_SQL = "SELECT name as minionName FROM minions";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        PreparedStatement preparedStatementMinions = connection.prepareStatement(GET_MINION_NAMES_SQL);

        ResultSet resultMinions = preparedStatementMinions.executeQuery();

        ArrayDeque<String> stackMinions = new ArrayDeque<>();

        ArrayDeque<String> queueMinions = new ArrayDeque<>();

        while (resultMinions.next()) {
            String dbMinionName = resultMinions.getString("minionName");

                stackMinions.push(dbMinionName);
                queueMinions.offer(dbMinionName);
        }

        for (int i=0; i<stackMinions.size();i++) {
            System.out.println(queueMinions.poll());
            System.out.println(stackMinions.pop());
        }

        connection.close();
    }
}
