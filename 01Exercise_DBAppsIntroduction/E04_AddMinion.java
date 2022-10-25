import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E04_AddMinion {
    private static final String GET_VILLAIN_NAME_SQL = "SELECT villains.id AS villain_name" +
            " FROM villains" +
            " where villains.name = ?";

    private static final String INSERT_TOWN_SQL = "insert into towns(name) values(?)";

    private static final String INSERT_VILLAIN_SQL = "insert into villains (name, evilness_factor) values(?, \"evil\")";

    private static final String GET_MINION_TOWN_SQL = "select towns.id as minion_town from towns where towns.name=?";

    private static final String INSERT_MINION_SQL = "insert into minions (name, age, town_id) values (?, ?, ?)";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        System.out.print("Minion: ");
        String minionInput = scanner.nextLine();

        System.out.print("Villain: ");
        String villainInput = scanner.nextLine();

        String[] listMinionInput = minionInput.split(" ");
        String minionNameInput = listMinionInput[0];
        int minionAgeInput = Integer.parseInt(listMinionInput[1]);
        String minionTownInput = listMinionInput[2];

        PreparedStatement preparedStatementMinionTown = connection.prepareStatement(GET_MINION_TOWN_SQL);
        preparedStatementMinionTown.setString(1, minionTownInput);

        ResultSet resultMinionTown = preparedStatementMinionTown.executeQuery();

        if (!resultMinionTown.next()) {

            PreparedStatement preparedStatementInsertTown = connection.prepareStatement(INSERT_TOWN_SQL);

            preparedStatementInsertTown.setString(1, minionTownInput);

            boolean resultTown = preparedStatementInsertTown.execute();

            // if (preparedStatementInsertTown.execute()) {
                System.out.printf("Town %s was added to the database.%n", minionTownInput);
            // }
        }

        PreparedStatement preparedStatementVillain = connection.prepareStatement(GET_VILLAIN_NAME_SQL);
        preparedStatementVillain.setString(1, villainInput);

        ResultSet resultVillain = preparedStatementVillain.executeQuery();

        if (!resultVillain.next()) {
            PreparedStatement preparedStatementInsertVillain = connection.prepareStatement(INSERT_VILLAIN_SQL);

            preparedStatementInsertVillain.setString(1, minionTownInput);

            boolean resultSQLVillain = preparedStatementInsertVillain.execute();

          // if (resultSQLVillain) {
                System.out.printf("Villain %s was added to the database.%n", villainInput);
           // }
        }

        PreparedStatement preparedStatementMinion = connection.prepareStatement(INSERT_MINION_SQL);
        preparedStatementMinion.setString(1, minionNameInput);
        preparedStatementMinion.setInt(2, minionAgeInput);

        ResultSet resultMinionTownID = preparedStatementMinionTown.executeQuery();
        preparedStatementMinion.setInt(3, resultMinionTownID.getInt(1));

        preparedStatementVillain.execute();
    }
}
