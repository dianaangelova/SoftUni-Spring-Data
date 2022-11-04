import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class E13_RemoveTowns {

    private static final String GET_TOWN = "FROM Town t WHERE t.name = '%s'";
    private static final String GET_ADDRESS = "FROM Address a WHERE a.town.name = '%s'";
    private static final String GET_EMPLOYEE = "FROM Employee e WHERE e.address.town.name = '%s'";

    public static void main(String[] args) {

        String inputTown = new Scanner(System.in).nextLine();

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        String queryTown = String.format(GET_TOWN, inputTown);

        String queryAddress = String.format(GET_ADDRESS, inputTown);

        String queryEmployee = String.format(GET_EMPLOYEE, inputTown);

        Town resultTown = myEntityManager.createQuery(queryTown, Town.class)
                .getSingleResult();

        List<Address> resultAddressList = myEntityManager.createQuery(queryAddress, Address.class)
                .getResultList();

        myEntityManager.createQuery(queryEmployee, Employee.class)
                .getResultList()
                .forEach(employee -> employee.setAddress(null));

        int count = resultAddressList.size();

        for (Address address : resultAddressList) {
            myEntityManager.remove(address);
        }

        myEntityManager.remove(resultTown);

        myEntityManager.getTransaction().commit();

        if (count == 1) {
            System.out.printf("%d address in %s deleted", count, inputTown);
        } else System.out.printf("%d addresses in %s deleted", count, inputTown);


        myEntityManager.close();
    }

}