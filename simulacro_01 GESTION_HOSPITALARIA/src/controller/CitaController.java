package controller;

import entity.Cita;
import entity.Especialidad;
import entity.Paciente;
import model.CitaModel;
import model.EspecialidadModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class CitaController {
    CitaModel citaModel;

    public CitaController(CitaModel citaModel){
        this.citaModel = citaModel;
    }

    public static void guardarCita(){

        Cita cita = new Cita();

        LocalDate fecha_cita = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingresa fecha de la cita: "));
        LocalTime hora_cita = LocalTime.parse(JOptionPane.showInputDialog(null, "Ingresa hora: "));
        String motivo = JOptionPane.showInputDialog(null, "Ingresa motivo: ");

        cita.setFecha_cita(fecha_cita);
        cita.setHora_cita(hora_cita);
        cita.setMotivo(motivo);

        cita = this.citaModel.guardarCita(cita);

        JOptionPane.showMessageDialog(null, "Cita guardado: \n" + cita.toString());
    }

    public static void consultar(){

        List<Cita> listCita;

        listCita = this.citaModel.consultar();

        JOptionPane.showMessageDialog(null, "Lista de citas: \n" + listCita);

    }

    public static void consultarPorFiltro(){
        String[] options = {"ID" ,"Fecha cita", "Hora cita" };

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID de la cita, fecha cita, hora cita)");

        List<Cita> listCita = this.citaModel.findByFilter(selectedFilter, valueFilter);
    }

    public static void actualizarCita(){
        JOptionPane.showMessageDialog(null, "Estas son las citas: " + citaModel.consultar());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id: "));

        LocalDate fechaActualizado = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingresa el nueva fecha: "));
        LocalTime horaActualizada = LocalTime.parse(JOptionPane.showInputDialog(null, "Ingresa la nueva hora: "));
        String motivo = JOptionPane.showInputDialog(null, "Ingresa la nueva hora: ");


        Cita cita = new Cita();

        cita.setFecha_cita(fechaActualizado);
        cita.setHora_cita(horaActualizada);
        cita.setMotivo(motivo);
        cita.setId(id);

        this.citaModel.actualizarCita(cita);

        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
    }

    public static void eliminarCita(){
        JOptionPane.showMessageDialog(null, "Estas son las citas: " + citaModel.consultar());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id de la cita a eliminar"));

        this.citaModel.eliminarCita(id);
    }
}
