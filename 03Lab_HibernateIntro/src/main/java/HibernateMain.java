import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {
        System.out.println("Works");

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

// First example //
//        Student student = new Student();
//        student.setName("Pesho");
//        session.persist(student);
//
//        Student fromDBStudent =session.get(Student.class, 1);
//        System.out.println(fromDBStudent.getId() + " " + fromDBStudent.getName());

        List<Student> studentsList = session.createQuery("FROM Student where name='Pesho'", Student.class).list();

        for (Student student : studentsList) {
            System.out.println(student.getId() + " " + student.getName());
        }
        session.getTransaction().commit();
        session.close();
    }
}
