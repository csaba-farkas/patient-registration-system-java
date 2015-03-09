
package model;

import java.io.Serializable;

/*
 * @author Csaba
 * Date: 19-Feb-2015
 */
public class AdultPatient extends Patient implements Serializable {

    private String occupation;

    public AdultPatient(String name, String address, String gender, String occupation) {
        super(name, address, gender);
        this.occupation = occupation;
    }
}
