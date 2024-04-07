package entity;

public class Passenger {

    private Integer id;

    private String namePassenger;

    private String lastname;

    private String identityCard;

    public Passenger() {
    }

    public Passenger(Integer id, String namePassenger, String lastname, String identityCard) {
        this.id = id;
        this.namePassenger = namePassenger;
        this.lastname = lastname;
        this.identityCard = identityCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePassenger() {
        return namePassenger;
    }

    public void setNamePassenger(String namePassenger) {
        this.namePassenger = namePassenger;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    @Override
    public String toString() {
        return "Passenger\n" +
                "id:" + id +
                ", name passenger:" + namePassenger +
                ", lastname:" + lastname +
                ", identity card:" + identityCard +
                "\n--------------------------------------------\n";
    }
}
