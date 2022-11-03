import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public enum Utils {
    ;

    public static EntityManager createMyEntityManager() {
        return Persistence.createEntityManagerFactory("soft_uni").createEntityManager();
    }
}
