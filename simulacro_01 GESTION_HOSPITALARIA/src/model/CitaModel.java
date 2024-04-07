package model;

import database.CRUDCita;
import database.ConfigDB;
import entity.Cita;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CitaModel implements CRUDCita {
    @Override
    public Cita guardarCita(Cita cita) {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear SQL
            String sql = "INSERT INTO especialidades(id_paciente, id_medico, fecha_cita, hora_cita, motivo) VALUES(?,?,?,?,?)";

            //4.Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los ?
            prepare.setInt(1, cita.getId_paciente());
            prepare.setInt(2, cita.getId_medico());
            prepare.setDate(3, cita.getFecha_cita());
            prepare.setTime(4,cita.getHora_cita());
            prepare.setString(5, cita.getMotivo);



            //6.Ejecutamos el Query
            prepare.execute();

            //7. Obtener el resultado
            ResultSet result = prepare.getGeneratedKeys();
            while (result.next()){
                cita.setId(result.getInt(1));
            }

            //8.Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "Cita creada");


        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear cita" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();


        return cita;
    }

    @Override
    public List<Cita> consultar() {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        List<Cita> citaList = new ArrayList<>();

        try {
            //3. Crear el SQL
            String sql = "SELECT * FROM citas;";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //6. Ejecutamos el Query
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()){

                //Creamos una instancias
                Cita cita = new Cita();

                //Llenamos nuestros objeto con lo que devuelve la base de datos
                cita.setId(resultSet.getInt("id"));
                cita.setId_paciente(resultSet.getInt("id_paciente"));
                cita.setId_medico(resultSet.getInt("id_medico"));
                cita.setFecha_cita(resultSet.getDate("fecha_cita"));
                cita.setHora_cita(resultSet.getTime("hora_cita"));
                cita.setMotivo(resultSet.getString("motivo"));

                //Finalmente agregar a la lista
                citaList.add(cita);
            }

            //8. Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "¡Lista de citas exitosa!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mostrar lista");
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return citaList;
    }

    @Override
    public List<Cita> consultarPorFiltro(String filter, String value) {
        Connection connection = ConfigDB.openConnection();

        String sql;

        List<Cita> citaList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM citas WHERE id = ?;";

                citaList = consultarPorId(sql, value);

            }
            if (Objects.equals(filter, "Fecha cita")) {
                sql = "SELECT * FROM citas WHERE fecha_cita = ?;";

                citaList = consultarPorFechaCita(sql, value);

            }
            if (Objects.equals(filter, "Hora cita")) {
                sql = "SELECT * FROM citas WHERE hora_cita = ?;";

                citaList = consultarPorHoraCita(sql, value);

            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return citaList;
    }

    private List<Cita> consultarPorId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Cita cita = new Cita();

                cita.setId(objResult.getInt("id"));
                cita.setFecha_cita(objResult.getDate("fecha_cita").toLocalDate());
                cita.setHora_cita(objResult.getTime("hora_cita").toLocalTime());
                cita.setId_paciente(objResult.getInt("id_paciente"));
                cita.setId_medico(objResult.getInt("is_medico"));

                citaList.add(cita);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de citas:\n" + citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;
    }

    private List<Cita> consultarPorFechaCita(String sql, String value) {
        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Cita cita = new Cita();

                cita.setId(objResult.getInt("id"));
                cita.setFecha_cita(objResult.getDate("fecha_cita").toLocalDate());
                cita.setHora_cita(objResult.getTime("hora_cita").toLocalTime());
                cita.setId_paciente(objResult.getInt("id_paciente"));
                cita.setId_medico(objResult.getInt("is_medico"));

                citaList.add(cita);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de citas:\n" + citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;
    }

    private List<Cita> consultarPorHoraCita(String sql, String value) {
        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Cita cita = new Cita();

                cita.setId(objResult.getInt("id"));
                cita.setFecha_cita(objResult.getDate("fecha_cita").toLocalDate());
                cita.setHora_cita(objResult.getTime("hora_cita").toLocalTime());
                cita.setId_paciente(objResult.getInt("id_paciente"));
                cita.setId_medico(objResult.getInt("is_medico"));

                citaList.add(cita);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de citas:\n" + citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;
    }


    @Override
    public Cita actualizarCita(Cita cita) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL
            String sql = "UPDATE citas SET id_paciente = ?, id_medico = ?, fecha_cita = ?, hora_cita = ?, motivo = ? WHERE (id = ?);";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //5. Asignar los ?
            prepare.setInt(1, cita.getId_paciente());
            prepare.setInt(2, cita.getId_medico());
            prepare.setDate(3, Date.valueOf(cita.getFecha_cita()));
            prepare.setTime(4, Time.valueOf(cita.getHora_cita()));
            prepare.setString(5, cita.getMotivo());
            prepare.setInt(6, cita.getId());

            //6. Ejecutamos el Query
            prepare.execute();

            prepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return cita;
    }

    @Override
    public void eliminarCita(Integer id) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL y el prepare statement
            String sql = "DELETE FROM citas WHERE id;";
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
