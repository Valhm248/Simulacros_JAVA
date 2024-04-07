package entity;

public class Airplane {

    private Integer id;

    private String model;

    private Integer capacity;

    public Airplane() {};

    public Airplane(Integer id, String model, Integer capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane\n" +
                "id:" + id +
                ", model:" + model +
                ", capacity:" + capacity +
                "\n--------------------------------------------\n";
    }
}
