import controller.AirplaneController;
import controller.FlightController;
import controller.PassengerController;
import controller.ReservationController;
import database.ConfigDB;
import model.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ConfigDB.openConnection();

        AirplaneModel airplaneModel = new AirplaneModel();
        AirplaneController airplaneController = new AirplaneController(airplaneModel);

        PassengerModel passengerModel = new PassengerModel();;
        PassengerController passengerController = new PassengerController(passengerModel);

        FlightModel flightModel = new FlightModel();
        FlightController flightController = new FlightController(flightModel, airplaneModel);

        SeatModel seatModel = new SeatModel();

        ReservationModel reservationModel = new ReservationModel();
        ReservationController reservationController = new ReservationController(reservationModel,  passengerModel, flightModel, seatModel);

        String option;

        do {

            option = JOptionPane.showInputDialog("""
                    Welcome to Back Flight select the desired option:
                                        
                    1. Airplane Manager
                    2. Flight Manager
                    3. Passenger Administrator
                    4. Reservation Manager
                    5. Exit.
                    """);

            switch (option) {
                case "1":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Welcome to Back Flight!
                                                                
                                Airplane Manager select the desired option:
                                                                
                                1. Create a new airplane.
                                2. Filter airplane.
                                3. Update airplane.
                                4. Delete airplane.
                                5. Show all airplane.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                airplaneController.create();
                                break;
                            case "2":
                                airplaneController.findByFilters();
                                break;
                            case "3":
                                airplaneController.update();
                                break;
                            case "4":
                                airplaneController.delete();
                                break;
                            case "5":
                                airplaneController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "2":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Welcome to Back Flight!
                                                                
                                Flight Manager select the desired option:
                                                                
                                1. Create a new Flight.
                                2. Filter Flight.
                                3. Update Flight.
                                4. Delete Flight.
                                5. Show all Flight.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                flightController.create();
                                break;
                            case "2":
                                flightController.findByFilters();
                                break;
                            case "3":
                                flightController.update();
                                break;
                            case "4":
                                flightController.delete();
                                break;
                            case "5":
                                flightController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "3":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Welcome to Back Flight!
                                                                
                                Passenger Administrator select the desired option:
                                                                
                                1. Create a new Passenger.
                                2. Filter Passenger.
                                3. Update Passenger.
                                4. Delete Passenger.
                                5. Show all Passenger.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                passengerController.create();
                                break;
                            case "2":
                                passengerController.findByFilter();
                                break;
                            case "3":
                                passengerController.update();
                                break;
                            case "4":
                                passengerController.delete();
                                break;
                            case "5":
                                passengerController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "4":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Welcome to Back Flight!
                                                                
                                Reservation Manager select the desired option:
                                                                
                                1. Create a new Reservation.
                                2. Filter Reservation.
                                3. Update Reservation.
                                4. Delete Reservation.
                                5. Show all Reservation.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                reservationController.create();
                                break;
                            case "2":
                                reservationController.findByFilters();
                                break;
                            case "3":
                                reservationController.update();
                                break;
                            case "4":
                                //reservationController.delete();
                                break;
                            case "5":
                                reservationController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
            }

        } while (!option.equals("5"));

    }
}