
package view;

import controller.GUIInterface;
import model.SurgeryTableModel;
import controller.SurgeryController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/*
 * @author Csaba
 * Date: 20-Feb-2015
 */
public class SurgeryFrame extends JFrame implements GUIInterface {
    
    private static final String ADD_BUTTON_LABEL = "Add...";
    private static final String DELETE_BUTTON_LABEL = "Delete...";
    private static final String SAVE_AND_EXIT_BUTTON_LABEL = "Save&Exit";
    private static final String EXIT_BUTTON_LABEL = "Exit";
    
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveAndCloseButton;
    private JButton closeButton;
    private JTable patientTable;
    private SurgeryTableModel surgeryTableModel;
    
    
    public SurgeryFrame(String title) throws IOException, FileNotFoundException, ClassNotFoundException {
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
        this.setSize(800, 500);
        
        //Frame appears in the middle of the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      //Get the size of the screen
        int surgeryFrameWidth = this.getSize().width;
        int surgeryFrameHeigth = this.getSize().height;
        int locationX = (dimension.width - surgeryFrameWidth)/2;                //X coordinate = (width of screen - width of frame) / 2
        int locationY = (dimension.height - surgeryFrameHeigth)/2;              //Y coordinate = (heigth of screen - height of frame) / 2
        this.setLocation(locationX, locationY);
        
        this.setVisible(true);
        
    }
    
    //Getter method for patientTable
    private JTable getPatientTable() {
        return this.patientTable;
    }

      
    //This is the panel in the bottom with the three buttons
    private Component createSouthPanel() {
        
        JPanel southPanel = new JPanel();
        
        //Add button with actionListener that opens the 'Add new patient' dialog
        this.addButton = new JButton(SurgeryFrame.ADD_BUTTON_LABEL);
        this.addButton.addActionListener((ActionEvent e) -> {
            SurgeryDialog surgeryDialog = new SurgeryDialog(SurgeryFrame.this, "Add new patient", SurgeryFrame.this.getSize().width, SurgeryFrame.this.getSize().height);
        });
        
        //Delete button that opens a JOptionPane warning box
        this.deleteButton = new JButton(SurgeryFrame.DELETE_BUTTON_LABEL);
        this.deleteButton.setEnabled(false);
        this.deleteButton.addActionListener(new DeleteButtonActionListener());
        
        //Save&Close button that saves the progress and exits the program
        this.saveAndCloseButton = new JButton(SurgeryFrame.SAVE_AND_EXIT_BUTTON_LABEL);
        this.saveAndCloseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit?");
                if(answer != JOptionPane.NO_OPTION && answer != JOptionPane.CANCEL_OPTION) {
                    SurgeryController.getInstance().save();
                    dispose();
                }
            }
        });
        
        //Close button that closes the program without saving
        this.closeButton = new JButton(SurgeryFrame.EXIT_BUTTON_LABEL);
        this.closeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit without saving?");
                if(answer != JOptionPane.NO_OPTION && answer != JOptionPane.CANCEL_OPTION) {
                    dispose();
                }
            }
        });
        
        
        //Add buttons to panel and return panel
        southPanel.add(this.addButton);
        southPanel.add(this.deleteButton);
        southPanel.add(this.saveAndCloseButton);
        southPanel.add(this.closeButton);
        
        return southPanel;
    }

    //Create the JScrollPane that holds the table
    private JScrollPane createTableScrollPane() throws IOException, FileNotFoundException, ClassNotFoundException {
        
        SurgeryController.getInstance().open();
        this.surgeryTableModel = new SurgeryTableModel(SurgeryController.getInstance().getSurgeryModel().getPatients());
        
        this.patientTable = new JTable();
        
        this.patientTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(patientTable.getSelectedRow() != -1) {
                deleteButton.setEnabled(true);
            }
            else {
                deleteButton.setEnabled(false);
            }
        });
        //Get the table headers and set their background color to light blue
        JTableHeader tableHeader = this.patientTable.getTableHeader();
        tableHeader.setBackground(new Color(107, 161, 237));
        
        //The next part is the table renderer. I played around with the blackground colors
        this.patientTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
           
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                
                //Set the background color of every second row to a lighter blue than the header
                if(row%2 != 0) {
                    this.setBackground(new Color(205, 224, 250));
                }
                
                //Set the background color of the other rows to white
                else {
                    this.setBackground(Color.white);
                }
                
                //Set the background color of a row when selected to a light gray color
                if(isSelected) {
                    this.setBackground(new Color(184, 187, 191));
                }
                
                //Set the background color of the cell when it has focus to a darker gray color
                if(hasFocus) {
                    this.setBackground(new Color(123, 138, 135));
                }
                return this;
            }
        });
        
        //Add table model to table and table to JScrollPane, then return it at the end of the method
        this.patientTable.setModel(surgeryTableModel);
        JScrollPane tableScrollPane = new JScrollPane(this.patientTable);
        
        return tableScrollPane;
    }
    
    @Override
    public void refresh() {
        this.surgeryTableModel.fireTableDataChanged();
    }

    //Inner class: DeleteButtonActionListener
    private class DeleteButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Get index of the row that is selected (returns -1 if no row selected)
            int rowSelected = SurgeryFrame.this.getPatientTable().getSelectedRow();
            
            //If row selected, show confirm dialog and delete/cancel
            String warning = "Are you sure you want to delete a patient ?";
            int answer = JOptionPane.showConfirmDialog(rootPane, warning);

            if(answer == JOptionPane.YES_OPTION) {
                System.out.println("Patient deletion confirmed!");
                //Call the controller, to remove patient that was selected
                SurgeryController.getInstance().removeAdultPatient(SurgeryController.getInstance().getSurgeryModel().getPatientAtRowNumber(rowSelected));
                
                SurgeryController.getInstance().getGUIReference().refresh();
            }
            else if(answer == JOptionPane.NO_OPTION) {
                System.out.println("Patient deletion cancelled!");
            }
            
        }
    }
   
}
