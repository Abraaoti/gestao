package br.ind.cmil.gestao.fornecedor.domain;

import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 *
 * @author abraao
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "tbl_fornecedores")
public class Fornecedor extends PessoaJuridica {

    //@JsonIgnore
    //@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    //protected List<Produto> produtos;

    public Fornecedor() {
       // this.produtos = new ArrayList<>();
    }

    /**public Fornecedor(List<Produto> produtos) {
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
    }**/

}
