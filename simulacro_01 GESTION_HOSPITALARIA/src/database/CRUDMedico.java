package database;

import entity.Medico;

import java.util.List;

public interface CRUDMedico {

    Medico guardarMedico(Medico medico);

    List<Medico> consultar();

    List<Medico> consultarPorFiltro(String filter, String value);


    Medico actualizarMedico(Medico medico);

    void eliminarMedico(Integer id);
}
