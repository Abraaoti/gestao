
package br.ind.cmil.gestao.email.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "tbl_emails")
public class Email extends Entidade {

    @Column(length = 100, unique = true)
    protected String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_fk", referencedColumnName = "id")
	@JsonBackReference
    private Pessoa pessoa;

    public Email() {
    }

    public Email(String email, Pessoa pessoa) {
        this.email = email;
        this.pessoa = pessoa;
    }

    

}

