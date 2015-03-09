
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import model.AdultPatient;
import model.ChildPatient;
import model.Patient;
import model.Surgery;

/*
 * @author Csaba
 * Date: 05-Mar-2015
 */
public class SurgeryController {

    private static SurgeryController instance;
    
    public static SurgeryController getInstance() {
        if(instance == null) {
            instance = new SurgeryController();
        }
        
        return instance;
    }
    
    private Surgery surgeryModel;
    private GUIInterface gui;
    
    public SurgeryController() {
        
    }
    
    public void setSurgeryModel(Surgery surgery) {
        this.surgeryModel = surgery;
    }
    
    public Surgery getSurgeryModel() {
        return this.surgeryModel;
    } 
    
    
    //Add adult patient
    public void createAdultPatient(String name, String address, String gender, String occupation) {
        AdultPatient adultPatient = new AdultPatient(name, address, gender, occupation);
        this.surgeryModel.addPatient(adultPatient); 
        this.gui.refresh();
    }
    
    public void setGUIReference(GUIInterface gui) {
        this.gui = gui;
    }
    
    public GUIInterface getGUIReference() {
        return this.gui;
    }
    public void removeAdultPatient(Patient patient) {
        this.surgeryModel.removePatient(patient);
        
    }
    
    //Add child patient
    public void createChildPatient(String name, String address, String gender, String nameOfSchool) {
        ChildPatient childPatient = new ChildPatient(name, address, gender, nameOfSchool);
        this.surgeryModel.addPatient(childPatient);
        this.gui.refresh();
    }

    //Save file
    public void save() {
        SurgerySerializer surgerySerializer = new SurgerySerializer(this.surgeryModel);
        try {
            surgerySerializer.writeToFile();
        } catch (IOException ex) {
            System.out.println("'dat' file was not found");
            System.out.println(ex.getMessage());
        }
    }
    
    //Open file
    public void open() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        FileInputStream fileInputStream = new FileInputStream("c:\\Users\\Csaba\\Documents\\NetBeansProjects\\OOP2 - Assignment 1\\src\\data\\surgery.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
        Surgery patients = (Surgery) objectInputStream.readObject();
        
        
        objectInputStream.close();
        fileInputStream.close();
        
        if(patients != null) {
           this.surgeryModel = patients;
        }
            
        }
        
        
    }
    
    

