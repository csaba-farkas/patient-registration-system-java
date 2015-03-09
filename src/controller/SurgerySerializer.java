
package controller;

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
            
            FileOutputStream fileOutputStream = new FileOutputStream("surgery.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.surgery);
            objectOutputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SurgerySerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
