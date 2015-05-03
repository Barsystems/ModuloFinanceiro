
package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Class_Renderer_Receitas_Despesas extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column);
        
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        Object COD = modelo.getValueAt(row, 8);
        String codigo = "";
        if (COD == null) {
            codigo = null;
        } else if (!COD.equals("")) {
            codigo = COD.toString();
        }
        
        Font fonteGrande = new Font("Arial", Font.PLAIN, 14);
        Font fonteNormal = new Font("Arial", Font.PLAIN, 12);
               
        c.setForeground(Color.BLACK);
        if (codigo == null) {
            c.setFont(fonteGrande);
            c.setBackground(Color.getHSBColor(50, 50, 50));
        } else {
            c.setFont(fonteNormal);
            c.setBackground(Color.WHITE);
        }
       
        if (isSelected) {
            c.setBackground(Color.decode("#CCFFFF"));
        }   

        return c;
    }
    
}
