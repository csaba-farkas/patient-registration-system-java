
package model;

import java.io.Serializable;

/*
 * @author Csaba
 * Date: 19-Feb-2015
 */
public class ChildPatient extends Patient implements Serializable {
    
    String nameOfSchool;
    
    public ChildPatient(String name, String address, String gender, String nameOfSchool) {
        super(name, address, gender);
        this.nameOfSchool = nameOfSchool;
    }

}
