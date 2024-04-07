package controller;


import entity.*;
import model.FlightModel;
import model.PassengerModel;
import model.ReservationModel;
import model.SeatModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservationController {

    ReservationModel reservationModel;

    PassengerModel passengerModel;

    FlightModel flightModel;

    SeatModel seatModel;

    public ReservationController(ReservationModel reservationModel, PassengerModel passengerModel, FlightModel flightModel, SeatModel seatModel) {
        this.reservationModel = reservationModel;
        this.passengerModel = passengerModel;
        this.flightModel = flightModel;
        this.seatModel = seatModel;
    }

    public void delete(){

        List<Reservation> reservationList = reservationModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the reservation:\n" + reservationList + "\nEnter the reservation ID to delete\n"));
        reservationList.stream().filter(reservation -> reservation.getId().equals(id)).findFirst().get();

        this.flightModel.delete(id);

    }

    public void update(){

        Reservation reservation = new Reservation();

        List<Passenger> passengerList = passengerModel.findAll();
        List<Flight> flightList = flightModel.findAll();
        List<Reservation> reservations = reservationModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the reservations:\n" + reservations + "\nEnter the reservation ID to update\n"));
        Reservation reservationsFilter = reservations.stream().filter(reservation1 -> reservation1.getId().equals(id)).findFirst().get();


        Object[] optionsPassenger = passengerList.stream().map(Passenger::getNamePassenger).toArray();
        String selectedFilterPassenger =
                (String) JOptionPane.showInputDialog(null, "Select passenger\n\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsPassenger, optionsPassenger[0]);

        Object[] optionsFlight = flightList.stream().map(Flight::getDestination).toArray();
        String selectedFilterFlight =
                (String) JOptionPane.showInputDialog(null, "Select destination\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlight, optionsFlight[0]);
        Flight flight = flightList.stream().filter(flightFilter -> flightFilter.getDestination().equals(selectedFilterFlight)).findFirst().get();

        Object[] optionsFlightDate = {flight.getDepartureDate()};
        String selectedFilterFlightDate =
                String.valueOf(JOptionPane.showInputDialog(null, "Select departure date\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlightDate, optionsFlightDate[0]));

        Object[] optionsFlightTime = {flight.getDepartureTime()};
        String selectedFilterFlightTime =
                String.valueOf(JOptionPane.showInputDialog(null, "Select departure time\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlightTime, optionsFlightTime[0]));

        Passenger passenger = passengerList.stream().filter(passengerFilter -> passengerFilter.getNamePassenger().equals(selectedFilterPassenger)).findFirst().get();

        List<Seat> seatList = seatModel.findAllByIdFlight(flight.getId());

        Object[] optionsSeat = seatList.stream().map(Seat::getSeatCode).toArray();
        Object selectedFilterSeat =
                JOptionPane.showInputDialog(null, "Select availability seat\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsSeat, optionsSeat[0]);

        Seat seat = seatList.stream().filter(seatFilter -> seatFilter.getSeatCode().equals(selectedFilterSeat)).findFirst().get();


        reservation.setReservationDate(selectedFilterFlightDate);
        reservation.setReservationTime(selectedFilterFlightTime);
        reservation.setIdPassenger(passenger.getId());
        reservation.setIdFlight(flight.getId());
        reservation.setSeat(seat.getSeatCode());
        reservation.setIdSeat(seat.getId());
        reservation.setId(reservationsFilter.getId());

        this.reservationModel.update(reservation);

        JOptionPane.showMessageDialog(null, "Reservation data was updated correctly");

    }

    public void findByFilters() {

        String[] options = {"ID", "Date", "Time", "Passenger", "Flight"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select filter\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedFilter == "Passenger") {

            List<Passenger> passengerList = passengerModel.findAll();
            Object[] optionsPassenger = passengerList.stream().map(Passenger::getNamePassenger).toArray();
            String selectedFilterPassenger = (String) JOptionPane.showInputDialog(null, "Select the passenger\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsPassenger, optionsPassenger[0]);

            Passenger passenger = passengerList.stream().filter(passengerFilter -> passengerFilter.getNamePassenger().equals(selectedFilterPassenger)).findFirst().get();

            List<Reservation> reservationList = this.reservationModel.findByFilter(selectedFilter, String.valueOf(passenger.getId()));

        } if (selectedFilter == "Flight") {

            List<Flight> flightList = flightModel.findAll();
            Object[] optionsFlight = flightList.stream().map(Flight::getDestination).toArray();
            String selectedFilterFlight = (String) JOptionPane.showInputDialog(null, "Select the Flight\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlight, optionsFlight[0]);

            Flight flight = flightList.stream().filter(flightFilter -> flightFilter.getDestination().equals(selectedFilterFlight)).findFirst().get();

            List<Reservation> reservationList = this.reservationModel.findByFilter(selectedFilter, String.valueOf(flight.getId()));

        } else {

            String valueFilter = JOptionPane.showInputDialog(null, "Enter the requested data for the query\n");
            List<Reservation> reservationList = this.reservationModel.findByFilter(selectedFilter, valueFilter);
        }


    }

    public void findAll() {

        List<Reservation> reservationList = this.reservationModel.findAll();
        JOptionPane.showMessageDialog(null, "List of reservation:\n" + reservationList);
    }

    public void create() {

        Reservation reservation = new Reservation();

        List<Passenger> passengerList = passengerModel.findAll();
        List<Flight> flightList = flightModel.findAll();

        Object[] optionsPassenger = passengerList.stream().map(Passenger::getNamePassenger).toArray();
        String selectedFilterPassenger =
                (String) JOptionPane.showInputDialog(null, "Select passenger\n\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsPassenger, optionsPassenger[0]);

        Object[] optionsFlight = flightList.stream().map(Flight::getDestination).toArray();
        String selectedFilterFlight =
                (String) JOptionPane.showInputDialog(null, "Select destination\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlight, optionsFlight[0]);
        Flight flight = flightList.stream().filter(flightFilter -> flightFilter.getDestination().equals(selectedFilterFlight)).findFirst().get();

        Object[] optionsFlightDate = {flight.getDepartureDate()};
        String selectedFilterFlightDate =
                String.valueOf(JOptionPane.showInputDialog(null, "Select departure date\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlightDate, optionsFlightDate[0]));

        Object[] optionsFlightTime = {flight.getDepartureTime()};
        String selectedFilterFlightTime =
                String.valueOf(JOptionPane.showInputDialog(null, "Select departure time\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlightTime, optionsFlightTime[0]));

        Passenger passenger = passengerList.stream().filter(passengerFilter -> passengerFilter.getNamePassenger().equals(selectedFilterPassenger)).findFirst().get();

        List<Seat> seatList = seatModel.findAllByIdFlight(flight.getId());

        Object[] optionsSeat = seatList.stream().map(Seat::getSeatCode).toArray();
        Object selectedFilterSeat =
                JOptionPane.showInputDialog(null, "Select availability seat\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsSeat, optionsSeat[0]);

        Seat seat = seatList.stream().filter(seatFilter -> seatFilter.getSeatCode().equals(selectedFilterSeat)).findFirst().get();


        reservation.setReservationDate(selectedFilterFlightDate);
        reservation.setReservationTime(selectedFilterFlightTime);
        reservation.setIdPassenger(passenger.getId());
        reservation.setIdFlight(flight.getId());
        reservation.setSeat(seat.getSeatCode());
        reservation.setIdSeat(seat.getId());


        this.reservationModel.create(reservation);

    }

}
