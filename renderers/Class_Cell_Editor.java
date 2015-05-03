
package renderers;  

import com.toedter.calendar.JDateChooser;  
import java.awt.Component;    
import java.awt.Font;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import javax.swing.AbstractCellEditor;  
import javax.swing.JTable;  
import javax.swing.table.TableCellEditor;  

public class Class_Cell_Editor extends AbstractCellEditor implements  
        TableCellEditor {  
  
    private JDateChooser dateChooser = new JDateChooser();  
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
  
    public Component getTableCellEditorComponent(JTable table, Object value,  
            boolean isSelected, int row, int column) {  
  
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 12));
        
        Date date = null;  
        //if (value instanceof Date) 
        try {
            date = formato.parse(value.toString());  
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        dateChooser.setDate(date);  
  
        return dateChooser;  
    }  
  
    public Object getCellEditorValue() {  
                
        return formato.format(dateChooser.getDate());  
    }  
}  