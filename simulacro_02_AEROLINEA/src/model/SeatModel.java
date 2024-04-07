package model;

import database.ConfigDB;
import database.SeatCRUD;
import entity.Seat;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatModel implements SeatCRUD {
    @Override
    public Seat create(Seat seat) {
        Connection connection = ConfigDB.openConnection();

        try {

            //4. Crear el SQL
            String sql = "INSERT INTO seats(seat_code, availability, id_flight) VALUE(?, ?, ?);";

            //5. Preparar el statement
            PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6.Asignar los "?"
            prepared.setString(1, seat.getSeatCode());
            prepared.setBoolean(2, seat.getAvailability());
            prepared.setInt(3, seat.getIdFlight());


            //7. Ejecutamos el Query
            prepared.execute();

            //8. Obtener el resultado
            ResultSet result = prepared.getGeneratedKeys();
            while (result.next()) {
                seat.setId(result.getInt(1));
            }

            //9. Cerramos el prepareStatement
            prepared.close();
            JOptionPane.showMessageDialog(null, "Seat created correctly.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating the chair" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return seat;
    }

    public List<Seat> saveAll(List<Seat> seats) {
        Connection connection = ConfigDB.openConnection();
        List<Seat> savedSeats = new ArrayList<>();

        try {
            String sql = "INSERT INTO seats(seat_code, availability, id_flight) VALUE(?, ?, ?);";
            PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            for (Seat seat : seats) {
                prepared.setString(1, seat.getSeatCode());
                prepared.setBoolean(2, seat.getAvailability());
                prepared.setInt(3, seat.getIdFlight());
                prepared.addBatch(); // Agregar el statement a un batch para ejecutarlo en conjunto
            }

            prepared.executeBatch(); // Ejecutar todas las inserciones en conjunto

            ResultSet result = prepared.getGeneratedKeys();
            int index = 0;
            while (result.next()) {
                seats.get(index).setId(result.getInt(1)); // Asignar el ID generado a cada asiento
                savedSeats.add(seats.get(index)); // Agregar el asiento guardado a la lista de retorno
                index++;
            }

            // prepared.close();
            JOptionPane.showMessageDialog(null, "Seats created correctly.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating the seats: " + e.getMessage());
        }

        //ConfigDB.closeConnection();
        return savedSeats;
    }

    @Override
    public void update(Seat seat) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE seats SET availability = ? WHERE (`id` = ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setBoolean(1, seat.getAvailability());
            prepareCall.setInt(2, seat.getId());

            prepareCall.execute();

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {

    }

    public List<Seat> findAllByIdFlight(Integer id) {

        Connection connection = ConfigDB.openConnection();
        List<Seat> seatList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM seats WHERE id_flight = ? AND availability = true;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Seat seat = new Seat();

                seat.setId(result.getInt("id"));
                seat.setSeatCode(result.getString("seat_code"));
                seat.setAvailability(result.getBoolean("availability"));
                seat.setIdFlight(result.getInt("id_flight"));

                seatList.add(seat);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the list" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return seatList;

    }
}
