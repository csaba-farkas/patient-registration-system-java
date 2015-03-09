
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
    private SurgeryFrame parent;
    
    public SurgeryDialog(SurgeryFrame parent, String title, int width, int height) {
        super(parent, title);
        this.parent = parent;
        this.getContentPane().add(getSurgeryDialog());
        pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int surgeryDialogWidth = this.getSize().width;
        int surgeryDialogHeight = this.getSize().height;
        int locationX = (dimension.width - surgeryDialogWidth)/2;
        int locationY = (dimension.height - surgeryDialogHeight)/2;
        this.setLocation(locationX, locationY);
        
        this.getRootPane().setDefaultButton(this.addButton);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
    }

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
        
        //Create panel that holds labels
        JPanel labelPanel = new JPanel();
        BoxLayout boxLayoutLabelPanel = new BoxLayout(labelPanel, BoxLayout.Y_AXIS);
        labelPanel.setLayout(boxLayoutLabelPanel);
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
        
        JPanel fieldPanel = new JPanel();
        BoxLayout boxLayoutFieldPanel = new BoxLayout(fieldPanel, BoxLayout.Y_AXIS);
        fieldPanel.setLayout(boxLayoutFieldPanel);
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        
        JPanel nameFieldWrapper = new JPanel();
        nameFieldWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
        this.nameField = new JTextField(20);
        this.nameField.getDocument().addDocumentListener(new SurgeryTextFieldListener());
        this.nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameFieldWrapper.add(this.nameField);
        
        fieldPanel.add(nameFieldWrapper);
        
        JPanel addressFieldWrapper = new JPanel();
        addressFieldWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 2));
        this.addressField = new JTextField(40);
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
        
        mainPanel.add(labelAndTextFieldsPanel);
        
        //Add buttons
        JPanel buttonPanel = new JPanel();
        this.addButton = new JButton(SurgeryDialog.ADD_BUTTON_LABEL);
        this.addButton.addActionListener(new AddButtonListener());
        this.addButton.setEnabled(false);
        this.cancelButton = new JButton(SurgeryDialog.CANCEL_BUTTON_LABEL);
        this.cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        buttonPanel.add(this.addButton);
        buttonPanel.add(this.cancelButton);
       
        mainPanel.add(buttonPanel);
        return mainPanel;
    }

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
