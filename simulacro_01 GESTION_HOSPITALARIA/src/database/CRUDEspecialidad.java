package database;

import entity.Especialidad;

import java.util.List;

public interface CRUDEspecialidad {

    Especialidad guardarEspecialidad(Especialidad especialidad);

    List<Especialidad> consultar();

    List<Especialidad> consultarPorFiltro(String filter, String value);


    Especialidad actualizarEspecialidad(Especialidad especialidad);

    void eliminarEspecialidad(Integer id);
}
