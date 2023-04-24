
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends Entidade{
    @Column(length = 80)
	protected String nome;

	@Column(length = 120)
	protected String sobrenome;

	@Column(name = "nasc")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date nascimento;

	// @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
	// @JsonManagedReference
	// private Endereco endereco;

	// @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
	// @JsonManagedReference
	// private Email mail;

	//@JsonIgnore
	//@OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE, orphanRemoval = true)
	//protected List<Telefone> telefones;

	public Pessoa() {
		
	}

	public Pessoa(String nome, String sobrenome, Date nascimento) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nascimento = nascimento;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", sobrenome=" + sobrenome + ", nascimento=" + nascimento + '}';
    }
    

}
