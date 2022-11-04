import entities.Employee;

import javax.persistence.EntityManager;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;
import java.util.Scanner;

public class E11_FindEmployees {
    private static final String GET_EMPLOYEES =
            "FROM Employee e WHERE e.firstName like '%s'";

    public static void main(String[] args) {

        Format decimalFormat = new DecimalFormat("0.##");

        String inputFirstName = new Scanner(System.in).nextLine();

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        String query = String.format(GET_EMPLOYEES, inputFirstName+"%");

        List<Employee> resultList = myEntityManager.createQuery(query, Employee.class)
                .getResultList();

        for (Employee employee : resultList) {

            System.out.println(employee.getFirstName() + " " +
                    employee.getLastName() + " - " + employee.getJobTitle() + " - ($"
                    + decimalFormat.format(employee.getSalary()) + ")");
        }


        myEntityManager.close();

    }

}