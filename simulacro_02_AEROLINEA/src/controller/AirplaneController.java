package controller;

import entity.Airplane;
import model.AirplaneModel;

import javax.swing.*;
import java.util.List;

public class AirplaneController {

    AirplaneModel airplaneModel;

    public AirplaneController(AirplaneModel airplaneModel){
         this.airplaneModel = new AirplaneModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of airplanes: \n" + airplaneModel.findAll() + "\nEnter the id of the airplane to delete"));

        this.airplaneModel.delete(id);

    }

    public void update(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the airplane:\n" + airplaneModel.findAll() + "\nEnter the airplane ID to update\n"));

        String model = JOptionPane.showInputDialog(null, "Enter the new airplane model\n: ");
        Integer capacity = Integer.valueOf(JOptionPane.showInputDialog(null, "Enter the airplane capacity\n: "));

        Airplane airplane = new Airplane();

        airplane.setModel(model);
        airplane.setCapacity(capacity);
        airplane.setId(id);

        this.airplaneModel.update(airplane);

        JOptionPane.showMessageDialog(null, "Aircraft data was updated correctly");

    }

    public void findByFilters(){

        String[] options = {"ID" , "model", "Capacity"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select the type of filter\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Enter the requested data for the query \n" + "(The airplane ID, Airplane model, Airplane capacity)");

        List<Airplane> airplaneList = this.airplaneModel.findByFilter(selectedFilter, valueFilter);
    }

    public void findAll(){

        List<Airplane> list = this.airplaneModel.findAll();
        JOptionPane.showMessageDialog(null, list);
    }

    public void create(){

        Airplane airplane = new Airplane();

        String model = JOptionPane.showInputDialog(null, "Enter the airplane model\n");
        Integer capacity = Integer.valueOf(JOptionPane.showInputDialog(null, "Enter the airplane capacity\n"));

        airplane.setModel(model);
        airplane.setCapacity(capacity);

        airplane = (Airplane) this.airplaneModel.create(airplane);

    }


}
