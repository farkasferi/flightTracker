package hu.myprojects.flighttracker.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "departure_time", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureTime;

    public Flight() {}

    public Flight(String code, LocalDateTime departureTime) {
        this.code = code;
        this.departureTime = departureTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", departureTime=" + departureTime +
                '}';
    }
}
