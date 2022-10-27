import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E08_IncreaseMinionsAge {

    private static final String UPDATE_MINION_NAMES_AGE_SQL = "update minions set name = LOWER(name), age = age+1 where id in (?)";

    private static final String SELECT_MINIONS_INFO_SQL = "select name, age from minions";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

       ArrayList integerArrayList = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        Integer[] integers = new Integer[integerArrayList.size()];

        for (int i = 0; i < integerArrayList.size(); i++)
            integers[i] = (Integer) integerArrayList.get(i);

        Connection connection = Utils.getSQLConnection();

        for (int i: integers) {
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(UPDATE_MINION_NAMES_AGE_SQL);
            preparedStatementUpdate.setInt(1, i);
            preparedStatementUpdate.executeUpdate();
        }


        PreparedStatement preparedStatementSelectMinions = connection.prepareStatement(SELECT_MINIONS_INFO_SQL);
        ResultSet resultMinionsInfo = preparedStatementSelectMinions.executeQuery();

        while (resultMinionsInfo.next()) {
            String dbMinionName = resultMinionsInfo.getString("name");
            int dbMinionAge = resultMinionsInfo.getInt("age");
            System.out.printf("%s %d%n", dbMinionName, dbMinionAge);
        }

        connection.close();
    }
}
