package database;

import entity.Reservation;

import java.util.List;

public interface ReservationCRUD {

    //Object es una clase genérica para los objetos, donde cualquier objeto de java puede ser un object.
    Reservation create(Reservation reservation);

    List<Reservation> findAll();

    List<Reservation> findByFilter(String filter, String value);

    void update(Reservation reservation);

    void delete(Integer id);

}

