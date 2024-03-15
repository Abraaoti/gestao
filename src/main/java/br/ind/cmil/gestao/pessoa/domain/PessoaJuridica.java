package br.ind.cmil.gestao.pessoa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraaocalelessocassi
 */
@Entity
@Table(name = "tbl_pessoas_juridicas", indexes = {
    @Index(name = "idx_pessoas_juridica_cnpj", columnList = "cnpj")})
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

    @Column(name = "cnpj", unique = true, length = 20, nullable = true)
    //@Pattern(regexp = "/^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$/", message = "cnpj obrigatório")
    private String cnpj;

    @Column(unique = true, length = 20, nullable = true)
    private String ie;

    @Column(unique = true, length = 20, nullable = false)
    private String im;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal capital;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id) {
        super.setId(id);
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

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" + "cnpj=" + cnpj + ", ie=" + ie + ", im=" + im + ", capital=" + capital + '}';
    }

}
