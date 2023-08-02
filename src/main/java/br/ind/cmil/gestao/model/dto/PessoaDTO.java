package br.ind.cmil.gestao.model.dto;

import br.ind.cmil.gestao.model.base.IdBase;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public class PessoaDTO extends IdBase {

    @Column(length = 80)
    protected String nome;
    @Column(length = 120)
    protected String sobrenome;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date nascimento;

    public PessoaDTO(String nome, String sobrenome, Date nascimento) {
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
        return "PessoaDTO{" + "nome=" + nome + ", sobrenome=" + sobrenome + ", nascimento=" + nascimento + '}';
    }

  

}
