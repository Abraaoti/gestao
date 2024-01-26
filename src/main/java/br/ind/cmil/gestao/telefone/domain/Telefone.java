
package br.ind.cmil.gestao.telefone.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.convert.TipoTelefoneConvert;
import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ti
 */
@Entity
@Table(name = "tbl_telefones")
@SuppressWarnings("serial")
public class Telefone extends Entidade {

    @Column(length = 20, unique = true)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Número do Telefone Obrigatório")
    private String numero;

    @Column(name = "tipo", nullable = false, length = 20)
    @Convert(converter = TipoTelefoneConvert.class)
    private TipoTelefone tipo;
   
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) 
    private Pessoa pessoa;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}