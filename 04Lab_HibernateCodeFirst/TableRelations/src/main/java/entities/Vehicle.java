package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="vehicles")
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Basic
    @Column(insertable = false, updatable = false)
    protected String type;

    @Column
    protected String model;

    @Column
    protected BigDecimal price;

    @Column(name= "fuel_type")
    protected String fuelType;

    protected Vehicle() {
    }

    protected Vehicle(String type){
        this();
        this.type=type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
