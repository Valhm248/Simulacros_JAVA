package entity;


public class Reservation {

    private Integer id;

    private Integer idPassenger;

    private Integer idFlight;

    private String reservationDate;
    private String reservationTime;
    private Integer idSeat;
    private String seat;


    public Reservation() {    }

    public Reservation(Integer id, Integer idPassenger, Integer idFlight, String reservationDate, String reservationTime, Integer idSeat, String seat) {
        this.id = id;
        this.idPassenger = idPassenger;
        this.idFlight = idFlight;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.idSeat = idSeat;
        this.seat = seat;
    }

    public Integer getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Integer idSeat) {
        this.idSeat = idSeat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(Integer idPassenger) {
        this.idPassenger = idPassenger;
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Integer idFlight) {
        this.idFlight = idFlight;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Reservation\n" +
                "id: " + id +
                ", id passenger: " + idPassenger +
                ", id flight: " + idFlight +
                ", Reservation Date: " + reservationDate +
                ", seat: " + seat +
                "\n--------------------------------------------\n";
    }
}
