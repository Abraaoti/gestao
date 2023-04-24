
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_produtos")
public class Produto extends Entidade {

    @Column(length = 50)
    private String nome;
    @Column(length = 110)
    private String descricao;
    @Column(length = 50)
    private String und;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "fornecedor_fk")
    private Fornecedor fornecedor;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(String nome, String descricao, String und, BigDecimal valor, Fornecedor fornecedor, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.und = und;
        this.preco = valor;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnd() {
        return und;
    }

    public void setUnd(String und) {
        this.und = und;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", descricao=" + descricao + ", und=" + und + ", preco=" + preco + ", fornecedor=" + fornecedor + ", categoria=" + categoria + '}';
    }

   

}
