package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
