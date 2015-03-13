
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * @author Csaba
 * Date: 09-Mar-2015
 * 
 * Class that extends DefaultTableCellRenderer. I personlized the table using this
 * class i.e. setting the background color of the rows and headers, setting background
 * color when 'hasFocus' or 'isSelected' etc.
 */
public class SurgeryTableRenderer extends DefaultTableCellRenderer {
    
    public SurgeryTableRenderer() {
        super();
    }
    
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
        
        //Align cell values to CENTER
        this.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return this;
    }
        

}
