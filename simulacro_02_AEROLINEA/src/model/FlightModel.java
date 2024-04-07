package model;

import database.ConfigDB;
import database.FlightCRUD;
import entity.Airplane;
import entity.Flight;
import entity.Seat;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightModel implements FlightCRUD {

    AirplaneModel airplaneModel = new AirplaneModel();
    SeatModel seatModel = new SeatModel();


    @Override
    public Flight create(Flight flight) {

        Connection connection = ConfigDB.openConnection();

        try {

            //4. Crear el SQL
            String sql = "INSERT INTO flights(destination, departure_date, departure_time, id_airplane) VALUE(?, ?, ?, ?);";

            //5. Preparar el statement
            PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6.Asignar los "?"
            prepared.setString(1, flight.getDestination());
            prepared.setDate(2, Date.valueOf(flight.getDepartureDate()));
            prepared.setTime(3, Time.valueOf(flight.getDepartureTime()));
            prepared.setInt(4, flight.getIdAirplane());

            //7. Ejecutamos el Query
            prepared.execute();

            //8. Obtener el resultado
            ResultSet result = prepared.getGeneratedKeys();
            while (result.next()) {
                flight.setId(result.getInt(1));
            }

            Airplane airplane = airplaneModel.findById(flight.getIdAirplane());
            List<Seat> seatList = new ArrayList<>();

            //Menor o igual
            for (int i = 1; i <= airplane.getCapacity(); i++) {

                //Creamos el objeto asiento
                Seat seat = new Seat();

                //Asignamos los valores del asiento
                seat.setSeatCode(String.valueOf(i));
                seat.setAvailability(Boolean.TRUE);
                seat.setIdFlight(flight.getIdAirplane());
                seatList.add(seat);
            }

            seatModel.saveAll(seatList);

            //9. Cerramos el prepareStatement

            prepared.close();
            JOptionPane.showMessageDialog(null, "Flight insertion was successful.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Flight " + e.getMessage());
        }

        //ConfigDB.closeConnection();
        return flight;
    }

    @Override
    public List<Flight> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Flight> flightList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM flights;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Flight flight = new Flight();

                flight.setId(result.getInt("id"));
                flight.setDestination(result.getString("destination"));
                flight.setDepartureDate(result.getDate("departure_date").toLocalDate());
                flight.setDepartureTime(result.getTime("departure_time").toLocalTime());
                flight.setIdAirplane(result.getInt("id_airplane"));



                flightList.add(flight);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the list" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return flightList;

    }

    @Override
    public List<Flight> findByFilter(String filter, String value) {

        String sql;

        List<Flight> flightList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM flights WHERE id = ?;";

                flightList = findById(sql, value);

            }
            if (Objects.equals(filter, "Destination")) {

                sql = "SELECT * FROM flights WHERE destination LIKE ?;";

                flightList = findByDestination(sql, value);
            }
            if (Objects.equals(filter, "Date")) {

                sql = "SELECT * FROM flights WHERE departure_date = ?;";

                flightList = findByDate(sql, value);
            }
            if (Objects.equals(filter, "Time")) {

                sql = "SELECT * FROM flights WHERE departure_time = ?;";

                flightList = findByTime(sql, value);
            }
            if (Objects.equals(filter, "Airplane")) {

                sql = "SELECT * FROM flights f JOIN airplanes a ON f.id_airplane = a.id WHERE a.id = ?;";

                flightList = findByAirplane(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error application filter " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return flightList;
    }
    public List<Flight> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Flight> flightList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Flight flight = new Flight();

                flight.setId(result.getInt("id"));
                flight.setDestination(result.getString("destination"));
                flight.setDepartureDate(result.getDate("departure_date").toLocalDate());
                flight.setDepartureTime(result.getTime("departure_time").toLocalTime());
                flight.setIdAirplane(result.getInt("id_airplane"));



                flightList.add(flight);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, flightList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return flightList;

    }
    public List<Flight> findByDestination(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Flight> flightList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Flight flight = new Flight();

                flight.setId(result.getInt("id"));
                flight.setDestination(result.getString("destination"));
                flight.setDepartureDate(result.getDate("departure_date").toLocalDate());
                flight.setDepartureTime(result.getTime("departure_time").toLocalTime());
                flight.setIdAirplane(result.getInt("id_airplane"));



                flightList.add(flight);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, flightList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return flightList;

    }
    public List<Flight> findByDate(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Flight> flightList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setDate(1, Date.valueOf(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Flight flight = new Flight();

                flight.setId(result.getInt("id"));
                flight.setDestination(result.getString("destination"));
                flight.setDepartureDate(result.getDate("departure_date").toLocalDate());
                flight.setDepartureTime(result.getTime("departure_time").toLocalTime());
                flight.setIdAirplane(result.getInt("id_airplane"));



                flightList.add(flight);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, flightList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return flightList;

    }
    public List<Flight> findByTime(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Flight> flightList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setTime(1, Time.valueOf(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Flight flight = new Flight();

                flight.setId(result.getInt("id"));
                flight.setDestination(result.getString("destination"));
                flight.setDepartureDate(result.getDate("departure_date").toLocalDate());
                flight.setDepartureTime(result.getTime("departure_time").toLocalTime());
                flight.setIdAirplane(result.getInt("id_airplane"));



                flightList.add(flight);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, flightList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return flightList;

    }
    public List<Flight> findByAirplane(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Flight> flightList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));

            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Flight flight = new Flight();

                flight.setId(result.getInt("id"));
                flight.setDestination(result.getString("destination"));
                flight.setDepartureDate(result.getDate("departure_date").toLocalDate());
                flight.setDepartureTime(result.getTime("departure_time").toLocalTime());
                flight.setIdAirplane(result.getInt("id_airplane"));



                flightList.add(flight);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, flightList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return flightList;

    }

    @Override
    public void update(Flight flight) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE flights SET destination = ?,  departure_date = ?, departure_time = ?, id_airplane = ? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, flight.getDestination());
            prepareCall.setDate(2, Date.valueOf(flight.getDepartureDate()));
            prepareCall.setTime(3, Time.valueOf(flight.getDepartureTime()));
            prepareCall.setInt(4, flight.getIdAirplane());
            prepareCall.setInt(5, flight.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Flight update.\n" + flight);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM flights WHERE id = ?;";

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
