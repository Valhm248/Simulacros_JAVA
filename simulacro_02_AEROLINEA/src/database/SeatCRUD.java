package database;

import entity.Seat;

public interface SeatCRUD {


    Seat create(Seat seat);

    void update(Seat seat);

    void delete(Integer id);

}

