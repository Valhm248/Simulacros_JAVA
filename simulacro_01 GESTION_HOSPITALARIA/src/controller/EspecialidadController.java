package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.util.List;

public class EspecialidadController {

    EspecialidadModel especialidadModel;

    public EspecialidadController(EspecialidadModel especialidadModel){
        this.especialidadModel = new EspecialidadModel();
    }

    public void guardarEspecialidad(){

        Especialidad especialidad = new Especialidad();

        String nombre = JOptionPane.showInputDialog(null, "Ingresa nombre de la especialidad: ");
        String descripcion = JOptionPane.showInputDialog(null, "Ingresa descripción: ");

        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);

        this.especialidadModel.guardarEspecialidad(especialidad);

        JOptionPane.showMessageDialog(null, "Especialidad guardada: \n" + especialidad.toString());
    }

    public void consultar(){

        List<Especialidad> list = this.especialidadModel.consultar();

        JOptionPane.showMessageDialog(null, "Lista de especialidades: \n" + list);

    }

    public void consultarPorFiltro(){
        String[] options = {"ID" ,"Nombre"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID de la especialidad, nombre de la especialidad)");

        List<Especialidad> list = this.especialidadModel.consultarPorFiltro(selectedFilter, valueFilter);
    }

    public void actualizarEspecialidad(){

        JOptionPane.showMessageDialog(null, "Estas son las especialidades: " + especialidadModel.consultar());
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id: "));

        String nombreActualizado = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre: ");
        String descripcionActualizada = JOptionPane.showInputDialog(null, "Ingresa la nueva descripción: ");

        Especialidad especialidad = new Especialidad();

        especialidad.setNombre(nombreActualizado);
        especialidad.setDescripcion(descripcionActualizada);
        especialidad.setId(id);

        this.especialidadModel.actualizarEspecialidad(especialidad);

        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
    }

    public void eliminarEspecialidad(){

        JOptionPane.showMessageDialog(null, "Estas son las especialidades: " + especialidadModel.consultar());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id de la especialidad a eliminar"));

        this.especialidadModel.eliminarEspecialidad(id);
    }
}
