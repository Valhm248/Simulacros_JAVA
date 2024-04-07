package model;

import database.ConfigDB;
import database.PassengerCRUD;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PassengerModel implements PassengerCRUD {
    @Override
    public Passenger create(Passenger passenger) {

        //1. Abrir conexión
        Connection connection = ConfigDB.openConnection();


        try {
            //3. Crear SQL
            String sql = "INSERT INTO passengers(name_passenger, lastname, identity_card) VALUES(?, ?, ?)";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los ?
            prepare.setString(1, passenger.getNamePassenger());
            prepare.setString(2, passenger.getLastname());
            prepare.setString(3, passenger.getIdentityCard());

            //6. Ejecutamos el query
            prepare.execute();

            //7.Obtener el resultado
            ResultSet result = prepare.getGeneratedKeys();
            while (result.next()){
                passenger.setId(result.getInt(1));
            }

            //8. Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "Insertation succesfully");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error to insert" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return passenger;
    }

    @Override
    public List<Passenger> findAll() {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        List<Passenger> passengerList = new ArrayList<>();

        try {
            //3. Crear SQL
            String sql = "SELECT * FROM passengers;";

            //4.Prepara el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //5. Ejecutamos el query
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()){

                //creamos instancias
                Passenger passenger = new Passenger();

                //Llenamos nuestros objeto con lo que devuelve la base de datos
                passenger.setId(resultSet.getInt("id"));
                passenger.setNamePassenger(resultSet.getString("name_passenger"));
                passenger.setLastname(resultSet.getString("lastname"));
                passenger.setIdentityCard(resultSet.getString("identity_card"));

                //Agregamos a la lista
                passengerList.add(passenger);
            }

            //8. Cerramos el prepare
            prepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error to show list of passengers");
        }

        //9. Cerrar conexión
        ConfigDB.closeConnection();

        return passengerList;
    }

    @Override
    public List<Passenger> findByFilter(String filter, String value) {

        String sql;

        List<Passenger> passengerList = new ArrayList<>();

        try {
            if(Objects.equals(filter, "ID")){
                sql = "SELECT * FROM passengers WHERE id = ?;";

                passengerList = findById(sql, value);
            }

            if (Objects.equals(filter, "Name passenger")) {

                sql = "SELECT * FROM passengers WHERE name_passenger LIKE ?;";

                passengerList = findByName(sql, value);
            }
            if (Objects.equals(filter, "Last name")) {

                sql = "SELECT * FROM passengers WHERE lastname LIKE ?;";

                passengerList = findByLastName(sql, value);
            }
            if (Objects.equals(filter, "Identity card")) {

                sql = "SELECT * FROM passengers WHERE identity_card LIKE ?;";

                passengerList = findByIdentityCard(sql, value);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error to apply filter " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return passengerList;
    }

    public List<Passenger> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Passenger> passengerList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Passenger passenger = new Passenger();

                passenger.setId(objResult.getInt("id"));
                passenger.setNamePassenger(objResult.getString("name_passenger"));
                passenger.setLastname(objResult.getString("lastname"));
                passenger.setIdentityCard(objResult.getString("identity_card"));


                passengerList.add(passenger);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List of passengers: " + passengerList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filters " + e.getMessage());
        }

        return passengerList;

    }

    private List<Passenger> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Passenger> passengerList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Passenger passenger = new Passenger();

                passenger.setId(objResult.getInt("id"));
                passenger.setNamePassenger(objResult.getString("name_passenger"));
                passenger.setLastname(objResult.getString("lastname"));
                passenger.setIdentityCard(objResult.getString("identity_card"));



                passengerList.add(passenger);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List of passengers: " + passengerList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show the filter " + e.getMessage());

        }

        return passengerList;

    }

    private List<Passenger> findByLastName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Passenger> passengerList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Passenger passenger = new Passenger();

                passenger.setId(objResult.getInt("id"));
                passenger.setNamePassenger(objResult.getString("name_passenger"));
                passenger.setLastname(objResult.getString("lastname"));
                passenger.setIdentityCard(objResult.getString("identity_card"));



                passengerList.add(passenger);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List of passengers: " + passengerList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show the filter" + e.getMessage());

        }

        return passengerList;

    }

    private List<Passenger> findByIdentityCard(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Passenger> passengerList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Passenger passenger = new Passenger();

                passenger.setId(objResult.getInt("id"));
                passenger.setNamePassenger(objResult.getString("name_passenger"));
                passenger.setLastname(objResult.getString("lastname"));
                passenger.setIdentityCard(objResult.getString("identity_card"));



                passengerList.add(passenger);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List of passengers: " + passengerList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show the filter " + e.getMessage());

        }

        return passengerList;

    }

    @Override
    public void update(Passenger passenger){
        //Abrir la conexión
        Connection connection = ConfigDB.openConnection();


        try {
            //Crear sql
            String sql = "UPDATE passengers SET name_passenger = ?, lastname = ?, identity_card = ? WHERE id = ?;";

            //Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //Asignar los ?
            prepare.setString(1, passenger.getNamePassenger());
            prepare.setString(2, passenger.getLastname());
            prepare.setString(3, passenger.getIdentityCard());
            prepare.setInt(4, passenger.getId());

            //Ejecutamos el execute
            prepare.execute();

            //Cerrar el prepare
            prepare.close();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR to update" + e.getMessage());

        }

        //Cerramos conexión
        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {
        //Abrir la conexión
        Connection connection = ConfigDB.openConnection();


        try {
            //Crear el SQL y el prepare statement
            String sql = "DELETE FROM passengers WHERE id = ?;";
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);
            prepare.setInt(1, id);
            prepare.execute();
            prepare.close();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR to delete");
        }

        //9. Cerramos las conexión
        ConfigDB.closeConnection();
    }
}