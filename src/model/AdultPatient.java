
package model;

/*
 * @author Csaba
 * Date: 19-Feb-2015
 */
public class AdultPatient extends Patient {

    private String occupation;

    public AdultPatient(String name, String address, String gender, String occupation) {
        super(name, address, gender);
        this.occupation = occupation;
    }
}
