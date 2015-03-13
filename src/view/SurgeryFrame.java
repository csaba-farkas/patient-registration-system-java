
package view;

import controller.GUIInterface;
import model.SurgeryTableModel;
import controller.SurgeryController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.JTableHeader;

/*
 * @author Csaba
 * Date: 20-Feb-2015
 */
public class SurgeryFrame extends JFrame implements GUIInterface {
    
    private static final String ADD_BUTTON_LABEL = "Add...";
    private static final String DELETE_BUTTON_LABEL = "Delete...";
    private static final String EXIT_BUTTON_LABEL = "Exit";
    
    private JButton addButton;
    private JButton deleteButton;
    private JButton closeButton;
    private JTable patientTable;
    private SurgeryTableModel surgeryTableModel;
    
    
    public SurgeryFrame(String title) {
        super(title);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        
        ////////// Add panel with buttons //////////////////////
        mainPanel.add(createSouthPanel(), BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel);
        ///////////////////////////////////////////////////////
        
        ////////// Add table ///////////////////////////////////
        JScrollPane tableScrollPane = createTableScrollPane();
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(125, 137, 153), 3));
        ////////////////////////////////////////////////////////
               
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900, 600);
        
        //Frame appears in the middle of the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      //Get the size of the screen
        int surgeryFrameWidth = this.getSize().width;
        int surgeryFrameHeigth = this.getSize().height;
        int locationX = (dimension.width - surgeryFrameWidth)/2;                //X coordinate = (width of screen - width of frame) / 2
        int locationY = (dimension.height - surgeryFrameHeigth)/2;              //Y coordinate = (heigth of screen - height of frame) / 2
        this.setLocation(locationX, locationY);
        
        this.setVisible(true);
        
    }
    
    //Getter method returns patientTable
    private JTable getPatientTable() {
        return this.patientTable;
    }

    //This is the panel in the bottom with the four buttons
    private Component createSouthPanel() {
        
        JPanel southPanel = new JPanel();
        
        //Add button with actionListener that opens the 'Add new patient' dialog
        this.addButton = new JButton(SurgeryFrame.ADD_BUTTON_LABEL);
        this.addButton.addActionListener((ActionEvent e) -> {
            SurgeryDialog surgeryDialog = new SurgeryDialog(SurgeryFrame.this, "Add new patient", SurgeryFrame.this.getSize().width, SurgeryFrame.this.getSize().height);
        });
        
        //Delete button
        this.deleteButton = new JButton(SurgeryFrame.DELETE_BUTTON_LABEL);
        this.deleteButton.setEnabled(false);
        this.deleteButton.addActionListener(new DeleteButtonActionListener());  //Add inner class ActionListener to Delete button
        
        //Close button that saves the progress and exits the program
        this.closeButton = new JButton(SurgeryFrame.EXIT_BUTTON_LABEL);
        this.closeButton.addActionListener(new ActionListener() {

            //Users must confirm if they want to exit
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit?");
                if(answer != JOptionPane.NO_OPTION && answer != JOptionPane.CANCEL_OPTION) {
                    dispose();
                }
            }
        });
        
        //Add buttons to southPanel and return southPanel
        southPanel.add(this.addButton);
        southPanel.add(this.deleteButton);
        southPanel.add(this.closeButton);
        
        return southPanel;
    }

    //Create the JScrollPane that holds the table
    private JScrollPane createTableScrollPane() {
        
        /*
        The instructions in the assignment required the creation of 5 patients and add them to the table.
        Instead of that, I made the 'Add' button functional. In this version, only adult patients are created.
        */
        
        //create a table model with ArrayList<Patient> passed by controller
        this.surgeryTableModel = new SurgeryTableModel(SurgeryController.getInstance().getSurgeryModel().getPatients());
        
        this.patientTable = new JTable();
        
        /*
        The 'Delete...' buttton is disablesd by default.
        If user selects a row, button is enabled.
        */
        this.patientTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(patientTable.getSelectedRow() != -1) {
                deleteButton.setEnabled(true);
            }
            else {
                deleteButton.setEnabled(false);
            }
        });
        
        //Get the table headers and set their background color to light blue and change font to bold
        JTableHeader tableHeader = this.patientTable.getTableHeader();
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD));
        tableHeader.setBackground(new Color(107, 161, 237));
        
        //I created a customized DefaultTableCellRenderer class that is added to the table
        this.patientTable.setDefaultRenderer(Object.class, new SurgeryTableRenderer());
        
        //Add table model to table and table to JScrollPane, then return it at the end of the method
        this.patientTable.setModel(surgeryTableModel);
        JScrollPane tableScrollPane = new JScrollPane(this.patientTable);
        
        return tableScrollPane;
    }
    
    //This is the GUI interface method
    @Override
    public void refresh() {
        this.surgeryTableModel.fireTableDataChanged();
    }

    //Inner class: DeleteButtonActionListener
    private class DeleteButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Get index of the row that is selected
            int rowSelected = SurgeryFrame.this.getPatientTable().getSelectedRow();
            
            //If row selected, show confirm dialog and delete/cancel
            String warning = "Are you sure you want to delete a patient ?";
            int answer = JOptionPane.showConfirmDialog(rootPane, warning);

            if(answer == JOptionPane.YES_OPTION) {
                System.out.println("Patient deletion confirmed!");
                //Call the controller, to remove patient that was selected
                SurgeryController.getInstance().removePatient(SurgeryController.getInstance().getSurgeryModel().getPatientAtRowNumber(rowSelected));
                
                SurgeryController.getInstance().getGUIReference().refresh();
            }
            else if(answer == JOptionPane.NO_OPTION) {
                System.out.println("Patient deletion cancelled!");
            }
            
        }
    }
   
}
