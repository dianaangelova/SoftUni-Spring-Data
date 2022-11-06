package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("relations");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Vehicle car = new Car("Ford Focus", "Petrol", 5);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Airbus", "Petrol", 50);

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
