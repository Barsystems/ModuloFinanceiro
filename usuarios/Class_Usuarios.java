
package usuarios;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Class_Usuarios {
    
    protected String nome, tipo, senha;
    protected int id_usuario;
    
    public Class_Usuarios() {
        
    }
    
    public Class_Usuarios(String nome, String tipo, String senha) {
        this.nome = nome;
        this.tipo = tipo;
        this.senha = senha;
    }
    
    public int getIdUsuario() {
        return id_usuario;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void carregaUsuariosNormaisComboBox(javax.swing.JComboBox combo_usuario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome, tipo FROM usuarios WHERE excluido = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!rs.getString(2).equals("Administrador")) {
                    combo_usuario.addItem(rs.getString(1));
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DefaultListModel carregaLista(String nome) {
        DefaultListModel lista = new DefaultListModel();
        try 
        {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String sql = "SELECT nome from usuarios WHERE excluido = 0 AND nome LIKE '%"+nome+"%' order by nome";
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
    
    public void carregaUsuario(String nome) {
        
        try 
        {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String SQL = "select id_usuario, nome, senha, tipo from usuarios where nome = '"+nome+"'";
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while(rs.next()) 
            {
                this.id_usuario = rs.getInt(1);
                this.nome = rs.getString(2);
                this.senha = rs.getString(3);
                this.tipo = rs.getString(4);
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
    
    public boolean verificaCadastroExistente(String nome) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement("SELECT nome FROM usuarios WHERE nome = '"+nome+"' "
                    + "AND excluido = 0");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public boolean cadastra(String nome, String senha, String tipo) {
        
        String sql = "INSERT INTO usuarios(nome, senha, tipo) VALUES(?,?,?)";    
    
        try {    
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.setString(1, nome);
            stmt.setString(2, senha);    
            stmt.setString(3, tipo);    

            if(!stmt.execute()){
                stmt.close();
                con.close();
                return false;
            }
            else{
                stmt.execute();
                stmt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!");
                return true;
            }
        } catch (SQLException u) {    
            throw new RuntimeException(u);
        }
        
    }
    
    public int getIdUsuario(String nome_usuario) {
        int id_usuario = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "select id_usuario from usuarios where nome = '"+nome_usuario+"' and excluido = 0";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_usuario = rs.getInt("id_usuario");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_usuario;
    }
    
    public String getTipoUsuario(String nome) {
        String tipo = null;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "select tipo from usuarios where nome = '"+nome+"' and excluido = 0";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipo = rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }
    
    public boolean verificaSenhaAdministrador(String senha) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "select senha from usuarios where nome = 'Administrador' and senha = '"+senha+"' "
                    + "and excluido = 0";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
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
    
    public boolean pedeSenhaAdministrador(String nome_usuario) {
        boolean flag = false;
        if (getTipoUsuario(nome_usuario).equals("Administrador")) {
            String senha = JOptionPane.showInputDialog(null, "Digite a senha do administrador!", "Atenção", JOptionPane.PLAIN_MESSAGE);
            if (verificaSenhaAdministrador(senha) == true) {
                flag = true;
            }
        }
        return flag;
    }
    
    public void alteraUsuario(String nome_usuario, String senha, int id_usuario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "UPDATE usuarios SET nome = '"+nome_usuario+"', senha = '"+senha+"' "
                    + "WHERE id_usuario = '"+id_usuario+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiUsuario(int id_usuario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "UPDATE usuarios SET excluido = 1 WHERE id_usuario = '"+id_usuario+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
