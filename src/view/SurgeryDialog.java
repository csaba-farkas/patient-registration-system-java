
package view;

import controller.SurgeryController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/*
 * @author Csaba
 * Date: 04-Mar-2015
 */
public class SurgeryDialog extends JDialog {
    //Static final variables for labels
    private static final String NAME_LABEL = "Name: ";
    private static final String ADDRESS_LABEL = "Address: ";
    private static final String GENDER_LABEL= "Gender: ";
    private static final String ADD_BUTTON_LABEL = "Add";
    private static final String CANCEL_BUTTON_LABEL = "Cancel";
    
    //Instance variables
    private JTextField nameField, addressField, genderField;
    private JButton addButton, cancelButton;
    
    
    public SurgeryDialog(SurgeryFrame parent, String title, int width, int height) {
        super(parent, title);                   
        
        //I created a wrapper JPanel called mainPanel that contains all the other elements.
        JPanel mainPanel = new JPanel();
        mainPanel.add(getSurgeryDialog());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        this.getContentPane().add(mainPanel);
        //pack() command sets the size of the window as big as the elements inside require
        pack();
        
        //Position the dialog in the center of the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int surgeryDialogWidth = this.getSize().width;
        int surgeryDialogHeight = this.getSize().height;
        int locationX = (dimension.width - surgeryDialogWidth)/2;
        int locationY = (dimension.height - surgeryDialogHeight)/2;
        this.setLocation(locationX, locationY);
        
        //The next line sets the 'Add' button to 'default button'. This means that
        //the add button has an 'Enter' key listener so we can 'press' the 'Add' button
        //by hitting 'Enter'.
        this.getRootPane().setDefaultButton(this.addButton);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }

