package model;

import database.AirplaneCRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AirplaneModel implements AirplaneCRUD {

    @Override
    public Airplane create(Airplane airplane) {

        //1. Abrir la connexión
        Connection connection = ConfigDB.openConnection();


        try {

            //4. Crear el SQL
            String sql = "INSERT INTO airplanes(model, capacity) VALUE(?, ?);";

            //5. Preparar el statement
            PreparedStatement prepared = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6.Asignar los "?"
            prepared.setString(1, airplane.getModel());
            prepared.setInt(2, airplane.getCapacity());

            //7. Ejecutamos el Query
            prepared.execute();

            //8. Obtener el resultado
            ResultSet result = prepared.getGeneratedKeys();
            while (result.next()) {
                airplane.setId(result.getInt(1));
            }

            //9. Cerramos el prepareStatement
            prepared.close();
            JOptionPane.showMessageDialog(null, "Airplane insertion was successful.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding airplane " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return airplane;
    }

    @Override
    public List<Airplane> findAll() {

        Connection connection = ConfigDB.openConnection();

        List<Airplane> airplaneList = new ArrayList<>();

        try{

            String sql = "SELECT * FROM airplanes ORDER BY airplanes.id ASC;";
            PreparedStatement prepared = connection.prepareStatement(sql);

            ResultSet result = prepared.executeQuery();

            while (result.next()){

                Airplane airplane = new Airplane();

                airplane.setId(result.getInt("id"));
                airplane.setModel(result.getString("model"));
                airplane.setCapacity(result.getInt("capacity"));

                airplaneList.add(airplane);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
        }

        ConfigDB.closeConnection();
        return airplaneList;
    }

    @Override
    public List<Airplane> findByFilter(String filter, String value) {

        String sql;

        List<Airplane> airplaneList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM airplanes WHERE id = ?;";

                airplaneList = findByFilterId(sql, value);

            }
            if (Objects.equals(filter, "model")) {

                sql = "SELECT * FROM airplanes WHERE model LIKE ?;";

                airplaneList = findByModel(sql, value);
            }
            if (Objects.equals(filter, "Capacity")) {

                sql = "SELECT * FROM airplanes WHERE Capacity LIKE ?;";

                airplaneList = findByCapacity(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error filter application " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return airplaneList;
    }


    public List<Airplane> findByFilterId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();

        List<Airplane> airplaneList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Airplane airplane = new Airplane();

                airplane.setId(result.getInt("id"));
                airplane.setModel(result.getString("model"));
                airplane.setCapacity(result.getInt("capacity"));


                airplaneList.add(airplane);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List airplane:\n" + airplaneList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the filter  " + e.getMessage());
        }

        return airplaneList;

    }

    private List<Airplane> findByModel(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Airplane> airplaneList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Airplane airplane = new Airplane();

                airplane.setId(result.getInt("id"));
                airplane.setModel(result.getString("model"));
                airplane.setCapacity(result.getInt("capacity"));


                airplaneList.add(airplane);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List airplane:\n" + airplaneList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the filter  " + e.getMessage());
        }

        return airplaneList;

    }

    private List<Airplane> findByCapacity(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Airplane> airplaneList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Airplane airplane = new Airplane();

                airplane.setId(result.getInt("id"));
                airplane.setModel(result.getString("model"));
                airplane.setCapacity(result.getInt("capacity"));


                airplaneList.add(airplane);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "List airplane:\n" + airplaneList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the filter  " + e.getMessage());
        }

        return airplaneList;

    }

    @Override
    public void update(Airplane airplane) {


        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE airplanes SET model =?, capacity =? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, airplane.getModel());
            prepareCall.setInt(2, airplane.getCapacity());
            prepareCall.setInt(3, airplane.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Airplane update.\n" + airplane);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sql= "DELETE FROM airplanes WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();


            JOptionPane.showMessageDialog(null, "Airplane delete successful.\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Airplane delete successful " + e.getMessage());
        }

        ConfigDB.closeConnection();


    }

    public Airplane findById (Integer id){
        Connection connection = ConfigDB.openConnection();
       String sql = "SELECT * FROM airplanes WHERE id = ?;";


        Airplane airplane = new Airplane();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                airplane.setId(result.getInt("id"));
                airplane.setModel(result.getString("model"));
                airplane.setCapacity(result.getInt("capacity"));

            }

           prepareCall.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error find by ID  " + e.getMessage());
        }

        //ConfigDB.closeConnection();
        return airplane;
    }
}
