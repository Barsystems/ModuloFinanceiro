
package configuracoes;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

public class Class_Configurar_Cartoes {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected String id_cartao, cartao, taxa, dias_receber;

    public Class_Configurar_Cartoes() {
    }
    
    public String getIdCartao() {
        return this.id_cartao;
    }
    
    public String getCartao() {
        return this.cartao;
    }
    
    public String getTaxa() {
        return this.taxa;
    }
    
    public String getDiasReceber() {
        return this.dias_receber;
    }
    
    public DefaultListModel carregaLista(String cartao) {
        
        DefaultListModel lista = new DefaultListModel();
        
        try 
        {
            Connection con = banco.getConexaoMySQL();
            String sql = "SELECT formas_pagamento.descricao "
                    + "FROM formas_pagamento "
                    + "INNER JOIN cartoes ON formas_pagamento.id_forma_pagamento = cartoes.id_forma_pagamento "
                    + "WHERE formas_pagamento.excluido = 0 AND formas_pagamento.descricao LIKE '%"+cartao+"%' order by formas_pagamento.descricao";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                lista.addElement(rs.getString(1));
            }
            
            rs.close();
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista;
    }
    
    public void carregaCartao(String cartao) {
        
        try 
        {
            Connection con = banco.getConexaoMySQL();
            String SQL = "select formas_pagamento.descricao, cartoes.id_cartao, cartoes.taxa, cartoes.dias_receber "
                    + "from formas_pagamento "
                    + "inner join cartoes on formas_pagamento.id_forma_pagamento = cartoes.id_forma_pagamento "
                    + "where formas_pagamento.descricao = '"+cartao+"'";
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while(rs.next()) 
            {
                this.id_cartao = rs.getString(2);
                this.cartao = rs.getString(1);
                this.taxa = rs.getString(3) + " %";
                this.dias_receber = rs.getString(4) + " dias";
            }
            
            rs.close();
            st.close();
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public boolean edita(
            String codigo, 
            String taxa, 
            String dias_receber) {
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        
        String sql = "Update cartoes set taxa='"+troca.trocaVirgulaPorPonto(taxa)+""
                +"',dias_receber='"+dias_receber+"' WHERE id_cartao = "+codigo;
    
            try {    
                Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);    
                
    
                stmt.executeUpdate();
                    stmt.close();
                    con.close();
                    return true;
                
                    
                }
             catch (SQLException u) {    
                throw new RuntimeException(u);   
                
        } 
    }
    
}
