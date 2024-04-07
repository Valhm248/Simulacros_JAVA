package database;

import entity.Airplane;

import java.util.List;

public interface AirplaneCRUD {

    //Object es una clase genérica para los objetos, donde cualquier objeto de java puede ser un object.
     Airplane create(Airplane airplane);

    List<Airplane> findAll();

    List<Airplane> findByFilter(String filter, String value);

    void update(Airplane airplane);

    void delete(Integer id);

}

