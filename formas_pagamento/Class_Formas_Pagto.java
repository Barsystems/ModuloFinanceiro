
package formas_pagamento;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Class_Formas_Pagto {
    
    public Class_Formas_Pagto() {
        
    }
    
    public void carregaFormasPagamento(javax.swing.JComboBox combo) {
        combo.removeAllItems();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select descricao "
                    + "from formas_pagamento where excluido = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("descricao"));
            }
            combo.setSelectedIndex(0);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int retornaDiasCartao(String forma_pagamento) {
        int dias = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select dias_receber from cartoes "
                    + "where id_forma_pagamento = (select id_forma_pagamento from formas_pagamento "
                    + "where descricao = '"+forma_pagamento+" and excluido = 0')");
            ResultSet rs = ps.executeQuery();
            rs.next();
            dias = rs.getInt("dias_receber");
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dias;
    }
    
    public int retornaIdFormaPagamento(String forma) {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select id_forma_pagamento from formas_pagamento "
                    + "where descricao = '"+forma+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
}
