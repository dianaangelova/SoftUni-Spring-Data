import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

public class E10_IncreaseSalaries {

    private static final String GET_EMPLOYEES =
            "FROM Employee e WHERE e.department.name = 'Engineering' or e.department.name = 'Tool Design' or e.department.name ='Marketing' or e.department.name ='Information Services'";

    public static void main(String[] args) {
        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        List<Employee> employeesResultList = myEntityManager.createQuery(GET_EMPLOYEES, Employee.class).getResultList();

        for (Employee employee : employeesResultList) {

            BigDecimal newSalary = employee.getSalary().multiply(BigDecimal.valueOf(0.12)).add(employee.getSalary());

            employee.setSalary(newSalary);

            myEntityManager.persist(employee);
        }

        myEntityManager.getTransaction().commit();

        Format decimalFormat = new DecimalFormat("0.##");

        for (Employee employee : employeesResultList) {

            System.out.println(employee.getFirstName() +" "+ employee.getLastName() +" ($" + decimalFormat.format(employee.getSalary()) + ")");
        }

        myEntityManager.close();
    }
}
