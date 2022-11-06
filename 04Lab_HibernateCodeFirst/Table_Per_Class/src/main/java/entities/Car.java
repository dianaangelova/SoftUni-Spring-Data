package entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    private static final String VEHICLE_TYPE = "CAR";

    @Basic
    private int seats;

    public Car() {
        super(VEHICLE_TYPE);
    }

    public Car(String model, String fuelType, int seats) {
        this();
        this.model=getModel();
        this.fuelType=getFuelType();
        this.seats=seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
