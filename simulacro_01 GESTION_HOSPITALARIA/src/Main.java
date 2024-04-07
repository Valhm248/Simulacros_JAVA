import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import database.ConfigDB;
import model.EspecialidadModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        /*ConfigDB.openConnection(); ESTO ES PARA COMPROBAR QUE SÍ SE HAYA ESTABLECIDO LA CONEXIÓN(si no lo hizo, voy a file, project ) */

        EspecialidadModel especialidadModel = new EspecialidadModel();
        EspecialidadController especialidadController = new EspecialidadController(especialidadModel);

        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    MENÚ
                                        
                    1. Menú especialidades
                    2. Menú médicos
                    3. Menú pacientes
                    4. Menú citas
                    5. Salir                   
                                        
                    Elige una opción
                                        
                    """);

            switch (option) {

                case "1":
                    String option2;

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                MENÚ ESPECIALIDADES
                                                                
                                1. Guardar nueva especialidad.
                                2. Consultar todos las especialidades.
                                3. Consultar por filtro
                                4. Actualizar lista especialidades
                                5. Eliminar especialidad
                                6. Salir
                                """);


                        switch (option2) {
                            case "1":
                                especialidadController.guardarEspecialidad();
                                break;


                            case "2":
                                especialidadController.consultar();
                                break;


                            case "3":
                                especialidadController.consultarPorFiltro();
                                break;


                            case "4":
                                especialidadController.actualizarEspecialidad();
                                break;


                            case "5":
                                especialidadController.eliminarEspecialidad();
                                break;


                        }
                    } while (!option2.equals("6"));
                    break;

                case "2":
                    String option3;
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                MENÚ MÉDICOS

                                1. Guardar nuevo médico
                                2. Consultar todos los médicos
                                3. Consultar por filtro
                                4. Actualizar lista médicos
                                5. Eliminar médico
                                6. Salir
                                                                
                                """);

                        switch (option3) {
                            case "1":
                                MedicoController.guardarMedico();
                                break;


                            case "2":
                                MedicoController.consultar();
                                break;


                            case "3":
                                MedicoController.consultarPorFiltro();
                                break;


                            case "4":
                                MedicoController.actualizarMedico();
                                break;


                            case "5":
                                MedicoController.eliminarMedico();
                                break;


                        }
                    } while (!option3.equals("6"));
                    break;

                case "3":
                    String option4;
                    do {
                        option4 = JOptionPane.showInputDialog("""
                                MENÚ PACIENTES

                                1. Guardar nuevo paciente
                                2. Consultar todos los pacientes
                                3. Consultar por filtro
                                4. Actualizar lista pacientes
                                5. Eliminar paciente
                                6. Salir
                                                                
                                """);

                        switch (option4) {
                            case "1":

                                PacienteController.guardarPaciente();
                                break;


                            case "2":
                                PacienteController.consultar();
                                break;


                            case "3":
                                PacienteController.consultarPorFiltro();
                                break;


                            case "4":
                                PacienteController.actualizarPaciente();
                                break;


                            case "5":
                                PacienteController.eliminarPaciente();
                                break;


                        }
                    } while (!option4.equals("6"));
                    break;

                case "4":
                    String option5;
                    do {
                        option5 = JOptionPane.showInputDialog("""
                                MENÚ CITAS

                                1. Guardar nueva cita
                                2. Consultar todos las citas
                                3. Consultar por filtro
                                4. Actualizar lista citas
                                5. Eliminar cita
                                6. Salir
                                                                
                                """);

                        switch (option5) {
                            case "1":
                                CitaController.guardarCita();
                                break;


                            case "2":
                                CitaController.consultar();
                                break;


                            case "3":
                                CitaController.consultarPorFiltro();
                                break;


                            case "4":
                                CitaController.actualizarCita();
                                break;


                            case "5":
                                CitaController.eliminarCita();
                                break;


                        }
                    } while (!option5.equals("6"));
                    break;

            }
        }while (!option.equals("5"));

    }
}

