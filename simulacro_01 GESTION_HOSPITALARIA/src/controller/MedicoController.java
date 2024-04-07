package controller;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;
import java.util.List;

public class MedicoController {
    static MedicoModel medicoModel;

    public MedicoController(MedicoModel medicoModel){
        this.medicoModel = medicoModel;
    }

    public static void guardarMedico(){

        Medico medico = new Medico();

        String nombre = JOptionPane.showInputDialog(null, "Ingresa nombre: ");
        String apellidos = JOptionPane.showInputDialog(null, "Ingresa apellidos: ");
        Integer id_especialidad = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingresa el id de la especialidad: "));

        medico.setNombre(nombre);
        medico.setApellidos(apellidos);
        medico.setId_especialidad(id_especialidad);

        medico = this.medicoModel.guardarMedico(medico);

        JOptionPane.showMessageDialog(null, "Médico guardado: \n" + medico.toString());
    }

    public static void consultar(){

        List<Medico> listMedico;

        listMedico = this.medicoModel.consultar();

        JOptionPane.showMessageDialog(null, "Lista de medicoS: \n" + listMedico);

    }

    public static void consultarPorFiltro(){

        String[] options = {"ID" ,"Nombre"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione un tipo de dato: \n", "Filtro", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID, Nombre)");

        List<Medico> medicoList = this.medicoModel.consultarPorFiltro(selectedFilter, valueFilter);
    }

    public static void actualizarMedico(){
        JOptionPane.showMessageDialog(null, "Estas son LOS medicos: " + medicoModel.consultar());

        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id: "));

        String nombreActualizado = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre: ");
        String apellidosActualizados = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos: ");
        Integer idEspecialidadActualizado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa nuevo id de especialidad: "));

        Medico medico = new Medico();

        medico.setNombre(nombreActualizado);
        medico.setApellidos(apellidosActualizados);
        medico.setId_especialidad(idEspecialidadActualizado);
        medico.setId(id);

        this.medicoModel.actualizarMedico(medico);

        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
    }

    public static void eliminarMedico(){

        JOptionPane.showMessageDialog(null, "Estos son los médicos: " + medicoModel.consultar());

        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id de la especialidad a eliminar"));

        this.medicoModel.eliminarMedico(id);
    }
}
