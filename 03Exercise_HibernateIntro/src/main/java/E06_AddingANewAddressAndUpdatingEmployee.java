import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class E06_AddingANewAddressAndUpdatingEmployee {

    private static final String GET_EMPLOYEE =
            "FROM Employee e where e.lastName  = :ln";

    private static final String NEW_ADDRESS = "Vitoshka 15";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String inputLastName = scanner.nextLine();

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        List<Employee> employeeResultList = myEntityManager.createQuery(GET_EMPLOYEE, Employee.class)
                .setParameter("ln", inputLastName)
                .getResultList();

        Address address = new Address();
        address.setText(NEW_ADDRESS);
        myEntityManager.persist(address);

        for (Employee employee : employeeResultList) {
            employee.setAddress(address);
            myEntityManager.persist(employee);
        }

        myEntityManager.getTransaction().commit();
        myEntityManager.close();

    }
}
