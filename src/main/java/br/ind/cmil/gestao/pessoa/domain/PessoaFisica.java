package br.ind.cmil.gestao.pessoa.domain;

import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.convert.EstadoCivilConvert;
import br.ind.cmil.gestao.convert.GeneroConvert;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_pessoa_fisicas")
@PrimaryKeyJoinColumn(name = "pessoaId")
public class PessoaFisica extends Pessoa {

    @Column(name = "cpf", length = 15, unique = true)
    //@Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
    protected String cpf;

    @Column(name = "rg", length = 11, unique = true)
    protected String rg;

    @Column(name = "mae", nullable = false)
    protected String mae;

    @Column(name = "pai", nullable = false)
    protected String pai;

    @Column(name = "genero", nullable = false)
    @Convert(converter = GeneroConvert.class)
    protected Genero genero;

    @Column(name = "ec", nullable = false)
    @Convert(converter = EstadoCivilConvert.class)
    protected EstadoCivil estado_civil;

    @Column(length = 50, nullable = false)
    protected String naturalidade;

    public PessoaFisica() {
    }
    public PessoaFisica(Long id) {
        super.setId(id);
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id) {
        super(id);
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome) {
        super(nome);
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id, String nome, String sobrenome, LocalDate nascimento) {
        super(id, nome, sobrenome, nascimento);
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
    }
    
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public EstadoCivil getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(EstadoCivil estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PessoaFisica{");
        sb.append("cpf=").append(cpf);
        sb.append(", rg=").append(rg);
        sb.append(", mae=").append(mae);
        sb.append(", pai=").append(pai);
        sb.append(", genero=").append(genero);
        sb.append(", estado_civil=").append(estado_civil);
        sb.append(", naturalidade=").append(naturalidade);
        sb.append('}');
        return sb.toString();
    }

   

}
