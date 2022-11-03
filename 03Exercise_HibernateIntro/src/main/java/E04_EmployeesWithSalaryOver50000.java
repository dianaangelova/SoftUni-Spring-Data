import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E04_EmployeesWithSalaryOver50000 {

    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE =
            "select e.firstName from Employee e where e.salary > %d";
    private static final int SALARY_CRITERIA = 50000;

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(DATABASE_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = String.format(GET_EMPLOYEE, SALARY_CRITERIA);

        List<String> namesResultList = entityManager.createQuery(query, String.class).getResultList();

        for (String s : namesResultList) {
            System.out.println(s);
        }

        entityManager.close();
    }
}
