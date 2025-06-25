package forms;

import beans.Produto;
import beans.Usuario;
import dao.ProdutoJpaController;
import dao.VendaJpaController;
import dao.exceptions.CarrinhoVazioException;
import dao.exceptions.EstoqueInsuficienteException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class FormCadastroVenda extends javax.swing.JFrame {
    
    private final VendaJpaController vendaController;
    private final ProdutoJpaController produtoController;
    private final Usuario usuarioLogado;
    private final List<Produto> todosOsProdutos;
    private final Map<Produto, Integer> carrinhoDeCompras;

    public FormCadastroVenda(Usuario usuarioLogado) {
        initComponents();
        setLocationRelativeTo(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CantinaPU");
        this.vendaController = new VendaJpaController(emf);
        this.produtoController = new ProdutoJpaController(emf);
        this.usuarioLogado = usuarioLogado;
        this.todosOsProdutos = produtoController.findProdutoEntities();
        this.carrinhoDeCompras = new LinkedHashMap<>();

        atualizarTabelaProdutosDisponiveis(todosOsProdutos);
        configurarBuscaAutomatica();
        atualizarTabelaCarrinho();
    }
    
    private void configurarBuscaAutomatica() {
        txtBuscaProduto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarProdutos(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrarProdutos(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrarProdutos(); }
        });
    }
    
    private void filtrarProdutos() {
        String textoBusca = txtBuscaProduto.getText().toLowerCase();
        List<Produto> produtosFiltrados = new ArrayList<>();
        for (Produto p : todosOsProdutos) {
            if (p.getNome().toLowerCase().contains(textoBusca)) {
                produtosFiltrados.add(p);
            }
        }
        atualizarTabelaProdutosDisponiveis(produtosFiltrados);
    }
    
    private void atualizarTabelaProdutosDisponiveis(List<Produto> produtos) {
        DefaultTableModel model = (DefaultTableModel) tblProdutosDisponiveis.getModel();
        model.setRowCount(0);
        for (Produto p : produtos) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getValorUnitario(), p.getQuantidadeEstoque()});
        }
    }

    private void atualizarTabelaCarrinho() {
        DefaultTableModel model = (DefaultTableModel) tblCarrinho.getModel();
        model.setRowCount(0);
        
        BigDecimal valorTotal = BigDecimal.ZERO;
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        
        for (Map.Entry<Produto, Integer> entry : carrinhoDeCompras.entrySet()) {
            Produto p = entry.getKey();
            Integer qtd = entry.getValue();
            BigDecimal subtotal = p.getValorUnitario().multiply(new BigDecimal(qtd));
            valorTotal = valorTotal.add(subtotal);

            model.addRow(new Object[]{
                p.getNome(),
                formatadorMoeda.format(p.getValorUnitario()),
                qtd,
            });
        }
        
        txtValorTotal.setText(formatadorMoeda.format(valorTotal));
    }
    
    private void limparVenda() {
        carrinhoDeCompras.clear();
        txtBuscaProduto.setText("");
        spnQuantidade.setValue(1);
        atualizarTabelaCarrinho();
        filtrarProdutos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscaProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutosDisponiveis = new javax.swing.JTable();
        spnQuantidade = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        btnAdicionarItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCarrinho = new javax.swing.JTable();
        btnFinalizarVenda = new javax.swing.JButton();
        btnRemoverItem = new javax.swing.JButton();
        txtValorTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Cadastrar venda");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Produto:");

        txtBuscaProduto.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        tblProdutosDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Valor", "Quantidade Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProdutosDisponiveis);
        if (tblProdutosDisponiveis.getColumnModel().getColumnCount() > 0) {
            tblProdutosDisponiveis.getColumnModel().getColumn(0).setResizable(false);
            tblProdutosDisponiveis.getColumnModel().getColumn(1).setResizable(false);
            tblProdutosDisponiveis.getColumnModel().getColumn(2).setResizable(false);
            tblProdutosDisponiveis.getColumnModel().getColumn(3).setResizable(false);
        }

        spnQuantidade.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Quantidade:");

        btnAdicionarItem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnAdicionarItem.setText("Adicionar");
        btnAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarItemActionPerformed(evt);
            }
        });

        tblCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Produto", "Valor", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCarrinho);
        if (tblCarrinho.getColumnModel().getColumnCount() > 0) {
            tblCarrinho.getColumnModel().getColumn(0).setResizable(false);
            tblCarrinho.getColumnModel().getColumn(1).setResizable(false);
            tblCarrinho.getColumnModel().getColumn(2).setResizable(false);
        }

        btnFinalizarVenda.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnFinalizarVenda.setText("Finalizar Venda");
        btnFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVendaActionPerformed(evt);
            }
        });

        btnRemoverItem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRemoverItem.setText("Remover");
        btnRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemActionPerformed(evt);
            }
        });

        txtValorTotal.setEditable(false);
        txtValorTotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Valor total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btnFinalizarVenda))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemoverItem)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdicionarItem))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(spnQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(spnQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarItem)
                    .addComponent(btnRemoverItem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarVenda)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        int selectedRow = tblProdutosDisponiveis.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer idProduto = (Integer) tblProdutosDisponiveis.getValueAt(selectedRow, 0);
        Produto produtoSelecionado = produtoController.findProduto(idProduto);

        int quantidadeParaAdicionar = (int) spnQuantidade.getValue();
        if (quantidadeParaAdicionar <= 0) {
            JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int quantidadeJaNoCarrinho = carrinhoDeCompras.getOrDefault(produtoSelecionado, 0);
        if ((quantidadeJaNoCarrinho + quantidadeParaAdicionar) > produtoSelecionado.getQuantidadeEstoque()) {
            JOptionPane.showMessageDialog(this, "Estoque insuficiente para a quantidade desejada.", "Erro de Estoque", JOptionPane.ERROR_MESSAGE);
            return;
        }

        carrinhoDeCompras.put(produtoSelecionado, quantidadeJaNoCarrinho + quantidadeParaAdicionar);
        atualizarTabelaCarrinho();
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        int selectedRow = tblCarrinho.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item do carrinho para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este item?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String nomeProduto = (String) tblCarrinho.getValueAt(selectedRow, 0);

        Produto produtoParaRemover = null;
        for (Produto p : carrinhoDeCompras.keySet()) {
            if (p.getNome().equals(nomeProduto)) {
                produtoParaRemover = p;
                break;
            }
        }
        
        if (produtoParaRemover != null) {
            carrinhoDeCompras.remove(produtoParaRemover);
            atualizarTabelaCarrinho();
        }
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void btnFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVendaActionPerformed
        if (carrinhoDeCompras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O carrinho está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja finalizar a venda?", "Confirmar Venda", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            vendaController.registrarVenda(usuarioLogado, carrinhoDeCompras);
            JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparVenda();
            
            todosOsProdutos.clear();
            todosOsProdutos.addAll(produtoController.findProdutoEntities());
            filtrarProdutos();

        } catch (EstoqueInsuficienteException | CarrinhoVazioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro crítico ao registrar a venda.\n" + e.getMessage(), "Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }//GEN-LAST:event_btnFinalizarVendaActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spnQuantidade;
    private javax.swing.JTable tblCarrinho;
    private javax.swing.JTable tblProdutosDisponiveis;
    private javax.swing.JTextField txtBuscaProduto;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
