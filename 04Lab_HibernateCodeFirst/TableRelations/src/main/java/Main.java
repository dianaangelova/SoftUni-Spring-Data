import entities.Bike;
import entities.Car;
import entities.Plane;
import entities.Vehicle;
import hasEntities.Article;
import hasEntities.PlateNumber;
import hasEntities.Truck;
import hasEntities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("relations");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        // Inheritance

        Vehicle car = new Car("Ford Focus", "Petrol", 5);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Airbus", "Petrol", 50);

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);

        Car fromDB = entityManager.find(Car.class, 1L);
        System.out.println(fromDB.getModel() + " " + fromDB.getSeats());

        // One To One
        PlateNumber plateNumber = new PlateNumber("CA123NH");
        Truck truck = new Truck("BMW", plateNumber);
        Truck truck1 = new Truck("TRUCK MODEL", plateNumber);
        entityManager.persist(plateNumber);
        entityManager.persist(truck);
        entityManager.persist(truck1);

        //Many To One

        User user = new User("Petrov");
        Article article = new Article("textArticle", user);
        Article article1 = new Article("textArticle1", user);
        Article article2 = new Article("textArticle2", user);

        entityManager.persist(user);
        entityManager.persist(article);
        entityManager.persist(article1);
        entityManager.persist(article2);


        Article article3 = new Article("textArticle3 new text", user);
        User tosho = new User("Tosho");

        article3.setAuthor(tosho);
        tosho.addArticle(article3);

        entityManager.persist(tosho);



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
