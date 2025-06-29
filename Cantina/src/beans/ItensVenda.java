package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_venda")
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Integer id;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda", referencedColumnName = "id", nullable = false)
    private Venda venda;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
    private Produto produto;

    public ItensVenda() {}

    public Integer getId() { 
        return id; 
    }
    
    public void setId(Integer id) { 
        this.id = id; 
    }
    
    public Integer getQuantidade() { 
        return quantidade; 
    }
    
    public void setQuantidade(Integer quantidade) { 
        this.quantidade = quantidade; 
    }
    
    public Venda getVenda() { 
        return venda; 
    }
    
    public void setVenda(Venda venda) { 
        this.venda = venda; 
    }
    
    public Produto getProduto() { 
        return produto; 
    }
    
    public void setProduto(Produto produto) { 
        this.produto = produto; 
    }
}
