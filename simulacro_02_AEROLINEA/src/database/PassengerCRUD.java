package database;

import entity.Passenger;

import java.util.List;

public interface PassengerCRUD {

    //Object es una clase genérica para los objetos, donde cualquier objeto de java puede ser un object.
    Passenger create(Passenger passenger);

    List<Passenger> findAll();

    List<Passenger> findByFilter(String filter, String value);

    void update(Passenger passenger);

    void delete(Integer id);

}

