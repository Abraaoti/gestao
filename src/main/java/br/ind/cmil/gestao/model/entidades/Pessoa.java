package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends Entidade {

    @Column(length = 80)
    protected String nome;
    @Column(length = 120)
    protected String sobrenome;
    @Column(name = "nasc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date nascimento;

    @OneToOne(mappedBy = "pessoa",cascade = CascadeType.ALL, orphanRemoval = true)
    protected Endereco endereco;
   

    public Pessoa() {

    }

    public Pessoa(String nome, String sobrenome, Date nascimento, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nascimento = nascimento;
        this.endereco = endereco;
    }

    public void addEndereco(Endereco endereco) {
        this.endereco = endereco;
        endereco.setPessoa(this);
    }

    public void removeEndereco(Endereco endereco) {
        if (endereco != null) {
            endereco.setPessoa(null);
        }
        this.endereco = null;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", sobrenome=" + sobrenome + ", nascimento=" + nascimento + ", endereco=" + endereco + '}';
    }

   

}
