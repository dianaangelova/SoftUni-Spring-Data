import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class E08_GetEmployeeWithProject {

    private static final String GET_EMPLOYEE =
            "FROM Employee e WHERE e.id = :id";

    public static void main(String[] args) {

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        int inputEmployeeID = Integer.parseInt(scanner.nextLine());

        Employee resultEmployee = myEntityManager.createQuery(GET_EMPLOYEE, Employee.class)
                .setParameter("id", inputEmployeeID).getSingleResult();

        System.out.println(resultEmployee.getFirstName() + " " + resultEmployee.getLastName() + " - " + resultEmployee.getJobTitle());

        List<Project> resultProjects = resultEmployee.getProjects().stream().collect(Collectors.toList());

        Collections.sort(resultProjects, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        resultProjects.stream().forEach(project -> System.out.println("\t\t" + project.getName()));

        myEntityManager.close();
    }
}
