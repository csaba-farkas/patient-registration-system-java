
import controller.SurgeryController;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.Surgery;
import view.SurgeryFrame;


/*
 * @author Csaba
 * Date: 20-Feb-2015
 */
public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        /*
        Main class contains only four lines of code.
            1. Surgery data model is created
            2. Controller sets the data model of the application
            3. A gui is created
            4. Controller sets the gui of the application
        */
        Surgery dataModel = new Surgery();
        
        SurgeryController.getInstance().setSurgeryModel(dataModel);
        
        SurgeryFrame gui = new SurgeryFrame("Patient Database");
        
        SurgeryController.getInstance().setGUIReference(gui);
        
        
    }
}
