package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Variable que va a contener el estado de la conexión
    static Connection connection = null;

    //Método para abrir la conexión entre Java y la base de datos

    public static Connection openConnection(){

        try {

            // Class.forName permite obtener o implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos variables con nuestras credenciales de la base de datos
            String url = "jdbc:mysql://bgtfrcc4ngqnbelzrsoo-mysql.services.clever-cloud.com:3306/bgtfrcc4ngqnbelzrsoo";
            String user = "uqqdkk2jdqkyabe8";
            String password = "O1V6Dwx6BqbYyFPrcF7W";

            //Establecemos la conexion
            connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Established connection");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed \n"+e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> A connection to the DB could not be established\n"+e.getMessage());
        }

        return connection;
    }


    public static void closeConnection(){

        //Si hay una conexión activa, la cerramos
        try{
            if (connection != null) connection.close();


            //
        }catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());
        }
    }
}
