
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Surgery;

/*
 * @author Csaba
 * Date: 09-Mar-2015
 */
public class SurgerySerializer {
    
    private Surgery surgery;
    
    public SurgerySerializer(Surgery surgery) {
        
        this.surgery = surgery;
        
    }
    
    public void writeToFile() throws IOException {
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream("c:\\Users\\Csaba\\Documents\\NetBeansProjects\\OOP2 - Assignment 1\\src\\data\\surgery.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.surgery);
            objectOutputStream.close();
            fileOutputStream.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        }
    }

}