    //Method that creates and returns the main panel
    private JPanel getSurgeryDialog() {
        
        //mainPanel is the main container, layout is set to BoxLayout.Y_AXIS
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayoutMainPanel = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayoutMainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        //labelAndTextFieldsPanel is the second container, it contains two panels:
        //labelPanel for the labels and fieldPanel for the textfields
        //Layout here is set to BoxLayout.X_AXIS
        JPanel labelAndTextFieldsPanel = new JPanel();
        BoxLayout boxLayoutLabelAndTextFieldsPanel = new BoxLayout(labelAndTextFieldsPanel, BoxLayout.X_AXIS);
        labelAndTextFieldsPanel.setLayout(boxLayoutLabelAndTextFieldsPanel);
        
        //Create panel that holds labels. Layout is BoxlLayout and elements are placed vertically (Y_AXIS)
        JPanel labelPanel = new JPanel();
        BoxLayout boxLayoutLabelPanel = new BoxLayout(labelPanel, BoxLayout.Y_AXIS);
        labelPanel.setLayout(boxLayoutLabelPanel);
        
        //Labels are added individually with some padding in between them
        JLabel nameLabel = new JLabel(SurgeryDialog.NAME_LABEL);
        labelPanel.add(nameLabel);
        labelPanel.add(Box.createVerticalStrut(7));
        nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel addressLabel = new JLabel(SurgeryDialog.ADDRESS_LABEL);
        labelPanel.add(addressLabel);
        labelPanel.add(Box.createVerticalStrut(7));
        addressLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel genderLabel = new JLabel(SurgeryDialog.GENDER_LABEL);
        labelPanel.add(genderLabel);
        genderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        labelAndTextFieldsPanel.add(labelPanel);
        
        //The next panel is containing all of the JTextFields. Its layout is also set to
        //boxLayout with the elements aligned vertically.
        JPanel fieldPanel = new JPanel();
        BoxLayout boxLayoutFieldPanel = new BoxLayout(fieldPanel, BoxLayout.Y_AXIS);
        fieldPanel.setLayout(boxLayoutFieldPanel);
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        
        //All of the JTextField objects are wrapped into a 'dummy' JPanel because the layout
        //of the outer panels made them stretch horizontally. These 'dummy' JPanels are
        //insulating them.
        JPanel nameFieldWrapper = new JPanel();
        nameFieldWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
        this.nameField = new JTextField(20);
        this.nameField.getDocument().addDocumentListener(new SurgeryTextFieldListener());
        this.nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameFieldWrapper.add(this.nameField);
        
        fieldPanel.add(nameFieldWrapper);
        
        JPanel addressFieldWrapper = new JPanel();
        addressFieldWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 2));
        this.addressField = new JTextField(30);
        this.addressField.getDocument().addDocumentListener(new SurgeryTextFieldListener());
        this.addressField.setAlignmentX(Component.LEFT_ALIGNMENT);
        addressFieldWrapper.add(this.addressField);
               
        fieldPanel.add(addressFieldWrapper);
        
        
        JPanel genderFieldWrapper = new JPanel();
        genderFieldWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 2));
        this.genderField = new JTextField(5);
        this.genderField.getDocument().addDocumentListener(new SurgeryTextFieldListener());
        this.genderField.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderFieldWrapper.add(this.genderField);
        
        fieldPanel.add(genderFieldWrapper);
        
        labelAndTextFieldsPanel.add(fieldPanel);
        labelAndTextFieldsPanel.setBorder(BorderFactory.createTitledBorder(""));
        
        mainPanel.add(labelAndTextFieldsPanel);
        //////// End of construction of panel holding labels and fields //////////////
        
        //And finally I added the buttons to a new panel that's layout is left to 
        //default FlowLayout, center aligned.
        JPanel buttonPanel = new JPanel();
        this.addButton = new JButton(SurgeryDialog.ADD_BUTTON_LABEL);           
        this.addButton.addActionListener(new AddButtonListener());              //AddButtonListener inner class is added to 'Add' button
        this.addButton.setEnabled(false);
        this.cancelButton = new JButton(SurgeryDialog.CANCEL_BUTTON_LABEL);     //ActionListener is added to 'Cancel' button to dispose window is pressed
        this.cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        buttonPanel.add(this.addButton);
        buttonPanel.add(this.cancelButton);
       /////////// End of constructing panel holding buttons ////////////////////
        
        mainPanel.add(buttonPanel);
        return mainPanel;
    }

    /*
    The next inner class implements the DocumentListener interface. The 'Add' button on the
    JDialog is disabled on default and it only gets enabled when all of the JTextFields contain
    some data. DocumentListener interface contains 3 functions, all of them are catching DocumentEvents.
    They are fired if there is update inserted (user writes something into field), update removed (user
    deletes something from a field) or update changed (user changes the contents of a field).
    All of the functions are firing the same method calle
    */
    private class SurgeryTextFieldListener implements DocumentListener{

        public SurgeryTextFieldListener() {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            toggleAdd();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            toggleAdd();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            toggleAdd();
        }
        
        //toglaAdd() function has an array containing all of the JTextField objects.
        //If method is called, it enables 'Add' button only and only if all of the fields 
        //contain some data (whitespaces are not considered as data)
        private void toggleAdd() {
            
            boolean enabled = true;
            JTextField[] jTextFields = {nameField, addressField, genderField};
            
            for(JTextField field : jTextFields) {
                if(field.getText().trim().isEmpty()) {
                    enabled = false;
                    break;
                }
            }
            addButton.setEnabled(enabled);
        }

    }

    /*
    The ActionListener that is attached to the 'Add' buton.
    This class parses the data from the JTextFields and by calling the controller,
    a new patient is created. Then the GUI is refreshed, so the table can display
    the new patient.
    */
    private class AddButtonListener implements ActionListener {

        public AddButtonListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String name = nameField.getText();
            String address = addressField.getText();
            String gender = genderField.getText();
            
            SurgeryController.getInstance().createAdultPatient(name, address, gender, "");            
            SurgeryController.getInstance().getGUIReference().refresh();
            
            dispose();
            
        }
    }

}
