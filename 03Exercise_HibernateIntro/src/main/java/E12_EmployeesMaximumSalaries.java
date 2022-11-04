import javax.persistence.EntityManager;



public class E12_EmployeesMaximumSalaries {
    private static final String GET_DEPARTMENTS =
"Select e.department.name, max(e.salary) from Employee e GROUP BY e.department.name HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

    public static void main(String[] args) {

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        myEntityManager.createQuery(GET_DEPARTMENTS, Object[].class).getResultList()
                .forEach(o -> System.out.println(o[0] + " " + o[1]));

        myEntityManager.close();


    }

}