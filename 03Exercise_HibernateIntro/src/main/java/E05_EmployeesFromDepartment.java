import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Stream;

public class E05_EmployeesFromDepartment {

    private static final String DATABASE_NAME = "soft_uni";
    private static final String DEPARTMENT_CRITERIA = "Research and Development";
    private static final String GET_EMPLOYEE =
            "Select e " +
                    "from Employee e " +
//                    "JOIN Department d " +
//                    "on e.department =  d.id " +
//                    "and d.name = '%s' " +
                    "where e.department.name = '%s' " +
                    "order by e.salary, " +
                    "e.id";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(DATABASE_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = String.format(GET_EMPLOYEE, DEPARTMENT_CRITERIA);

        List<Employee> employeeResultList = entityManager.createQuery(query, Employee.class).getResultList();

        for (Employee employee : employeeResultList) {
            System.out.println(employee.getFirstName()
                    + " "
                    + employee.getLastName()
                    + " from "
                    + employee.getDepartment().getName()
                    + " - $"
                    + employee.getSalary());
        }

        entityManager.close();

    }
}
