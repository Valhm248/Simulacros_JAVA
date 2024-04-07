package controller;


import entity.Airplane;
import entity.Flight;
import model.AirplaneModel;
import model.FlightModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlightController {


    FlightModel flightModel;
    AirplaneModel airplaneModel;

    public FlightController(FlightModel flightModel, AirplaneModel airplaneModel) {
        this.flightModel = new FlightModel();
        this.airplaneModel = new AirplaneModel();

    }

    public void delete(){

        List<Flight> flightList = flightModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the flight:\n" + flightList + "\nEnter the flight ID to delete\n"));
        flightList.stream().filter(flight -> flight.getId().equals(id)).findFirst().get();

        this.flightModel.delete(id);

    }

    public void update(){

        List<Flight> flightList = flightModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the flight:\n" + flightList + "\nEnter the flight ID to update\n"));
        Flight flightFilter = flightList.stream().filter(flight -> flight.getId().equals(id)).findFirst().get();



        String destination = JOptionPane.showInputDialog(null, "Enter the new destination\n: ", flightFilter.getDestination());
        LocalDate departureDate = LocalDate.parse(JOptionPane.showInputDialog(null, "Enter the departure date (YYYY-MM-DD)\n", flightFilter.getDepartureDate()));
        LocalTime departureTime = LocalTime.parse(JOptionPane.showInputDialog(null, "Enter the departure time (HH:00:SS)\n", flightFilter.getDepartureTime()));

        List<Airplane> airplaneList = airplaneModel.findAll();

        Object[] options = airplaneList.stream().map(Airplane::getModel).toArray();
        String selectedFilter =
                (String) JOptionPane.showInputDialog(null, "Select of the flight\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Airplane airplane = airplaneList.stream().filter(airplaneFilter -> airplaneFilter.getModel().equals(selectedFilter)).findFirst().get();

        Flight flight = new Flight();

        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);
        flight.setDepartureTime(departureTime);
        flight.setIdAirplane(airplane.getId());
        flight.setId(id);

        this.flightModel.update(flight);

        JOptionPane.showMessageDialog(null, "Flight data was updated correctly");

    }

    public void findByFilters() {

        String[] options = {"ID", "Destination", "Date", "Time", "Airplane"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select filter\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedFilter == "Airplane") {
            List<Airplane> airplaneList = airplaneModel.findAll();
            Object[] optionsAirplane = airplaneList.stream().map(Airplane::getModel).toArray();
            String selectedFilterAirplane = (String) JOptionPane.showInputDialog(null, "Select the airplane model\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsAirplane, optionsAirplane[0]);

            Airplane airplane = airplaneList.stream().filter(airplaneFilter -> airplaneFilter.getModel().equals(selectedFilterAirplane)).findFirst().get();

            List<Flight> flightList = this.flightModel.findByFilter(selectedFilter, String.valueOf(airplane.getId()));

        }else {

            String valueFilter = JOptionPane.showInputDialog(null, "Enter the requested data for the query\n");
            List<Flight> flightList = this.flightModel.findByFilter(selectedFilter, valueFilter);
        }



    }

    public void findAll() {

        List<Flight> flightList = this.flightModel.findAll();
        JOptionPane.showMessageDialog(null, "List of flight:\n" + flightList);
    }

    public void create() {

        Flight flight = new Flight();

        List<Airplane> airplaneList = airplaneModel.findAll();

        String destination = JOptionPane.showInputDialog(null, "Enter the destination flight\n");
        LocalDate departureDate = LocalDate.parse(JOptionPane.showInputDialog(null, "Enter the departure date (YYYY-MM-DD)\n"));
        LocalTime departureTime = LocalTime.parse(JOptionPane.showInputDialog(null, "Enter the departure time (HH:00:SS)\n"));

        Object[] options = airplaneList.stream().map(Airplane::getModel).toArray();
        String selectedFilter =
                (String) JOptionPane.showInputDialog(null, "Select of the flight\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Airplane airplane = airplaneList.stream().filter(airplaneFilter -> airplaneFilter.getModel().equals(selectedFilter)).findFirst().get();

        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);
        flight.setDepartureTime(departureTime);
        flight.setIdAirplane(airplane.getId());

        this.flightModel.create(flight);

    }

}
