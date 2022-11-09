package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name= "first_name", length = 50)
    private String firstName;

    @Column(name= "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name= "notes", length = 1000)
    private String notes;

    @Column(name= "age", nullable = false)
    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String creator;

    @Column(name = "magic_wand_size")
    private short size;

    @Column(name="deposit_group", length = 20)
    private String group;

    @Column(name="deposit_start_date")
    private Date startDate;

    @Column(name="deposit_amount")
    private double amount;

    @Column(name="deposit_interest")
    private double interest;

    @Column(name="deposit_charge")
    private double charge;

    @Column(name="deposit_expiration_date")
    private Date expirationDate;

    @Column(name="is_deposit_expired")
    private boolean expired;
}
