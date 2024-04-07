package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private int id;

    private LocalDate fecha_cita;

    private LocalTime hora_cita;

    private String motivo;

    private int id_paciente;

    private int id_medico;



    public Cita(){

    }


    public Cita(int id, LocalDate fecha_cita, LocalTime hora_cita, String motivo, int id_paciente, int id_medico) {
        this.id = id;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(LocalDate fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public LocalTime getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(LocalTime hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fecha_cita=" + fecha_cita +
                ", hora_cita=" + hora_cita +
                ", motivo='" + motivo + '\'' +
                ", id_paciente=" + id_paciente +
                ", id_medico=" + id_medico +
                '}';
    }
}
