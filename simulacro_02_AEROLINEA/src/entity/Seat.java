package entity;

public class Seat {

    private Integer id;

    private String seatCode;

    private Boolean availability;

    private Integer idFlight;


    public Seat() {};

    public Seat(Integer id, String seatCode, Boolean availability, Integer idFlight) {

        this.id = id;
        this.seatCode = seatCode;
        this.availability = availability;
        this.idFlight = idFlight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Integer id_flight) {
        this.idFlight = id_flight;
    }


    @Override
    public String toString() {
        return "Seat\n" +
                "id: " + id +
                ", seatCode: " + seatCode  +
                ", availability: " + availability +
                ", id flight: " + idFlight +
                "\n--------------------------------------------------------\n\n";
    }
}
