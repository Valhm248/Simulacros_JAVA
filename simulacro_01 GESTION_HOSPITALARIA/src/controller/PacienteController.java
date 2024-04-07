package controller;

import entity.Especialidad;
import entity.Paciente;
import model.EspecialidadModel;
import model.PacienteModel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class PacienteController {
    PacienteModel pacienteModel;

    public PacienteController(PacienteModel pacienteModel){
        this.pacienteModel = new PacienteModel();
    }

    public void guardarPaciente(){

        Paciente paciente = new Paciente();

        String nombre = JOptionPane.showInputDialog(null, "Ingresa nombre del paciente: ");
        String apellidos = JOptionPane.showInputDialog(null, "Ingresa apellidos: ");
        LocalDate fecha_nacimiento = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingresa fecha de nacimiento: "));
        String documento_identidad = JOptionPane.showInputDialog(null, "Ingresa documento de identidad: ");


        paciente.setNombre(nombre);
        paciente.setApellidos(apellidos);
        paciente.setFecha_nacimiento(fecha_nacimiento);
        paciente.setDocumento_identidad(documento_identidad);

        paciente = this.pacienteModel.guardarPaciente(paciente);

        JOptionPane.showMessageDialog(null, "paciente guardado: \n" + paciente.toString());
    }

    public void consultar(){

        List<Paciente> listPaciente;

        listPaciente = this.pacienteModel.consultar();

        JOptionPane.showMessageDialog(null, "Lista de pacientes: \n" + listPaciente);

    }

    public void consultarPorFiltro(){

        String[] options = {"ID" ,"Nombre", "Apellido"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID del paciente, nombre del paciente, apellido del paciente)");

        List<Paciente> listPaciente = this.pacienteModel.consultarPorFiltro(selectedFilter, valueFilter);
    }

    public void actualizarPaciente(){
        JOptionPane.showMessageDialog(null, "Estos son los pacientes: " + pacienteModel.consultar());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id: "));

        String nombreActualizado = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre: ");
        String apellidosActualizados = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos: ");
        LocalDate fecha_nacimiento_actualizada = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingresa nueva fecha de nacimiento: "));
        String documento_identidad_actualizado = JOptionPane.showInputDialog(null, "Ingresa nuevo documento de identidad: ");


        Paciente paciente = new Paciente();

        paciente.setNombre(nombreActualizado);
        paciente.setApellidos(apellidosActualizados);
        paciente.setFecha_nacimiento(fecha_nacimiento_actualizada);
        paciente.setDocumento_identidad(documento_identidad_actualizado);
        paciente.setId(id);

        this.pacienteModel.actualizarPaciente(paciente);

        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
    }

    public void eliminarPaciente(){
        JOptionPane.showMessageDialog(null, "Estos son los pacientes: " + pacienteModel.consultar());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id de la paciente a eliminar"));

        this.pacienteModel.eliminarPaciente(id);
    }
}
