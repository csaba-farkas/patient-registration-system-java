
package model;

/*
 * @author Csaba
 * Date: 19-Feb-2015
 */
public class ChildPatient extends Patient {
    
    String nameOfSchool;
    
    public ChildPatient(String name, String address, String gender, String nameOfSchool) {
        super(name, address, gender);
        this.nameOfSchool = nameOfSchool;
    }

}
