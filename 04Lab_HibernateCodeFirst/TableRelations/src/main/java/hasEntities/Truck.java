package hasEntities;

import javax.persistence.*;

@Entity
@Table(name="has_trucks")
public class Truck {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String truckModel;

    @OneToOne
    @JoinColumn(name="plate_number_id", referencedColumnName = "id")
    //двупосочна връзка
    private PlateNumber plateNumber;

    public Truck() {
    }

    public Truck(String truckModel, PlateNumber plateNumber) {
        this.truckModel = truckModel;
        this.plateNumber = plateNumber;
    }
}
