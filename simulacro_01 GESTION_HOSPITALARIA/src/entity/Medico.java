package entity;

public class Medico {

    private int id;

    private String nombre;

    private String apellidos;

    private int id_especialidad;

    public Medico(int id, String nombre, String apellidos, int id_especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_especialidad = id_especialidad;
    }

    public Medico(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }


    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id_especialidad=" + id_especialidad +
                '}';
    }
}
