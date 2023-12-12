package br.ind.cmil.gestao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author abraao
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "tbl_fornecedores")
public class Fornecedor extends PessoaJuridica {

    @JsonIgnore
    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    protected List<Produto> produtos;

    public Fornecedor() {
        this.produtos = new ArrayList<>();
    }

    public Fornecedor(List<Produto> produtos) {
        this.produtos = produtos;
    }

   
   
   

    public void addProduto(Produto produto) {
        if (this.produtos == null) {
            this.produtos = new ArrayList<>();
        }
        produtos.add(produto);
        produto.setFornecedor(this);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "produtos=" + produtos + '}';
    }

}
