import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class E07_AddressesWithEmployeeCount {

    private static final String GET_ADDRESSES =
            "FROM Address a ORDER BY a.employees.size DESC";

    public static void main(String[] args) {

        EntityManager myEntityManager = Utils.createMyEntityManager();

        myEntityManager.getTransaction().begin();

        List<Address> resultList = myEntityManager.createQuery(GET_ADDRESSES, Address.class)
                                                  .setMaxResults(10)
                                                  .getResultList();


        for (Address address : resultList) {
            if (!(address.getTown() == null)) {
                System.out.println(address.getText() + ", " + address.getTown().getName() + " - " + address.getEmployees().size()+" employees");
            }
        }
        myEntityManager.getTransaction().commit();
        myEntityManager.close();
    }
}