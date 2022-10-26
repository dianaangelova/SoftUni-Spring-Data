import java.sql.*;
import java.util.Scanner;

public class E04_AddMinion {
    private static final String GET_VILLAIN_NAME_SQL = "SELECT villains.id AS villain_name" +
            " FROM villains" +
            " where villains.name = ?";

    private static final String INSERT_TOWN_SQL = "insert into towns(name) values(?)";

    private static final String INSERT_VILLAIN_SQL = "insert into villains (name, evilness_factor) values(?, \"evil\")";

    private static final String GET_MINION_TOWN_SQL = "select towns.id as town_id from towns where towns.name=?";

    private static final String INSERT_MINION_SQL = "insert into minions (name, age, town_id) values (?, ?, ?)";

    private static final String INSERT_MINION_VILLAIN_SQL = "insert into minions_villains (minion_id, villain_id) values (?, ?)";

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

        ResultSet resultMinionTownID = preparedStatementMinionTown.executeQuery();

        int generatedTownIDKey = 0;

        if (!resultMinionTownID.next()) {

            PreparedStatement preparedStatementInsertTown = connection.prepareStatement(INSERT_TOWN_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatementInsertTown.setString(1, minionTownInput);

            preparedStatementInsertTown.execute();

            ResultSet resultSetTownID = preparedStatementInsertTown.getGeneratedKeys();

            if (resultSetTownID.next()) {
                generatedTownIDKey = resultSetTownID.getInt(1);
            }
            if (generatedTownIDKey != 0) {
                System.out.printf("Town %s was added to the database.%n", minionTownInput);
            }
        }

        PreparedStatement preparedStatementVillain = connection.prepareStatement(GET_VILLAIN_NAME_SQL);
        preparedStatementVillain.setString(1, villainInput);

        ResultSet resultVillain = preparedStatementVillain.executeQuery();

        int generatedVillainIDKey = 0;

        if (!resultVillain.next()) {
            PreparedStatement preparedStatementInsertVillain = connection.prepareStatement(INSERT_VILLAIN_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatementInsertVillain.setString(1, villainInput);

            preparedStatementInsertVillain.execute();

            ResultSet resultSetVillainID = preparedStatementInsertVillain.getGeneratedKeys();

            if (resultSetVillainID.next()) {
                generatedVillainIDKey = resultSetVillainID.getInt(1);
            }

            if (generatedVillainIDKey != 0) {
                System.out.printf("Villain %s was added to the database.%n", villainInput);
            }
        }

        PreparedStatement preparedStatementInsertMinion = connection.prepareStatement(INSERT_MINION_SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatementInsertMinion.setString(1, minionNameInput);
        preparedStatementInsertMinion.setInt(2, minionAgeInput);
        preparedStatementInsertMinion.setInt(3, generatedTownIDKey);

        preparedStatementInsertMinion.executeUpdate();

        ResultSet resultMinionID = preparedStatementInsertMinion.getGeneratedKeys();

        int generatedMinionIDKey = 0;

        if (resultMinionID.next()) {
            generatedMinionIDKey = resultMinionID.getInt(1);
        }


        PreparedStatement preparedStatementInsertMinVillaRelationship = connection.prepareStatement(INSERT_MINION_VILLAIN_SQL);
        preparedStatementInsertMinVillaRelationship.setInt(1, generatedMinionIDKey);
        preparedStatementInsertMinVillaRelationship.setInt(2, generatedVillainIDKey);

        preparedStatementInsertMinVillaRelationship.execute();

        System.out.printf("Successfully added %s to be minion of %s.", minionNameInput, villainInput);

    }
}
