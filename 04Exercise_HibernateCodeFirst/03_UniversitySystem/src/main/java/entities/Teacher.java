package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends Person{

    @Column
    private String email;

    @Column
    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {
        super();
        courses = new ArrayList<>();
    }
}
