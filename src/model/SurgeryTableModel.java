package model;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


/*
 * @author Csaba
 * Date: 05-Mar-2015
 */
public class SurgeryTableModel extends DefaultTableModel {

    //Final variables
    private static final int NUMBER_OF_COLUMNS = 3;     //Name, address, gender
    
    //Column indicies
    private static final int NAME_COL = 0;
    private static final int ADDRESS_COL = 1;
    
        
    ArrayList<Patient> patients;
    
    public SurgeryTableModel(ArrayList<Patient> patients) {
        super();
        this.patients = patients;
    }
           
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
    
    @Override
    public int getColumnCount() {
        return NUMBER_OF_COLUMNS;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == NAME_COL) {
            return "Name";
        }
        else if(columnIndex == ADDRESS_COL) {
            return "Address";
        }
        else {
            return "Gender";
        }
    }
    
    @Override
    public int getRowCount() {
        if(this.patients != null) {
            return this.patients.size();
        }
        else {
            return 0;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Patient currentPatient = this.patients.get(row);
        
        if(column == NAME_COL) {
            return currentPatient.getName();
        }
        else if(column == ADDRESS_COL) {
            return currentPatient.getAddress();
        }
        else {
            return currentPatient.getGender();
        }
    }
}
