package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author abraao
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_enderecos")
public class Endereco extends Entidade {

    @Column(length = 70)
    protected String uf;
    @Column(length = 70)
    protected String cidade;
    @Column(length = 70)
    protected String bairro;
    @Column(length = 70)
    protected String rua;
    //@Pattern("\\d{5}-\\d{3})
    @Column(length = 15)
    protected String cep;
    @Column(length = 15)
    protected String numero;
    @Column(length = 70)
    protected String complemento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    public Endereco() {
    }

    public Endereco(String uf, String cidade, String bairro, String rua, String cep, String numero, String complemento, Pessoa pessoa) {
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.pessoa = pessoa;
    }

}
