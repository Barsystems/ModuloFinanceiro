
package centros_custo;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import usuarios.Class_Usuarios;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class Class_Centros_Custo {
    
    protected String id_centro_custo, nome, tipo, saldo;
    
    public Class_Centros_Custo() {
        
    }
    
    public String getIdCentroCusto() {
        return this.id_centro_custo;
    }
    
    public String getNomeCentroCusto() {
        return this.nome;
    }
    
    public String getTipoCentroCusto() {
        return this.tipo;
    }
    
    public String getSaldoCentroCusto() {
        return this.saldo;
    }
    
    public DefaultListModel refreshCaixas() {
        DefaultListModel lista = new DefaultListModel();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome FROM centros_custo WHERE tipo = 'Caixa' "
                    + "AND excluido = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.addElement(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public int retornaIdCentroCusto(String nome) {
        int centro_custo = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT id_centro_custo FROM centros_custo "
                    + "WHERE nome = '"+nome+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            centro_custo = rs.getInt(1);
            rs.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centro_custo;
    }
    
    public DefaultListModel carregaLista(){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT nome FROM centros_custo where excluido = 0 order by nome";  
           Class_Conexao_Banco banco = new Class_Conexao_Banco();
           Connection con = banco.getConexaoMySQL();
           PreparedStatement stmt = con.prepareStatement(sql);    
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               listModel.addElement(rs.getString(1));
            }              
            rs.close();  
            stmt.close();
            con.close();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listModel;
    }// FIM CARREGA LISTA
    
    public void Cadastra(String nome, String tipo){
        
        String sql = "INSERT INTO centros_custo(nome, tipo) VALUES (?, ?)";    
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            
            stmt.execute();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Centro de custo cadastrado com sucesso!");
            
        } catch (SQLException e) {    
            e.printStackTrace();  
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
        } 
    } // Fim Cadastra
    
    public void carregaCentroCusto(String nome){
        try{
            
            NumberFormat z = NumberFormat.getCurrencyInstance();
            
           String sql = "SELECT id_centro_custo, nome, tipo, saldo FROM centros_custo where excluido = 0 and nome = '"+nome+"'";  
           Class_Conexao_Banco banco = new Class_Conexao_Banco(); 
           Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);    
   
            ResultSet rs = stmt.executeQuery();              
            rs.next();
                    
            this.id_centro_custo = rs.getString(1);
            this.nome = rs.getString(2);
            this.tipo = rs.getString(3);
            this.saldo = z.format(rs.getFloat(4));
                        
            rs.close();  
            stmt.close();
            con.close();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    } // FIM CARREGA CENTROS CUSTO
    
    public void edita(String codigo, String nome) {
        
        String sql = "Update centros_custo set nome= '"+nome+"' WHERE id_centro_custo = "+codigo;
    
        try {   
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    

            stmt.executeUpdate();
                stmt.close();
                con.close();    
                
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e) {    
                e.printStackTrace();
        } 
    }
    
    public void exclui(String codigo){
        String sql = "UPDATE centros_custo set excluido = 1 where id_centro_custo = '"+codigo+"'";    
    
        try {    
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.execute();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Centro de custo excluído com sucesso!");
        } catch (SQLException e) {    
            e.printStackTrace();
        } 
    }
    
    public void getCentrosCusto(JTabbedPane painel_centros_custo, int id_usuario, String nome_usuario) {
        try {     
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            Class_Usuarios usuarios = new Class_Usuarios();
            String tipo_usuario = usuarios.getTipoUsuario(nome_usuario);
            if (tipo_usuario.equals("Administrador")) { 
                ImageIcon icon = null;
                Component component = null;
                String tip = null;
                
                ps = conn.prepareStatement("SELECT centros_custo.id_centro_custo, centros_custo.nome, "
                        + "centros_custo.tipo FROM centros_custo WHERE centros_custo.Excluido = 0");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("centros_custo.tipo").equals("Caixa")) {
                        icon = new ImageIcon(getClass().getResource("/imagens/Dinheiro 16px.png"));
                        Painel_Caixa caixa = new Painel_Caixa(rs.getInt("centros_custo.id_centro_custo"), rs.getString("centros_custo.nome"), id_usuario, nome_usuario);
                        component = caixa;
                        tip = "Gerencie seu caixa!";
                    } else {
                        icon = new ImageIcon(getClass().getResource("/imagens/Símbolo de dinheiro 16px.png"));
                        Painel_Conta_Bancaria conta_bancaria = new Painel_Conta_Bancaria(rs.getInt("centros_custo.id_centro_custo"), rs.getString("centros_custo.nome"), id_usuario, nome_usuario);
                        component = conta_bancaria;
                        tip = "Gerencie sua conta bancária!";
                    }
                    painel_centros_custo.addTab(rs.getString("centros_custo.nome")+"   ", icon, component, tip);
                }
            } else {
                ps = conn.prepareStatement("SELECT centros_custo.id_centro_custo, centros_custo.nome, "
                        + "centros_custo.tipo, responsaveis_caixa.id_usuario "
                        + "FROM centros_custo "
                        + "INNER JOIN responsaveis_caixa ON centros_custo.id_centro_custo = responsaveis_caixa.id_centro_custo "
                        + "WHERE centros_custo.Excluido = 0 AND responsaveis_caixa.id_usuario = '"+id_usuario+"' "
                        + "AND centros_custo.tipo = 'Caixa'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Painel_Caixa caixa = new Painel_Caixa(rs.getInt("centros_custo.id_centro_custo"), rs.getString("centros_custo.nome"), id_usuario, nome_usuario);
                    painel_centros_custo.addTab(rs.getString("centros_custo.nome")+"   ", new ImageIcon(getClass().getResource("/imagens/Dinheiro 16px.png")), caixa, "Gerencie seu caixa!");
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alteraSaldoCentroCusto(String tipo, String valorInicial, String valorAtual, int id_centro_custo) {
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float ValorInicial = troca.trocaVirgulaPorPonto(valorInicial);
        float ValorAtual = troca.trocaVirgulaPorPonto(valorAtual);
        
        if (tipo.equals("Receita")) {
            ValorAtual = ValorAtual - ValorInicial;
        } else {
            ValorAtual = ValorInicial - ValorAtual;
        }
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement("UPDATE centros_custo SET saldo = saldo + '"+ValorAtual+"' "
                    + "WHERE id_centro_custo = '"+id_centro_custo+"'");    
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {    
            e.printStackTrace();
        } 
    }
    
}
