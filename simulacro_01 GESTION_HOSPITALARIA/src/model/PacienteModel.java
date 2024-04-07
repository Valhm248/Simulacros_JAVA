package model;

import database.CRUDPaciente;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacienteModel implements CRUDPaciente {
    @Override
    public Paciente guardarPaciente(Paciente paciente) {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear SQL
            String sql = "INSERT INTO pacientes(nombre, apellidos, fecha_nacimiento, documento_identidad) VALUES(?,?,?,?)";

            //4.Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los ?
            prepare.setString(1, paciente.getNombre());
            prepare.setString(2, paciente.getApellidos());
            prepare.setDate(3, Date.valueOf(paciente.getFecha_nacimiento()));
            prepare.setString(4, paciente.getDocumento_identidad());


            //6.Ejecutamos el Query
            prepare.execute();

            //7. Obtener el resultado
            ResultSet result = prepare.getGeneratedKeys();
            while (result.next()){
                paciente.setId(result.getInt(1));
            }

            //8.Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "Paciente creado");


        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear paciente" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();


        return paciente;
    }

    @Override
    public List<Paciente> consultar() {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        List<Paciente> pacienteList = new ArrayList<>();

        try {
            //3. Crear el SQL
            String sql = "SELECT * FROM pacientes;";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //6. Ejecutamos el Query
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()){

                //Creamos una instancias
                Paciente paciente = new Paciente();

                //Llenamos nuestros objeto con lo que devuelve la base de datos
                paciente.setId(resultSet.getInt("id"));
                paciente.setNombre(resultSet.getString("nombre"));
                paciente.setApellidos(resultSet.getString("apellidos"));
                paciente.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumento_identidad(resultSet.getString("documento_identidad"));

                //Finalmente agregar a la lista
                pacienteList.add(paciente);
            }

            //8. Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "¡Lista de pacientes exitosa!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mostrar lista");
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return pacienteList;
    }

    @Override
    public List<Paciente> consultarPorFiltro(String filter, String value) {
        Connection connection = ConfigDB.openConnection();

        String sql;

        List<Paciente> pacienteList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM Pacientes WHERE id = ?;";

                pacienteList = consultarPorId(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM Pacientes WHERE nombre LIKE ?;";

                pacienteList = consultarPorNombre(sql, value);
            }
            if (Objects.equals(filter, "Apellido")) {

                sql = "SELECT * FROM Pacientes WHERE apellido LIKE ?;";

                pacienteList = consultarPorApellidos(sql, value);
            }
            if (Objects.equals(filter, "fecha de nacimiento")) {

                sql = "SELECT * FROM Pacientes WHERE fecha_nacimiento LIKE ?;";

                pacienteList = consultarPorFecha(sql, value);
            }
            if (Objects.equals(filter, "Documento")) {

                sql = "SELECT * FROM Pacientes WHERE documento_identidad LIKE ?;";

                pacienteList = consutarPorDocumento(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return pacienteList;
    }



    public List<Paciente> consultarPorId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellidos(objResult.getString("apellido"));
                paciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return pacienteList;

    }

    private List<Paciente> consultarPorNombre(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellidos(objResult.getString("apellido"));
                paciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return pacienteList;

    }

    private List<Paciente> consultarPorApellidos(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellidos(objResult.getString("apellido"));
                paciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return pacienteList;

    }

    private List<Paciente> consultarPorFecha(String sql, String value) {
        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellidos(objResult.getString("apellido"));
                paciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return pacienteList;
    }

    private List<Paciente> consutarPorDocumento(String sql, String value) {
        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellidos(objResult.getString("apellido"));
                paciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return pacienteList;
    }


    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL
            String sql = "UPDATE pacientes SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE (id = ?);";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //5. Asignar los ?
            prepare.setString(1, paciente.getNombre());
            prepare.setString(2, paciente.getApellidos());
            prepare.setDate(3, Date.valueOf((LocalDate)paciente.getFecha_nacimiento()));
            prepare.setString(4, paciente.getDocumento_identidad());
            prepare.setInt(5, paciente.getId());


            //6. Ejecutamos el Query
            prepare.execute();

            prepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return paciente;
    }

    @Override
    public void eliminarPaciente(Integer id) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL y el prepare statement
            String sql = "DELETE FROM pacientes WHERE id;";
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);
            prepare.setInt(1, id);
            prepare.execute();
            prepare.close();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar");
        }

        //9. Cerramos las conexión
        ConfigDB.closeConnection();
    }




}
