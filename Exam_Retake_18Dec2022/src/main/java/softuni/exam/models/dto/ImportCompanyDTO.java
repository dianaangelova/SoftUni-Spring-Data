package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompanyDTO {

    @XmlElement(name = "companyName")
    @Size(min = 2, max = 40)
    @NotNull
    private String name;

    @XmlElement(name="dateEstablished")
    @Size(min = 2)
    @NotNull
    private String dateEstablished;

    @XmlElement()
    @Size(min = 2, max = 30)
    @NotNull
    private String website;

    @XmlElement(name = "countryId")
    @NotNull
    private long countryId;

    public ImportCompanyDTO(){}

    public String getName() {
        return name;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public String getWebsite() {
        return website;
    }

    public long getCountryId() {
        return countryId;
    }
}
