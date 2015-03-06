
package controller;

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
    
    public void setSurgeryModel(Surgery surgery, GUIInterface gui) {
        this.surgeryModel = surgery;
        this.gui = gui;
    }
    
    public Surgery getDataModel() {
        return this.surgeryModel;
    } 
    
    //Add adult patient
    public void createAdultPatient(String name, String address, String gender, String occupation) {
        AdultPatient adultPatient = new AdultPatient(name, address, gender, occupation);
        this.surgeryModel.addPatient(adultPatient);       
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
    }

    
    
}
