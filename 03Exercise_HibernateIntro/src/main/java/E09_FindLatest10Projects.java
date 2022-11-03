import entities.Address;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.List;

public class E09_FindLatest10Projects {
    private static final String GET_PROJECTS =
            "FROM Project p order by p.name";

    public static void main(String[] args) {

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        myEntityManager.createQuery(GET_PROJECTS, Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        myEntityManager.close();
    }
}
