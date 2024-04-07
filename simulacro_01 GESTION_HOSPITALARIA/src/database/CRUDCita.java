package database;

import entity.Cita;

import java.util.List;

public interface CRUDCita {

    Cita guardarCita(Cita cita);

    List<Cita> consultar();

    List<Cita> consultarPorFiltro(String filter, String value);


    Cita actualizarCita(Cita cita);

    void eliminarCita(Integer id);
}
