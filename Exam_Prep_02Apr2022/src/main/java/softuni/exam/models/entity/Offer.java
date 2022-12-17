package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private float price;

    @Column(name = "published_on")
    private LocalDate publishedOn;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Agent agent;

    public Offer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return String.format("Agent %s %s with offer №%d:\n" +
                        "\t-Apartment area: %.2f\n" +
                        "\t--Town: %s\n" +
                        "\t---Price: %.2f$",
                agent.getFirstName(), agent.getLastName(), getId(),
                getApartment().getArea(),
                getApartment().getTown().getTownName(),
                getPrice());
    }
}
