package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {

    private Integer id;

    private String destination;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private Integer idAirplane;

    private Airplane airplane;

    public Flight() {
    }

    public Flight(Integer id, String destination, LocalDate departureDate, LocalTime departureTime, Integer idAirplane, Airplane airplane) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.idAirplane = idAirplane;
        this.airplane = airplane;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(Integer idAirplane) {
        this.idAirplane = idAirplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Flight\n" +
                "id:" + id +
                ", destination: " + destination +
                ", departure date: " + departureDate +
                ", departure time: " + departureTime +
                ", id Airplane: " + idAirplane +
                "\n--------------------------------------------\n";
    }
}
