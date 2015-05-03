
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Class_Conta_Bancaria {
    
    public Class_Conta_Bancaria() {
        
    }
    
    public void carregaMovimentacoesContaBancaria(DefaultTableModel tabela, int id_conta_bancaria, Date data_inicio, 
            Date data_fim) {
        tabela.setRowCount(0);
        String valor;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dataInicio = sdf.format(data_inicio);
        String dataFim = sdf.format(data_fim);
        sdf.applyPattern("dd/MM/yyyy HH:mm");
        
        try {
            String query = "select movimentacoes_conta_bancaria.*, usuarios.nome, formas_pagamento.descricao "
                    + "from movimentacoes_conta_bancaria "
                    + "inner join usuarios on movimentacoes_conta_bancaria.id_usuario = usuarios.id_usuario "
                    + "inner join formas_pagamento on movimentacoes_conta_bancaria.id_forma_pagamento_fk = formas_pagamento.id_forma_pagamento "
                    + "where id_centro_custo = '"+id_conta_bancaria+"' and movimentacoes_conta_bancaria.excluido = 0 "
                    + "and movimentacoes_conta_bancaria.data_pagamento between '"+dataInicio+" 00:00:00' "
                    + "and '"+dataFim+" 23:59:59'";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                valor = nf.format(rs.getFloat("movimentacoes_conta_bancaria.valor"));
                
                tabela.addRow(new Object[] {
                    rs.getString("movimentacoes_conta_bancaria.descricao"),
                    rs.getString("formas_pagamento.descricao"),
                    valor,
                    rs.getString("movimentacoes_conta_bancaria.tipo"),
                    sdf.format(rs.getTimestamp("movimentacoes_conta_bancaria.data_pagamento")),
                    rs.getString("usuarios.nome"),
                    rs.getInt("movimentacoes_conta_bancaria.id_movimentacao_conta_bancaria")
                });
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void registraMovimentacaoContaBancaria(int id_conta_bancaria, String descricao, String forma_pagamento, 
            int numero_parcelas, String valor, String tipo, int id_usuario, String data_pagamento) {
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        try 
        {
            String query = "insert into movimentacoes_conta_bancaria (id_centro_custo, descricao, id_forma_pagamento_fk, numero_parcelas,"
                    + "valor, tipo, data_pagamento, id_usuario) values (?, ?, ?, ?, ?, ?, ?, ?)";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_conta_bancaria);
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
    
    public int getIdUltimaMovimentacaoContaBancaria() {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select max(id_movimentacao_conta_bancaria) as id from movimentacoes_conta_bancaria");
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
    
    public void alteraMovimentacaoContaBancaria(int id_movimentacao, String descricao, String forma_pagamento, String valor) {
        try {
            
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            float Valor = troca.trocaVirgulaPorPonto(valor);
            
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE movimentacoes_conta_bancaria SET "
                    + "descricao = '"+descricao+"', "
                    + "id_forma_pagamento_fk = '"+id_forma_pagamento+"', "
                    + "valor = '"+Valor+"' "
                    + "WHERE id_movimentacao_conta_bancaria = '"+id_movimentacao+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiMovimentacaoContaBancaria(int id_movimentacao) {
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE movimentacoes_conta_bancaria SET "
                    + "excluido = 1 "
                    + "WHERE id_movimentacao_conta_bancaria = '"+id_movimentacao+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
