package softuni.exam.models.dto;

import softuni.exam.models.entity.DayOfWeekEnum;
import softuni.exam.util.LocalTimeAdapter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportForecastDTO {

    @NotNull
    @XmlElement(name = "day_of_week")
    private DayOfWeekEnum dayOfWeek;

    @NotNull
    @XmlElement (name = "max_temperature" )
    @Min(-20)
    @Max(60)
    private float maxTemperature;

    @NotNull
    @XmlElement(name = "min_temperature")
    @Min(-50)
    @Max(40)
    private float minTemperature;

    @NotNull
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlElement
    private LocalTime sunrise;

    @NotNull
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlElement
    private LocalTime sunset;

    @XmlElement
    private long city;

    public long getCity() {
        return city;
    }

    public DayOfWeekEnum getDayOfWeek() {
        return dayOfWeek;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }
}
