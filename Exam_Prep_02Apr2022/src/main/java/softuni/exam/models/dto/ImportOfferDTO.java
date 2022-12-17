package softuni.exam.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {

    @Positive
    @NotNull
    @XmlElement
    private BigDecimal price;

    @NotNull
    @XmlElement
    private String publishedOn;

    @XmlElement(name = "agent")
    private AgentNameDTO agent;

    @XmlElement(name = "apartment")
    private ApartmentIDDTO apartment;

   public ImportOfferDTO(){}

    public BigDecimal getPrice() {
        return price;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public AgentNameDTO getAgent() {
        return agent;
    }

    public ApartmentIDDTO getApartment() {
        return apartment;
    }
}
