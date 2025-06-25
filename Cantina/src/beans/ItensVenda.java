package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_venda")
@NamedQueries({
    @NamedQuery(name = "ItensVenda.findAll", query = "SELECT i FROM ItensVenda i"),
    @NamedQuery(name = "ItensVenda.findByIdVenda", query = "SELECT i FROM ItensVenda i WHERE i.idVenda = :idVenda"),
    @NamedQuery(name = "ItensVenda.findByQuantidade", query = "SELECT i FROM ItensVenda i WHERE i.quantidade = :quantidade")})
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_venda")
    private Integer idVenda;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @JoinColumn(name = "id_venda", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Venda venda;
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto idProduto;

    public ItensVenda() {
    }

    public ItensVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public ItensVenda(Integer idVenda, int quantidade) {
        this.idVenda = idVenda;
        this.quantidade = quantidade;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenda != null ? idVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItensVenda)) {
            return false;
        }
        ItensVenda other = (ItensVenda) object;
        if ((this.idVenda == null && other.idVenda != null) || (this.idVenda != null && !this.idVenda.equals(other.idVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ItensVenda[ idVenda=" + idVenda + " ]";
    }
    
}
