package dao;

import beans.Produto;
import dao.exceptions.NomeJaExistenteException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProdutoJpaController implements Serializable {
    
    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Produto findProdutoByNome(String nome) {
        EntityManager em = getEntityManager();
        
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public void create(Produto produto) throws NomeJaExistenteException {
        if (findProdutoByNome(produto.getNome()) != null) {
            throw new NomeJaExistenteException("O nome de produto '" + produto.getNome() + "' já está em uso.");
        }
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Produto produto) throws NomeJaExistenteException, NonexistentEntityException, Exception {
        Produto produtoExistente = findProdutoByNome(produto.getNome());
        if (produtoExistente != null && !produtoExistente.getId().equals(produto.getId())) {
             throw new NomeJaExistenteException("O nome de produto '" + produto.getNome() + "' já existe, use a funcionalidade de alterar produto.");
        }

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            produto = em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getId();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("O produto com id " + id + " não existe mais.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
