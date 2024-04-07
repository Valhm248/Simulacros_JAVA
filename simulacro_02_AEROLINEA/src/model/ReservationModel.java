package model;

import database.ConfigDB;
import database.ReservationCRUD;
import entity.Flight;
import entity.Reservation;
import entity.Seat;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationModel implements ReservationCRUD {


    SeatModel seatModel = new SeatModel();


    @Override
    public Reservation create(Reservation reservation) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO reservations(reservation_date, reservation_time, seat, id_passengers, id_flight) VALUE ( ?, ?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setDate(1, Date.valueOf(reservation.getReservationDate()));
            prepareCall.setTime(2, Time.valueOf(reservation.getReservationTime().concat(":00")));
            prepareCall.setString(3, reservation.getSeat());
            prepareCall.setInt(4, reservation.getIdPassenger());
            prepareCall.setInt(5, reservation.getIdFlight());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()) {
                reservation.setId(result.getInt(1));
            }

            Seat seat = new Seat();
            seat.setId(reservation.getIdSeat());
            seat.setAvailability(Boolean.FALSE);

            seatModel.update(seat);

            JOptionPane.showMessageDialog(null, "Reserve created correctly\n" + reservation);

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating reservation\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Reservation> reservationList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM reservations;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Reservation reservations = new Reservation();

                reservations.setId(result.getInt("id"));
                reservations.setReservationDate(result.getString("reservation_date"));
                reservations.setReservationTime(result.getString("reservation_time"));
                reservations.setSeat(result.getString("seat"));
                reservations.setIdPassenger(result.getInt("id_passengers"));
                reservations.setIdFlight(result.getInt("id_flight"));



                reservationList.add(reservations);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the list" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return reservationList;
    }

    @Override
    public List<Reservation> findByFilter(String filter, String value) {

        String sql;

        List<Reservation> reservationList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM reservations WHERE id = ?;";

                reservationList = findById(sql, value);

            }
            if (Objects.equals(filter, "Date")) {

                sql = "SELECT * FROM reservations WHERE reservation_date = ?;";

                reservationList = findByDate(sql, value);
            }
            if (Objects.equals(filter, "Time")) {

                sql = "SELECT * FROM reservations WHERE departure_time = ?;";

                reservationList = findByTime(sql, value);
            }
            if (Objects.equals(filter, "Passenger")) {

                sql = "SELECT * FROM reservations r JOIN passengers p ON r.id_passengers = p.id WHERE p.id  = ?;";

                reservationList = findByPassenger(sql, value);
            }
            if (Objects.equals(filter, "Flight")) {

                sql = "SELECT * FROM reservations r JOIN flights f ON r.id_flight = f.id WHERE f.id = ?;";

                reservationList = findByFlight(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error application filter " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return reservationList;
    }

    public List<Reservation> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Reservation> reservationList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Reservation reservations = new Reservation();

                reservations.setId(result.getInt("id"));
                reservations.setReservationDate(result.getString("reservation_date"));
                reservations.setReservationTime(result.getString("reservation_time"));
                reservations.setSeat(result.getString("seat"));
                reservations.setIdPassenger(result.getInt("id_passengers"));
                reservations.setIdFlight(result.getInt("id_flight"));

                reservationList.add(reservations);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, reservationList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return reservationList;

    }

    public List<Reservation> findByDate(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Reservation> reservationList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setDate(1, Date.valueOf(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Reservation reservations = new Reservation();

                reservations.setId(result.getInt("id"));
                reservations.setReservationDate(result.getString("reservation_date"));
                reservations.setReservationTime(result.getString("reservation_time"));
                reservations.setSeat(result.getString("seat"));
                reservations.setIdPassenger(result.getInt("id_passengers"));
                reservations.setIdFlight(result.getInt("id_flight"));

                reservationList.add(reservations);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, reservationList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return reservationList;

    }

    public List<Reservation> findByTime(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Reservation> reservationList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setTime(1, Time.valueOf(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Reservation reservations = new Reservation();

                reservations.setId(result.getInt("id"));
                reservations.setReservationDate(result.getString("reservation_date"));
                reservations.setReservationTime(result.getString("reservation_time"));
                reservations.setSeat(result.getString("seat"));
                reservations.setIdPassenger(result.getInt("id_passengers"));
                reservations.setIdFlight(result.getInt("id_flight"));

                reservationList.add(reservations);
            }


            prepareCall.close();
            JOptionPane.showMessageDialog(null, reservationList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return reservationList;

    }

    public List<Reservation> findByPassenger(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Reservation> reservationList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Reservation reservations = new Reservation();

                reservations.setId(result.getInt("id"));
                reservations.setReservationDate(result.getString("reservation_date"));
                reservations.setReservationTime(result.getString("reservation_time"));
                reservations.setSeat(result.getString("seat"));
                reservations.setIdPassenger(result.getInt("id_passengers"));
                reservations.setIdFlight(result.getInt("id_flight"));

                reservationList.add(reservations);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, reservationList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return reservationList;

    }

    public List<Reservation> findByFlight(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Reservation> reservationList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));

            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Reservation reservations = new Reservation();

                reservations.setId(result.getInt("id"));
                reservations.setReservationDate(result.getString("reservation_date"));
                reservations.setReservationTime(result.getString("reservation_time"));
                reservations.setSeat(result.getString("seat"));
                reservations.setIdPassenger(result.getInt("id_passengers"));
                reservations.setIdFlight(result.getInt("id_flight"));

                reservationList.add(reservations);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, reservationList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return reservationList;

    }

    @Override
    public void update(Reservation reservation) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE reservations SET reservation_date = ?,  reservation_time = ?, seat = ?, id_passengers = ?, id_flight = ? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setDate(1, Date.valueOf(reservation.getReservationDate()));
            prepareCall.setTime(2, Time.valueOf(reservation.getReservationTime().concat(":00")));
            prepareCall.setString(3, reservation.getSeat());
            prepareCall.setInt(4, reservation.getIdPassenger());
            prepareCall.setInt(5, reservation.getIdFlight());
            prepareCall.setInt(6, reservation.getId());
            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Reservation update.\n" + reservation);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM reservations WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();

            JOptionPane.showMessageDialog(null, "Flight successfully eliminated\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error deleting\n" + e.getMessage());
        }

        ConfigDB.closeConnection();

    }
}
