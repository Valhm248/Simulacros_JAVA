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
            String url = "jdbc:mysql://bulzzoad4pbj96kmhagb-mysql.services.clever-cloud.com:3306/bulzzoad4pbj96kmhagb";
            String user = "ul17xmzx4bouzzqa";
            String password = "87sXucJHygaRhkt5MyJI";


            //Establecemos la conexion
            connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver no instalados \n"+e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> La conexión a BD no fue establecida\n"+e.getMessage());
        }

        return connection;
    }


    public static void closeConnection(){

        //Si hay una conexión activa, la cerramos
        try{
            if (connection != null) connection.close();

        }catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());
        }
    }
}
