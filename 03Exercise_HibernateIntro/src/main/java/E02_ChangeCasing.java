import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E02_ChangeCasing {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_ALL_TOWNS = "FROM Town";

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(DATABASE_NAME);

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> townsResultList = entityManager.createQuery(GET_ALL_TOWNS).getResultList();

        for (Town town : townsResultList) {

            if (town.getName().length() > 5) {
                entityManager.detach(town);
            } else {
                town.setName(town.getName().toUpperCase());
                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
