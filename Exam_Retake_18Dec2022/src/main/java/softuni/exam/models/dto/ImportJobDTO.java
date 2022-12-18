package softuni.exam.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportJobDTO {

    @XmlElement(name = "jobTitle")
    @Size(min = 2, max = 40)
    @NotNull
    private String title;

    @XmlElement(name = "hoursAWeek")
    @Min(10)
    @NotNull
    private double hoursAWeek;

    @XmlElement(name = "salary")
    @Min(300)
    @NotNull
    private double salary;

    @XmlElement(name = "description")
    @Size(min = 5)
    @NotNull
    private String description;

    @XmlElement(name = "companyId")
    @NotNull
    private long companyId;

    public ImportJobDTO() {
    }

    public String getTitle() {
        return title;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public double getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public long getCompanyId() {
        return companyId;
    }
}
