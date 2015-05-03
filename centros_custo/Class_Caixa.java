
package centros_custo;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import formas_pagamento.Class_Formas_Pagto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Class_Caixa {
    
    public Class_Caixa() {
        
    }
    
    public void retiraResponsavelCaixa(int id_centro_custo, int id_usuario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM responsaveis_caixa "
                    + "WHERE id_centro_custo = '"+id_centro_custo+"' AND id_usuario = '"+id_usuario+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void adicionaResponsavelCaixa(int id_centro_custo, int id_usuario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO responsaveis_caixa (id_centro_custo, id_usuario) "
                    + "VALUES (?, ?)");
            ps.setInt(1, id_centro_custo);
            ps.setInt(2, id_usuario);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DefaultListModel carregaResponsaveisCaixa(String nome_caixa) {
        DefaultListModel lista = new DefaultListModel();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT responsaveis_caixa.*, usuarios.nome, centros_custo.nome "
                    + "FROM responsaveis_caixa "
                    + "INNER JOIN usuarios ON usuarios.id_usuario = responsaveis_caixa.id_usuario "
                    + "INNER JOIN centros_custo ON centros_custo.id_centro_custo = responsaveis_caixa.id_centro_custo "
                    + "WHERE centros_custo.nome = '"+nome_caixa+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                lista.addElement(rs.getString("usuarios.nome"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public boolean verificaCaixaAberto(int id_caixa) {
        
        boolean flag = false;
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            String query = "select * from caixas where fechado = 0 and id_caixa = '"+id_caixa+"'";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public void carregaMovimentacoesCaixa(DefaultTableModel tabela, int id_caixa) {
        tabela.setRowCount(0);
        String valor;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        try {
            String query = "select movimentacoes_caixa.*, usuarios.nome, formas_pagamento.descricao "
                    + "from movimentacoes_caixa "
                    + "inner join usuarios on movimentacoes_caixa.id_usuario = usuarios.id_usuario "
                    + "inner join formas_pagamento on movimentacoes_caixa.id_forma_pagamento_fk = formas_pagamento.id_forma_pagamento "
                    + "where id_caixa = '"+id_caixa+"' and movimentacoes_caixa.excluido = 0";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                valor = nf.format(rs.getFloat("movimentacoes_caixa.valor"));
                
                tabela.addRow(new Object[] {
                    rs.getString("movimentacoes_caixa.descricao"),
                    rs.getString("formas_pagamento.descricao"),
                    valor,
                    rs.getString("movimentacoes_caixa.tipo"),
                    sdf.format(rs.getTimestamp("movimentacoes_caixa.data_pagamento")),
                    rs.getString("usuarios.nome"),
                    rs.getInt("movimentacoes_caixa.id_movimentacao_caixa")
                });
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void abrirCaixa(int id_centro_custo) {
        
        try 
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String dataAbertura = sdf.format(new Date());
            
            String query = "insert into caixas (data_abertura, id_centro_custo) values (?, ?)";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dataAbertura);
            ps.setInt(2, id_centro_custo);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public void registraMovimentacaoCaixa(int id_caixa, String descricao, String forma_pagamento, 
            int numero_parcelas, String valor, String tipo, int id_usuario, String data_pagamento) {
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        try 
        {
            String query = "insert into movimentacoes_caixa (id_caixa, descricao, id_forma_pagamento_fk, numero_parcelas,"
                    + "valor, tipo, data_pagamento, id_usuario) values (?, ?, ?, ?, ?, ?, ?, ?)";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_caixa);
            ps.setString(2, descricao);
            ps.setInt(3, id_forma_pagamento);
            ps.setInt(4, numero_parcelas);
            ps.setFloat(5, Valor);
            ps.setString(6, tipo);
            ps.setString(7, data_pagamento);
            ps.setInt(8, id_usuario);
            ps.executeUpdate();
            
            ps.close();
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public int getIdCaixa(int id_centro_custo) {
        
        int id_caixa = 0;
        
        try 
        {
            String query = "select id_caixa from caixas where fechado = 0 and id_centro_custo = '"+id_centro_custo+"'";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_caixa = rs.getInt("id_caixa");
            }
            rs.close();
            ps.close();
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return id_caixa;
    }
    
    public void fecharCaixa(int id_caixa) {
        
        try 
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            
            String query = "update caixas set data_fechamento = '"+sdf.format(new Date())+"', fechado = 1 "
                    + "where id_caixa = '"+id_caixa+"'";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Caixa fechado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public int getIdUltimaMovimentacaoCaixa() {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select max(id_movimentacao_caixa) as id from movimentacoes_caixa");
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
    
    public void alteraMovimentacaoCaixa(int id_movimentacao, String descricao, String forma_pagamento, String valor) {
        try {
            
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            float Valor = troca.trocaVirgulaPorPonto(valor);
            
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE movimentacoes_caixa SET "
                    + "descricao = '"+descricao+"', "
                    + "id_forma_pagamento_fk = '"+id_forma_pagamento+"', "
                    + "valor = '"+Valor+"' "
                    + "WHERE id_movimentacao_caixa = '"+id_movimentacao+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiMovimentacaoCaixa(int id_movimentacao) {
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE movimentacoes_caixa SET "
                    + "excluido = 1 "
                    + "WHERE id_movimentacao_caixa = '"+id_movimentacao+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaResponsavelCaixaAberto(int id_usuario) {
        
        int id_centro_custo = 0;
        boolean flag = false;
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT id_centro_custo FROM responsaveis_caixa "
                    + "WHERE id_usuario = '"+id_usuario+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_centro_custo = rs.getInt(1);
            }
            
            ps = con.prepareStatement("SELECT id_caixa FROM caixas WHERE id_centro_custo = '"+id_centro_custo+"' "
                    + "AND fechado = 0");
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
