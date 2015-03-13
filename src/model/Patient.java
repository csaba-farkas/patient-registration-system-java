
package model;

/*
 * @author Csaba
 * Date: 19-Feb-2015
 */

//Patient class implements Comparable interface. Patients are sorted alphabetically by their name
public abstract class Patient implements Comparable<Patient> {

    private String name;
    private String address;
    private String gender;
    
    public Patient(String name, String address, String gender) {
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Patient patient) {
        return this.getName().compareTo(patient.getName());
    }
    
    
}
