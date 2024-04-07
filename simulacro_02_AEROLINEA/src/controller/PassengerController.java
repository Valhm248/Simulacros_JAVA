package controller;

import entity.Passenger;
import model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {

    PassengerModel passengerModel;

    public PassengerController(PassengerModel passengerModel) {
        this.passengerModel = passengerModel;
    }

    public void delete(){
        JOptionPane.showMessageDialog(null, "List of passengers: " + passengerModel.findAll());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the id of passenger to delete"));

        this.passengerModel.delete(id);
    }

    public void update(){
        JOptionPane.showMessageDialog(null, "List of passengers: " + passengerModel.findAll());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the id: "));

        String update_name = JOptionPane.showInputDialog(null, "Insert new name: ");
        String update_lastName = JOptionPane.showInputDialog(null, "Insert new last name: ");
        String update_identityCard = JOptionPane.showInputDialog(null, "Insert new identity card: ");

        Passenger passenger = new Passenger();

        passenger.setNamePassenger(update_name);
        passenger.setLastname(update_lastName);
        passenger.setIdentityCard(update_identityCard);
        passenger.setId(id);

        this.passengerModel.update(passenger);

        JOptionPane.showMessageDialog(null, "Updated successfully");
    }
    public void findByFilter(){

        String[] options = {"ID" ,"Name passenger", "Last name", "Identity card" };

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select the filter\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Insert necccesary information\n" + "(ID passenger, name passenger, lastName, identity card");

        List<Passenger> listPassenger = this.passengerModel.findByFilter(selectedFilter, valueFilter);
    }

    public void findAll(){


        List<Passenger> passengerList;

        passengerList = this.passengerModel.findAll();

        JOptionPane.showMessageDialog(null, "List of passengers: \n" + passengerList);
    }

    public void create() {

        Passenger passenger = new Passenger();

        String name_passenger = JOptionPane.showInputDialog(null, "Insert name of passenger: ");
        String lastname = JOptionPane.showInputDialog(null, "Insert last name of passenger: ");
        String identity_card = JOptionPane.showInputDialog(null, "Insert identity card: ");

        passenger.setNamePassenger(name_passenger);
        passenger.setLastname(lastname);
        passenger.setIdentityCard(identity_card);

        passenger = this.passengerModel.create(passenger);

        JOptionPane.showMessageDialog(null, "Passenger created succesfully \n" + passenger.toString());

    }
}
