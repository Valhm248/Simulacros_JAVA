package database;

import entity.Paciente;

import java.util.List;

public interface CRUDPaciente {

    Paciente guardarPaciente(Paciente paciente);

    List<Paciente> consultar();

    List<Paciente> consultarPorFiltro(String filter, String value);


    Paciente actualizarPaciente(Paciente paciente);


    void eliminarPaciente(Integer id);
}
