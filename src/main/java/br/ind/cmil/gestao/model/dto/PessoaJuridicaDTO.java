package br.ind.cmil.gestao.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author abraao
 */
public class PessoaJuridicaDTO extends PessoaDTO {

    // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", message = "cpf obrigatório")
    @NotBlank
    @NotNull(message = "CNPJ é um campo requerido")
    private String cnpj;
    @NotBlank
    @NotNull(message = "IE é um campo requerido")
    private String ie;
    @NotBlank
    @NotNull(message = "IM é um campo requerido")
    private String im;

    public PessoaJuridicaDTO(String cnpj, String ie, String im, String nome, String sobrenome, Date nascimento) {
        super(nome, sobrenome, nascimento);
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
        return "PessoaJuridicaDTO{" + "cnpj=" + cnpj + ", ie=" + ie + ", im=" + im + '}';
    }

  
}
