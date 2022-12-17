package softuni.exam.models.entity;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "day_of_week", nullable = false)
    private DayOfWeekEnum dayOfWeek;

    @Column(name = "max_temperature", nullable = false)
    private float maxTemperature;

    @Column(name = "min_temperature", nullable = false)
    private float minTemperature;

    @Column
    private LocalTime sunrise;

    @Column
    private LocalTime sunset;

    @ManyToOne
    private City city;

    public Forecast() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DayOfWeekEnum getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeekEnum dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {

        return String.format("City: %s:%n" +
                "-min temperature: %.2f%n" +
                "--max temperature: %.2f%n" +
                "---sunrise: %s%n" +
                "----sunset: %s",
                getCity().getCityName(),
                getMinTemperature(),
                getMaxTemperature(),
                getSunrise().toString(),
                getSunset().toString()
                );
    }
}
