package entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table(name = "cars")
public class Car extends PassengerVehicle {

    private static final String VEHICLE_TYPE = "CAR";



    public Car() {
        super(VEHICLE_TYPE);
    }

    public Car(String model, String fuelType, int seats) {
        this();
        this.model= model;
        this.fuelType=fuelType;
        this.seats=seats;
    }


}
