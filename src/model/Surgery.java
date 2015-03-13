
package model;

import java.util.ArrayList;

/*
 * @author Csaba
 * Date: 05-Mar-2015
 */
public class Surgery {
      
    private ArrayList<Patient> patients;
    
    public Surgery() {
        this.patients = new ArrayList<Patient>();
    }

    //Public methods: get patients; and add patient to the list; remove a patient from the list
    public ArrayList<Patient> getPatients() {
        return this.patients;
    }
    
    public Patient getPatientAtRowNumber(int rowNumber) {
        return this.patients.get(rowNumber);
    }
    
    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }
    
    public void removePatient(Patient patient) {
        int indexOfPatient = this.patients.indexOf(patient);
        this.patients.remove(indexOfPatient);
    }
}
