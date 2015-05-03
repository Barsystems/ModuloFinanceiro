
package configuracoes;

import principal.Class_Consumir_Letras;
import principal.Class_Verifica_Menu_Aberto;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class Painel_Configuracoes extends javax.swing.JPanel {

    JTabbedPane painel_principal;
    String id_cartao;
    
    public Painel_Configuracoes(JTabbedPane painel_principal) {
        initComponents();
        this.painel_principal = painel_principal;
    }
    
    public void refreshList(String cartao) {
        
        Class_Configurar_Cartoes cartoes = new Class_Configurar_Cartoes();
        DefaultListModel lista = cartoes.carregaLista(cartao);
        listCartoes.setModel(lista);
        Configurar_Cartoes.setVisible(true);
    }
    
    public void refreshCampos() {
        
        Class_Configurar_Cartoes cartoes = new Class_Configurar_Cartoes();
        cartoes.carregaCartao((String) listCartoes.getSelectedValue());
        txtCartao.setText(cartoes.getCartao());
        txtTaxaCartao.setText(cartoes.getTaxa());
        txtDiasReceberCartao.setText(cartoes.getDiasReceber());
        id_cartao = cartoes.getIdCartao();
    }
    
    public void carregaEditar() {
        Class_Configurar_Cartoes cartoes = new Class_Configurar_Cartoes();
        cartoes.carregaCartao((String) listCartoes.getSelectedValue());
        txtCartao1.setText(cartoes.getCartao());
        txtTaxaCartao1.setText(cartoes.getTaxa().replace(" %", ""));
        txtDiasReceberCartao1.setText(cartoes.getDiasReceber().replace(" dias", ""));
        id_cartao = cartoes.getIdCartao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Configurar_Cartoes = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        lblCartoesDisponiveis = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCartao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTaxaCartao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiasReceberCartao = new javax.swing.JTextField();
        btnEditarCartao = new javax.swing.JButton();
        btnSairTelaCartao = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCartoes = new javax.swing.JList();
        Editar_Cartoes = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCartao1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTaxaCartao1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDiasReceberCartao1 = new javax.swing.JTextField();
        btnSalvarEdicaoCartao = new javax.swing.JButton();
        btnSairEdicaoCartao = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnConfigurarCartoes = new javax.swing.JButton();
        btnFecharConfiguracoes = new javax.swing.JButton();

        Configurar_Cartoes.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Configurar_Cartoes.setTitle("Manutenção de taxa e dias de recebimento dos cartões");
        Configurar_Cartoes.setModal(true);
        Configurar_Cartoes.setResizable(false);
        Configurar_Cartoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Configurar_CartoesMousePressed(evt);
            }
        });
        Configurar_Cartoes.getContentPane().setLayout(null);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cartões 24px.png"))); // NOI18N
        jLabel4.setText("Manutenção de cartões");
        Configurar_Cartoes.getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 30, 680, 30);

        lblCartoesDisponiveis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCartoesDisponiveis.setText("Cartões disponíveis");
        Configurar_Cartoes.getContentPane().add(lblCartoesDisponiveis);
        lblCartoesDisponiveis.setBounds(20, 100, 130, 17);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        jButton2.setToolTipText("Clique para pesquisar um cartão específico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Configurar_Cartoes.getContentPane().add(jButton2);
        jButton2.setBounds(230, 90, 40, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Cartão");
        Configurar_Cartoes.getContentPane().add(jLabel6);
        jLabel6.setBounds(350, 130, 50, 17);

        txtCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCartao.setToolTipText("");
        txtCartao.setEnabled(false);
        Configurar_Cartoes.getContentPane().add(txtCartao);
        txtCartao.setBounds(410, 120, 250, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Taxa");
        Configurar_Cartoes.getContentPane().add(jLabel7);
        jLabel7.setBounds(360, 170, 40, 17);

        txtTaxaCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTaxaCartao.setToolTipText("");
        txtTaxaCartao.setEnabled(false);
        Configurar_Cartoes.getContentPane().add(txtTaxaCartao);
        txtTaxaCartao.setBounds(410, 160, 250, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText(" Receber em");
        Configurar_Cartoes.getContentPane().add(jLabel8);
        jLabel8.setBounds(310, 210, 90, 17);

        txtDiasReceberCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiasReceberCartao.setToolTipText("");
        txtDiasReceberCartao.setEnabled(false);
        Configurar_Cartoes.getContentPane().add(txtDiasReceberCartao);
        txtDiasReceberCartao.setBounds(410, 200, 250, 30);

        btnEditarCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarCartao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        btnEditarCartao.setText("Editar");
        btnEditarCartao.setToolTipText("Editar a taxa ou os dias de recebimento de um cartão");
        btnEditarCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCartaoActionPerformed(evt);
            }
        });
        Configurar_Cartoes.getContentPane().add(btnEditarCartao);
        btnEditarCartao.setBounds(240, 410, 100, 30);

        btnSairTelaCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairTelaCartao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairTelaCartao.setText("Sair");
        btnSairTelaCartao.setToolTipText("Sair da tela de manutenção de cartões");
        btnSairTelaCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairTelaCartaoActionPerformed(evt);
            }
        });
        Configurar_Cartoes.getContentPane().add(btnSairTelaCartao);
        btnSairTelaCartao.setBounds(350, 410, 100, 30);

        listCartoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        listCartoes.setSelectionBackground(new java.awt.Color(204, 255, 255));
        listCartoes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listCartoes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listCartoesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listCartoes);

        Configurar_Cartoes.getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 120, 250, 230);

        Editar_Cartoes.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Editar_Cartoes.setTitle("Editar dados do cartão");
        Editar_Cartoes.setModal(true);
        Editar_Cartoes.getContentPane().setLayout(null);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cartões 24px.png"))); // NOI18N
        jLabel9.setText("Editar dados do cartão");
        Editar_Cartoes.getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 30, 520, 30);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Cartão");
        Editar_Cartoes.getContentPane().add(jLabel10);
        jLabel10.setBounds(120, 100, 50, 17);

        txtCartao1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCartao1.setEnabled(false);
        Editar_Cartoes.getContentPane().add(txtCartao1);
        txtCartao1.setBounds(180, 90, 250, 30);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Taxa");
        Editar_Cartoes.getContentPane().add(jLabel11);
        jLabel11.setBounds(130, 140, 40, 17);

        txtTaxaCartao1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTaxaCartao1.setToolTipText("Configure a taxa do cartão");
        txtTaxaCartao1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTaxaCartao1KeyTyped(evt);
            }
        });
        Editar_Cartoes.getContentPane().add(txtTaxaCartao1);
        txtTaxaCartao1.setBounds(180, 130, 250, 30);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText(" Receber em");
        Editar_Cartoes.getContentPane().add(jLabel12);
        jLabel12.setBounds(80, 180, 90, 17);

        txtDiasReceberCartao1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiasReceberCartao1.setToolTipText("Dias para o recebimento do cartão");
        txtDiasReceberCartao1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasReceberCartao1KeyTyped(evt);
            }
        });
        Editar_Cartoes.getContentPane().add(txtDiasReceberCartao1);
        txtDiasReceberCartao1.setBounds(180, 170, 250, 30);

        btnSalvarEdicaoCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarEdicaoCartao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarEdicaoCartao.setText("Salvar");
        btnSalvarEdicaoCartao.setToolTipText("Salvar alterações de taxa e dias de recebimento");
        btnSalvarEdicaoCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarEdicaoCartaoActionPerformed(evt);
            }
        });
        Editar_Cartoes.getContentPane().add(btnSalvarEdicaoCartao);
        btnSalvarEdicaoCartao.setBounds(170, 240, 100, 30);

        btnSairEdicaoCartao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairEdicaoCartao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairEdicaoCartao.setText("Sair");
        btnSairEdicaoCartao.setToolTipText("Cancelar alterações");
        btnSairEdicaoCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairEdicaoCartaoActionPerformed(evt);
            }
        });
        Editar_Cartoes.getContentPane().add(btnSairEdicaoCartao);
        btnSairEdicaoCartao.setBounds(280, 240, 100, 30);

        txtPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPesquisar.setText("Digite para pesquisar um cartão específico");
        txtPesquisar.setToolTipText("");
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Configurações 24px.png"))); // NOI18N
        jLabel1.setText("Configurações do sistema");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 710, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Configurar os dias de recebimento e");
        add(jLabel2);
        jLabel2.setBounds(20, 110, 240, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("a taxa de cada cartão de crédito e débito");
        add(jLabel3);
        jLabel3.setBounds(20, 130, 270, 17);

        btnConfigurarCartoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnConfigurarCartoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cartões 16px.png"))); // NOI18N
        btnConfigurarCartoes.setText("Configurar cartões");
        btnConfigurarCartoes.setToolTipText("Configure os cartões");
        btnConfigurarCartoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurarCartoesActionPerformed(evt);
            }
        });
        add(btnConfigurarCartoes);
        btnConfigurarCartoes.setBounds(20, 160, 170, 30);

        btnFecharConfiguracoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFecharConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnFecharConfiguracoes.setText("Sair");
        btnFecharConfiguracoes.setToolTipText("Fechar a tela de configurações");
        btnFecharConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharConfiguracoesActionPerformed(evt);
            }
        });
        add(btnFecharConfiguracoes);
        btnFecharConfiguracoes.setBounds(320, 340, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairTelaCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairTelaCartaoActionPerformed

        Configurar_CartoesMousePressed(null);
        Configurar_Cartoes.dispose();
        
    }//GEN-LAST:event_btnSairTelaCartaoActionPerformed

    private void btnConfigurarCartoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurarCartoesActionPerformed
        
        Class_Configurar_Cartoes cartoes = new Class_Configurar_Cartoes();
        listCartoes.setModel(cartoes.carregaLista(""));
        Configurar_Cartoes.setBounds(0, 0, 690, 510);
        Configurar_Cartoes.setLocationRelativeTo(null);
        Configurar_Cartoes.setVisible(true);
        
    }//GEN-LAST:event_btnConfigurarCartoesActionPerformed

    private void btnFecharConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharConfiguracoesActionPerformed
        
        Class_Verifica_Menu_Aberto verifica = new Class_Verifica_Menu_Aberto();
        int index = verifica.verificaMenuAberto(painel_principal, "Configurações do sistema   ");
        painel_principal.remove(index);
        
    }//GEN-LAST:event_btnFecharConfiguracoesActionPerformed

    private void btnEditarCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCartaoActionPerformed
        
        String cartao = (String) listCartoes.getSelectedValue();
        if (cartao == null) {
            JOptionPane.showMessageDialog(null, "Selecione um cartão para alterar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            carregaEditar();
            Editar_Cartoes.setBounds(0, 0, 550, 340);
            Editar_Cartoes.setLocationRelativeTo(null);
            Editar_Cartoes.setVisible(true);
            txtTaxaCartao1.grabFocus();
        }
        
    }//GEN-LAST:event_btnEditarCartaoActionPerformed

    private void listCartoesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listCartoesValueChanged
        
        refreshCampos();
        
    }//GEN-LAST:event_listCartoesValueChanged

    private void btnSairEdicaoCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairEdicaoCartaoActionPerformed
        
        Editar_Cartoes.dispose();
        
    }//GEN-LAST:event_btnSairEdicaoCartaoActionPerformed

    private void btnSalvarEdicaoCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarEdicaoCartaoActionPerformed
        
        if(txtCartao1.getText().equals("") ||
            txtTaxaCartao1.getText().equals("") ||
            txtDiasReceberCartao1.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Nao pode haver campos vazios!", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
        else{
            Class_Configurar_Cartoes cartoes = new Class_Configurar_Cartoes();
            boolean result = cartoes.edita(id_cartao,
                txtTaxaCartao1.getText(),
                txtDiasReceberCartao1.getText());
            if(result)
            {
                JOptionPane.showMessageDialog(null,"Produto editado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                refreshList("");
                Editar_Cartoes.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Falha ao editar produto!", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }//GEN-LAST:event_btnSalvarEdicaoCartaoActionPerformed

    private void txtTaxaCartao1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaxaCartao1KeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890,.%", evt);
        
    }//GEN-LAST:event_txtTaxaCartao1KeyTyped

    private void txtDiasReceberCartao1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasReceberCartao1KeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890", evt);
        
    }//GEN-LAST:event_txtDiasReceberCartao1KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Configurar_Cartoes.add(txtPesquisar);
        lblCartoesDisponiveis.setVisible(false);
        txtPesquisar.setBounds(20, 90, 190, 30);
        txtPesquisar.setText("");
        txtPesquisar.grabFocus();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        
        refreshList(txtPesquisar.getText());
        
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void Configurar_CartoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Configurar_CartoesMousePressed
        
        if (!lblCartoesDisponiveis.isVisible()) {
            Configurar_Cartoes.remove(txtPesquisar);
            lblCartoesDisponiveis.setVisible(true);
            refreshList("");
            Configurar_Cartoes.repaint();
        }
        
    }//GEN-LAST:event_Configurar_CartoesMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Configurar_Cartoes;
    private javax.swing.JDialog Editar_Cartoes;
    private javax.swing.JButton btnConfigurarCartoes;
    private javax.swing.JButton btnEditarCartao;
    private javax.swing.JButton btnFecharConfiguracoes;
    private javax.swing.JButton btnSairEdicaoCartao;
    private javax.swing.JButton btnSairTelaCartao;
    private javax.swing.JButton btnSalvarEdicaoCartao;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCartoesDisponiveis;
    private javax.swing.JList listCartoes;
    private javax.swing.JTextField txtCartao;
    private javax.swing.JTextField txtCartao1;
    private javax.swing.JTextField txtDiasReceberCartao;
    private javax.swing.JTextField txtDiasReceberCartao1;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtTaxaCartao;
    private javax.swing.JTextField txtTaxaCartao1;
    // End of variables declaration//GEN-END:variables
}
