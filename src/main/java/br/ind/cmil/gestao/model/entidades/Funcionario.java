package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@ToString
@Getter
@Setter
@Entity
@Table(name = "tbl_funcionarios")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends PessoaFisica {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate admissao;
    protected String matricula;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate demissao;

    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    protected BigDecimal salario;

    public Funcionario() {
    }

    public Funcionario(LocalDate admissao, String matricula, LocalDate demissao, BigDecimal salario) {
        this.admissao = admissao;
        this.matricula = matricula;
        this.demissao = demissao;
        this.salario = salario;
    }

    public Funcionario(LocalDate admissao, String matricula, LocalDate demissao, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade);
        this.admissao = admissao;
        this.matricula = matricula;
        this.demissao = demissao;
        this.salario = salario;
    }

    public Funcionario(LocalDate admissao, String matricula, LocalDate demissao, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome, String sobrenome, Date nascimento) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade, nome, sobrenome, nascimento);
        this.admissao = admissao;
        this.matricula = matricula;
        this.demissao = demissao;
        this.salario = salario;
    }

}
