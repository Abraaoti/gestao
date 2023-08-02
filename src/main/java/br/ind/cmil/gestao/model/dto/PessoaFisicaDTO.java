package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 *
 * @author abraao
 */
public class PessoaFisicaDTO extends PessoaDTO {

    //@Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
    @NotBlank
    @NotNull(message = "CPF é um campo requerido")
    // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
    protected String cpf;
    @NotBlank
    @NotNull(message = "RG é um campo requerido")
    protected String rg;
    @NotBlank
    @NotNull(message = "MÃE é um campo requerido")
    protected String mae;
    @NotBlank
    @NotNull(message = "PAI é um campo requerido")
    protected String pai;
    @NotBlank
    @NotNull(message = "PASSAPORTE é um campo requerido")
    protected String passaporte;
    @NotBlank
    @NotNull(message = "GENERO é um campo requerido")
    protected String genero;
    @NotBlank
    @NotNull(message = "ESTADO CIVIL é um campo requerido")
    protected String estado_civil;
    @NotBlank
    @NotNull(message = "NATURALIDADE é um campo requerido")
    protected String naturalidade;

    public PessoaFisicaDTO(String cpf, String rg, String mae, String pai, String passaporte, String genero, String estado_civil, String naturalidade, String nome, String sobrenome, Date nascimento) {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
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
        return "PessoaFisicaDTO{" + "cpf=" + cpf + ", rg=" + rg + ", mae=" + mae + ", pai=" + pai + ", passaporte=" + passaporte + ", genero=" + genero + ", estado_civil=" + estado_civil + ", naturalidade=" + naturalidade + '}';
    }

}
