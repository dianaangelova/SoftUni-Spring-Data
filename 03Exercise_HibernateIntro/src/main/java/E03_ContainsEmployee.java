import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class E03_ContainsEmployee {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE =
            "FROM Employee e where concat(e.firstName, ' ', e.lastName)  = '%s'";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputName = scanner.nextLine();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(DATABASE_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = String.format(GET_EMPLOYEE, inputName);

        List<Employee> namesResultList = entityManager.createQuery(query, Employee.class).getResultList();

        if (namesResultList.isEmpty()) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.close();
    }
}
