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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
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

}
