package forms;

import beans.Produto;
import beans.Usuario;
import beans.Venda;
import dao.ProdutoJpaController;
import dao.VendaJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormPrincipal extends javax.swing.JFrame {
    
    private final ProdutoJpaController produtoController;
    private final VendaJpaController vendaController;
    private final EntityManagerFactory emf;
    private final Usuario usuarioLogado;

    public FormPrincipal(Usuario usuarioLogado) {
        initComponents();
        emf = Persistence.createEntityManagerFactory("CantinaPU");
        produtoController = new ProdutoJpaController(emf);
        vendaController = new VendaJpaController(emf);
        this.usuarioLogado = usuarioLogado;
        atualizarTabelaProdutos();
        atualizarTabelaVendas();
    }
    
    public final void atualizarTabelaProdutos() {
        DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
        model.setRowCount(0);

        List<Produto> produtos = produtoController.findProdutoEntities();
        for (Produto p : produtos) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValorUnitario(),
                p.getQuantidadeEstoque()
            });
        }
    }

    public final void atualizarTabelaVendas() {
        DefaultTableModel model = (DefaultTableModel) tblVendas.getModel();
        model.setRowCount(0);
        List<Venda> vendas = vendaController.findVendaEntities();
        for (Venda v : vendas) {
            model.addRow(new Object[]{
                v.getId(),
                v.getVendedor().getNome(),
                v.getData(),
                v.getValorTotal()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btnCadastrarProdutos = new javax.swing.JButton();
        BtnAlterarProduto = new javax.swing.JButton();
        btnAlterarEstoque = new javax.swing.JButton();
        btnCadastrarVenda = new javax.swing.JButton();
        btnDetalhesVenda = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtIDVenda = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Produto", "Nome", "Valor Unitario", "Estoque"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        tblVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Venda", "Vendedor", "Data e Hora", "Valor Total"
            }
        ));
        jScrollPane2.setViewportView(tblVendas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Cadastros Gerais");

        btnCadastrarProdutos.setText("Cadastrar Produtos");
        btnCadastrarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarProdutosActionPerformed(evt);
            }
        });

        BtnAlterarProduto.setText("Alterar Produtos");
        BtnAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarProdutoActionPerformed(evt);
            }
        });

        btnAlterarEstoque.setText("Alterar Estoque");
        btnAlterarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarEstoqueActionPerformed(evt);
            }
        });

        btnCadastrarVenda.setText("Cadastrar Venda");
        btnCadastrarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarVendaActionPerformed(evt);
            }
        });

        btnDetalhesVenda.setText("Detalhes Venda");
        btnDetalhesVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalhesVendaActionPerformed(evt);
            }
        });

        jLabel2.setText("ID Venda:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAlterarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAlterarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(btnCadastrarProdutos)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAlterarProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(btnAlterarEstoque))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrarVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDetalhesVenda)
                            .addComponent(jLabel2)
                            .addComponent(txtIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarProdutosActionPerformed
        FormCadastroProduto formCadastroProduto = new FormCadastroProduto(this, null);
        formCadastroProduto.setVisible(true);
    }//GEN-LAST:event_btnCadastrarProdutosActionPerformed

    private void BtnAlterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarProdutoActionPerformed
        int selectedRow = tblProdutos.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um produto na tabela para alterar.", "Nenhum Produto Selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer idProduto = (Integer) tblProdutos.getValueAt(selectedRow, 0);

        FormCadastroProduto formCadastroProduto = new FormCadastroProduto(this, idProduto);
        formCadastroProduto.setVisible(true);
    }//GEN-LAST:event_BtnAlterarProdutoActionPerformed

    private void btnAlterarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarEstoqueActionPerformed

    private void btnCadastrarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarVendaActionPerformed
        FormCadastroVenda formCadastroVenda = new FormCadastroVenda(usuarioLogado);
        formCadastroVenda.setVisible(true);
    }//GEN-LAST:event_btnCadastrarVendaActionPerformed

    private void btnDetalhesVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetalhesVendaActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterarProduto;
    private javax.swing.JButton btnAlterarEstoque;
    private javax.swing.JButton btnCadastrarProdutos;
    private javax.swing.JButton btnCadastrarVenda;
    private javax.swing.JButton btnDetalhesVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTable tblVendas;
    private javax.swing.JTextField txtIDVenda;
    // End of variables declaration//GEN-END:variables
}
