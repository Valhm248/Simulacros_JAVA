package model;

import database.CRUDMedico;
import database.ConfigDB;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicoModel implements CRUDMedico {
    @Override
    public Medico guardarMedico(Medico medico) {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear SQL
            String sql = "INSERT INTO medicos(nombre, apellidos, id_especialidad) VALUES(?,?,?)";

            //4.Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los ?
            prepare.setString(1, medico.getNombre());
            prepare.setString(2, medico.getApellidos());
            prepare.setInt(3, medico.getId_especialidad());

            //6.Ejecutamos el Query
            prepare.execute();

            //7. Obtener el resultado
            ResultSet result = prepare.getGeneratedKeys();
            while (result.next()){
                medico.setId(result.getInt(1));
            }

            //8.Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "Médico creado");


        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();


        return medico;
    }

    @Override
    public List<Medico> consultar() {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        List<Medico> medicoList = new ArrayList<>();

        try {
            //3. Crear el SQL
            String sql = "SELECT * FROM medicos;";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //6. Ejecutamos el Query
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()){

                //Creamos una instancias
                Medico medico = new Medico();

                //Llenamos nuestros objeto con lo que devuelve la base de datos
                medico.setId(resultSet.getInt("id"));
                medico.setNombre(resultSet.getString("nombre"));
                medico.setApellidos(resultSet.getString("apellidos"));
                medico.setId_especialidad(resultSet.getInt("id_especialidad"));

                //Finalmente agregar a la lista
                medicoList.add(medico);
            }

            //8. Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "¡Lista de médicos exitosa!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mostrar lista");
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return medicoList;
    }

    @Override
    public List<Medico> consultarPorFiltro(String filter, String value) {
        String sql;

        List<Medico> medicoList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM medicos WHERE id = ?;";

                medicoList = findById(sql, value);

            }
            if (Objects.equals(filter, "Model")) {

                sql = "SELECT * FROM medicos WHERE model LIKE ?;";

                medicoList = findByName(sql, value);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error filter application " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return medicoList;
    }



    private List<Medico> findById(String sql, String value) {
        Connection connection = ConfigDB.openConnection();

        List<Medico> medicoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Medico medico = new Medico();

                medico.setId(result.getInt("id"));
                medico.setNombre(result.getString("nombre"));
                medico.setApellidos(result.getString("apellidos"));
                medico.setId_especialidad(result.getInt("id_especialidad"));


                medicoList.add(medico);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista médicos:\n" + medicoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the filter  " + e.getMessage());
        }

        return medicoList;
    }


    private List<Medico> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Medico> medicoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Medico medico = new Medico();

                medico.setId(objResult.getInt("id"));
                medico.setNombre(objResult.getString("nombre"));
                medico.setApellidos(objResult.getString("apellidos"));
                medico.setId_especialidad(objResult.getInt("id_especialidad"));



                medicoList.add(medico);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de médicos: " + medicoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al filtrar " + e.getMessage());

        }

        return medicoList;
    }
    @Override
    public Medico actualizarMedico(Medico medico) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL
            String sql = "UPDATE especialidades SET nombre = ?, apellidos = ?, id_especialidad = ? WHERE (id = ?);";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //5. Asignar los ?
            prepare.setString(1, medico.getNombre());
            prepare.setString(2, medico.getApellidos());
            prepare.setInt(3, medico.getId_especialidad());
            prepare.setInt(4, medico.getId());

            //6. Ejecutamos el Query
            prepare.execute();

            prepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return medico;
    }

    @Override
    public void eliminarMedico(Integer id) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL y el prepare statement
            String sql = "DELETE FROM medicos WHERE id;";
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
