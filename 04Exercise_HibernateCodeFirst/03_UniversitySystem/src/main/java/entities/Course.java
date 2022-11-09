package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column
    private int credits;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @ManyToOne
    private Teacher teacher;

    public Course() {
        this.students = new HashSet<>();
    }
}

