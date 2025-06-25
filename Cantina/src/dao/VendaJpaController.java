package dao;

import beans.ItensVenda;
import beans.Produto;
import beans.Usuario;
import beans.Venda;
import dao.exceptions.CarrinhoVazioException;
import dao.exceptions.EstoqueInsuficienteException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;

public class VendaJpaController implements Serializable {

    public VendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void registrarVenda(Usuario vendedor, Map<Produto, Integer> itensDoCarrinho) 
            throws EstoqueInsuficienteException, CarrinhoVazioException, Exception {

        if (itensDoCarrinho == null || itensDoCarrinho.isEmpty()) {
            throw new CarrinhoVazioException("Não é possível registrar uma venda sem itens.");
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            BigDecimal valorTotal = BigDecimal.ZERO;
            for (Map.Entry<Produto, Integer> entry : itensDoCarrinho.entrySet()) {
                Produto produto = entry.getKey();
                Integer quantidadeVendida = entry.getValue();

                Produto produtoNoBanco = em.find(Produto.class, produto.getId());
                if (produtoNoBanco.getQuantidadeEstoque() < quantidadeVendida) {
                    throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
                }

                valorTotal = valorTotal.add(
                    produtoNoBanco.getValorUnitario().multiply(new BigDecimal(quantidadeVendida))
                );
            }

            Venda novaVenda = new Venda();
            novaVenda.setVendedor(vendedor);
            novaVenda.setData(new Date());
            novaVenda.setValorTotal(valorTotal);
            em.persist(novaVenda);

            em.flush();

            for (Map.Entry<Produto, Integer> entry : itensDoCarrinho.entrySet()) {
                Produto produto = entry.getKey();
                Integer quantidadeVendida = entry.getValue();

                ItensVenda item = new ItensVenda();
                item.setVenda(novaVenda);
                item.setProduto(produto);

                item.setQuantidade(quantidadeVendida);
                em.persist(item);

                Produto produtoParaAtualizar = em.find(Produto.class, produto.getId());
                int novoEstoque = produtoParaAtualizar.getQuantidadeEstoque() - quantidadeVendida;
                produtoParaAtualizar.setQuantidadeEstoque(novoEstoque);
                em.merge(produtoParaAtualizar);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
}
