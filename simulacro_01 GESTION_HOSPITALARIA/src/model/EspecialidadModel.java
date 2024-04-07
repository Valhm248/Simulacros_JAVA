package model;

import database.CRUDEspecialidad;
import database.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EspecialidadModel implements CRUDEspecialidad {
    @Override
    public Especialidad guardarEspecialidad(Especialidad especialidad) {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear SQL
            String sql = "INSERT INTO especialidades(nombre, descripcion) VALUES(?,?)";

            //4.Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los ?
            prepare.setString(1, especialidad.getNombre());
            prepare.setString(2, especialidad.getDescripcion());

            //6.Ejecutamos el Query
            prepare.execute();

            //7. Obtener el resultado
            ResultSet result = prepare.getGeneratedKeys();
            while (result.next()){
                especialidad.setId(result.getInt(1));
            }

            //8.Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "Especialidad creada");


        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear especialidad" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();


        return especialidad;
    }

    @Override
    public List<Especialidad> consultar() {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        List<Especialidad> especialidadList = new ArrayList<>();

        try {
            //3. Crear el SQL
            String sql = "SELECT * FROM especialidades ORDER BY especialidades.id ASC;";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //6. Ejecutamos el Query
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()){

                //Creamos una instancias
                Especialidad especialidad = new Especialidad();

                //Llenamos nuestros objeto con lo que devuelve la base de datos
                especialidad.setId(resultSet.getInt("id"));
                especialidad.setNombre(resultSet.getString("nombre"));
                especialidad.setDescripcion(resultSet.getString("descripcion"));

                //Finalmente agregar a la lista
                especialidadList.add(especialidad);
            }

            //8. Cerramos el prepareStatement
            prepare.close();
            JOptionPane.showMessageDialog(null, "¡Lista de especialidades exitosa!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mostrar lista");
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return especialidadList;
    }

    @Override
    public List<Especialidad> consultarPorFiltro(String filter, String value) {
        Connection connection = ConfigDB.openConnection();

        String sql;

        List<Especialidad> especialidadList = new ArrayList<>();

        try {

            if(Objects.equals(filter, "ID")){
                sql = "SELECT * FROM Especialiades WHERE id = ?;";

                especialidadList = consultarPorId(sql, value);
            }

            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM Especialidades WHERE nombre LIKE ?;";

                especialidadList = consultarPorNombre(sql, value);
            }
            if (Objects.equals(filter, "Apellido")) {

                sql = "SELECT * FROM Especialidades WHERE apellido LIKE ?;";

                especialidadList = consultarPorApellido(sql, value);
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al aplicar el filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return especialidadList;
    }



    public List<Especialidad> consultarPorId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Especialidad> especialidadList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Especialidad especialidad = new Especialidad();

                especialidad.setId(objResult.getInt("id"));
                especialidad.setNombre(objResult.getString("nombre"));
                especialidad.setDescripcion(objResult.getString("descripcion"));


                especialidadList.add(especialidad);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de especialidades: " + especialidadList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar filtros " + e.getMessage());
        }

        return especialidadList;

    }

    private List<Especialidad> consultarPorNombre(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Especialidad> especialidadList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Especialidad especialidad = new Especialidad();

                especialidad.setId(objResult.getInt("id"));
                especialidad.setNombre(objResult.getString("nombre"));
                especialidad.setDescripcion(objResult.getString("descripcion"));


                especialidadList.add(especialidad);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de especialidades: " + especialidadList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al  mostrar filtros " + e.getMessage());

        }

        return especialidadList;

    }


    private List<Especialidad> consultarPorApellido(String sql, String value) {
        Connection connection = ConfigDB.openConnection();
        List<Especialidad> especialidadList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Especialidad especialidad = new Especialidad();

                especialidad.setId(objResult.getInt("id"));
                especialidad.setNombre(objResult.getString("nombre"));
                especialidad.setDescripcion(objResult.getString("descripcion"));


                especialidadList.add(especialidad);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de especialidades: " + especialidadList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al  mostrar filtros " + e.getMessage());

        }

        return especialidadList;
    }


    @Override
    public Especialidad actualizarEspecialidad(Especialidad especialidad) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //3. Crear el SQL
            String sql = "UPDATE especialidades SET nombre = ?, descripcion = ? WHERE (id = ?);";

            //4. Preparar el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //5. Asignar los ?
            prepare.setString(1, especialidad.getNombre());
            prepare.setString(2, especialidad.getDescripcion());
            prepare.setInt(3, especialidad.getId());

            //6. Ejecutamos el Query
            prepare.execute();

            prepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage());
        }

        //9. Cerramos la conexión
        ConfigDB.closeConnection();

        return especialidad;
    }

    @Override
    public void eliminarEspecialidad(Integer id) {

        //Abrir conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //Crear el SQL y el prepare statement
            String sql = "DELETE FROM especialidades WHERE id = ?;";
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
