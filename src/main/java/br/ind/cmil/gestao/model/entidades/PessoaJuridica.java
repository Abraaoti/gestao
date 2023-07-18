package br.ind.cmil.gestao.model.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

/**
 *
 * @author abraao
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "tbl_pessoa_juridicas", indexes = {
    @Index(name = "idx_empresa_cnpj", columnList = "cnpj")})
public class PessoaJuridica extends Pessoa {

    @Column(name = "cnpj", unique = true, length = 20, nullable = true)
    @Pattern(regexp = "/^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$/", message = "cnpj obrigatório")
    private String cnpj;

    @Column(unique = true, length = 20, nullable = true)
    private String ie;

    @Column(unique = true, length = 20, nullable = false)
    private String im;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String cnpj, String ie, String im) {
        this.cnpj = cnpj;
        this.ie = ie;
        this.im = im;
    }

    public PessoaJuridica(String cnpj, String ie, String im, String nome, String sobrenome, Date nascimento, Endereco endereco) {
        super(nome, sobrenome, nascimento, endereco);
        this.cnpj = cnpj;
        this.ie = ie;
        this.im = im;
    }

  

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" + "cnpj=" + cnpj + ", ie=" + ie + ", im=" + im + '}';
    }
    
}
