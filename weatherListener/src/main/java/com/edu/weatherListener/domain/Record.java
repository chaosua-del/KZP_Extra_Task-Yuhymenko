package com.edu.weatherListener.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "weatherRecords")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Date cannot be empty")
    @Length(max = 255, message = "Date too long")
    private String date;

    @NotBlank(message = "Temperature cannot be empty")
    @Length(max = 255, message = "Temperature too long")
    private String temperature;

    @NotBlank(message = "Cloudiness cannot be empty")
    @Length(max = 255, message = "Cloudiness too long")
    private String cloudiness;

    @NotBlank(message = "Wind Speed cannot be empty")
    @Length(max = 255, message = "Wind Speed too long")
    private String windSpeed;

    @NotBlank(message = "Pressure cannot be empty")
    @Length(max = 255, message = "Pressure too long")
    private String pressure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public Record() {
    }

    public Record(String date, String temperature, String windSpeed, String cloudiness, String pressure, User user) {
        this.date = date;
        this.temperature = temperature;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.author = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
