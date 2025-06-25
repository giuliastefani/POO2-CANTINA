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
        btnCadastrarVenda = new javax.swing.JButton();
        btnDetalhesVenda = new javax.swing.JButton();

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAlterarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
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
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrarVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalhesVenda)
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
        setLocationRelativeTo(null);
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

    private void btnCadastrarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarVendaActionPerformed
        FormCadastroVenda formCadastroVenda = new FormCadastroVenda(usuarioLogado);
        formCadastroVenda.setVisible(true);
    }//GEN-LAST:event_btnCadastrarVendaActionPerformed

    private void btnDetalhesVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesVendaActionPerformed
        int selectedRow = tblVendas.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma venda para visualizar.", "Nenhuma Venda Selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer idVenda = (Integer) tblVendas.getValueAt(selectedRow, 0);

        FormDetalhesVenda formDetalhesVenda = new FormDetalhesVenda(idVenda);
        formDetalhesVenda.setVisible(true);
    }//GEN-LAST:event_btnDetalhesVendaActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterarProduto;
    private javax.swing.JButton btnCadastrarProdutos;
    private javax.swing.JButton btnCadastrarVenda;
    private javax.swing.JButton btnDetalhesVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTable tblVendas;
    // End of variables declaration//GEN-END:variables
}
