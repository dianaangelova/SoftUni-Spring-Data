package hasEntities;

import javax.persistence.*;

@Entity
@Table(name="has_plateNumber")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String plateNumber;

    @OneToOne(targetEntity = Truck.class, mappedBy = "plateNumber")
    private Truck truck;

    public PlateNumber() {
    }

    public PlateNumber(String plateNumber) {
        this.plateNumber=plateNumber;
    }

}
