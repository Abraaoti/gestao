package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_funcionarios")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends PessoaFisica {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate admissao;
    protected String matricula;
    @ManyToOne
    @JoinColumn(name = "departmento_id", nullable = false)
    private Departamento departmento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate demissao;

    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    protected BigDecimal salario;

    public Funcionario() {
    }

    public Funcionario(LocalDate admissao, String matricula, Departamento departmento, LocalDate demissao, BigDecimal salario) {
        this.admissao = admissao;
        this.matricula = matricula;
        this.departmento = departmento;
        this.demissao = demissao;
        this.salario = salario;
    }

    public Funcionario(LocalDate admissao, String matricula, Departamento departmento, LocalDate demissao, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade);
        this.admissao = admissao;
        this.matricula = matricula;
        this.departmento = departmento;
        this.demissao = demissao;
        this.salario = salario;
    }

    public Funcionario(LocalDate admissao, String matricula, Departamento departmento, LocalDate demissao, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome, String sobrenome, Date nascimento, Endereco endereco) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade, nome, sobrenome, nascimento, endereco);
        this.admissao = admissao;
        this.matricula = matricula;
        this.departmento = departmento;
        this.demissao = demissao;
        this.salario = salario;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Departamento getDepartmento() {
        return departmento;
    }

    public void setDepartmento(Departamento departmento) {
        this.departmento = departmento;
    }

    public LocalDate getDemissao() {
        return demissao;
    }

    public void setDemissao(LocalDate demissao) {
        this.demissao = demissao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "admissao=" + admissao + ", matricula=" + matricula + ", departmento=" + departmento + ", demissao=" + demissao + ", salario=" + salario + '}';
    }

}
