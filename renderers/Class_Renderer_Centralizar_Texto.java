
package renderers;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Class_Renderer_Centralizar_Texto extends DefaultTableCellRenderer {
    
    public Class_Renderer_Centralizar_Texto() {  
        super();  
    }  
    
    public Component getTableCellRendererComponent(JTable table, Object value,  
            boolean isSelected, boolean hasFocus, int row, int column) {  
        this.setHorizontalAlignment(CENTER);
  
        return super.getTableCellRendererComponent(table, value, isSelected,  
                hasFocus, row, column);  
    }    
}
