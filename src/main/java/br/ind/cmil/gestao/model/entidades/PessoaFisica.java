
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import br.ind.cmil.gestao.model.enums.converters.EstadoCivilConvert;
import br.ind.cmil.gestao.model.enums.converters.GeneroConvert;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_pessoa_fisicas")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Pessoa {

    @Column(name = "cpf", length = 15, unique = true)
    @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
    protected String cpf;

    @Column(name = "rg", length = 11, unique = true)
    protected String rg;

    @Column(name = "mae", nullable = false)
    protected String mae;

    @Column(name = "pai", nullable = false)
    protected String pai;

    @Column(name = "passaporte", length = 15, unique = true, nullable = false)
    protected String passaporte;

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

    public PessoaFisica(String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.passaporte = passaporte;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome, String sobrenome, Date nascimento) {
        super(nome, sobrenome, nascimento);
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.passaporte = passaporte;
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

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
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
        return "PessoaFisica{" + "cpf=" + cpf + ", rg=" + rg + ", mae=" + mae + ", pai=" + pai + ", passaporte=" + passaporte + ", genero=" + genero + ", estado_civil=" + estado_civil + ", naturalidade=" + naturalidade + '}';
    }

}

