package forms;

import beans.Produto;
import com.formdev.flatlaf.FlatDarkLaf;
import dao.ProdutoJpaController;
import dao.exceptions.NomeJaExistenteException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FormCadastroProduto extends javax.swing.JFrame {

    private final ProdutoJpaController produtoController;
    private final FormPrincipal formPrincipal;
    private Integer idProdutoEmEdicao = null;
    
    public FormCadastroProduto(FormPrincipal parent, Integer idProdutoParaEditar) {
        initComponents();
        setLocationRelativeTo(parent);
        this.formPrincipal = parent;
        this.idProdutoEmEdicao = idProdutoParaEditar;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CantinaPU");
        produtoController = new ProdutoJpaController(emf);
        
        if (idProdutoEmEdicao != null) {
            setTitle("Alterar Produto");
            carregarDadosDoProduto();
        } else {
            setTitle("Cadastrar Novo Produto");
        }
    }
    
    private void carregarDadosDoProduto() {
        Produto produto = produtoController.findProduto(idProdutoEmEdicao);
        if (produto != null) {
            txtNome.setText(produto.getNome());
            txtValorUnitario.setText(String.valueOf(produto.getValorUnitario()));
            txtQtdEstoque.setText(String.valueOf(produto.getQuantidadeEstoque()));
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQtdEstoque = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SGBC - Cadastrar/Alterar Produto");
        setName("CadastroProduto"); // NOI18N
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Cadastrar/Alterar Produto");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Valor Unitário:");

        txtValorUnitario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Quantidade em Estoque:");

        txtQtdEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Nome Produto:");

        txtNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtQtdEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValorUnitario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQtdEstoque))
                .addGap(31, 31, 31))
        );

        btnSalvar.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(128, 128, 128))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (txtNome.getText().trim().isEmpty() || txtValorUnitario.getText().trim().isEmpty() || txtQtdEstoque.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nome = txtNome.getText().trim();
            long valor = Long.parseLong(txtValorUnitario.getText().replace(",", "."));
            int quantidade = Integer.parseInt(txtQtdEstoque.getText());
            
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setValorUnitario(valor);
            produto.setQuantidadeEstoque(quantidade);

            if (idProdutoEmEdicao == null) { // --- MODO CADASTRO ---
                produtoController.create(produto);
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            } else { // --- MODO ALTERAÇÃO ---
                produto.setId(idProdutoEmEdicao);
                produtoController.edit(produto);
                JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");
            }
            
            formPrincipal.atualizarTabela();
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor ou quantidade inválidos. Por favor, insira números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (NomeJaExistenteException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Duplicidade", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtQtdEstoque;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
