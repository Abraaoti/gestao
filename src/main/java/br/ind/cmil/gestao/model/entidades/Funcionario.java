package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
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
    private LocalDate admissao;
    @ManyToOne
    @JoinColumn(name = "departmento_id", nullable = false)
    private Departamento departmento;
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;

    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;

    public Funcionario() {
    }

    public Funcionario(LocalDate admissao, Departamento departmento, Cargo cargo, LocalDate demissao, BigDecimal salario) {
        this.admissao = admissao;
        this.departmento = departmento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
    }

    public Funcionario(LocalDate admissao, Departamento departmento, Cargo cargo, LocalDate demissao, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade);
        this.admissao = admissao;
        this.departmento = departmento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
    }

    public Funcionario(LocalDate admissao, Departamento departmento, Cargo cargo, LocalDate demissao, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome, String sobrenome, Date nascimento, Endereco endereco) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade, nome, sobrenome, nascimento, endereco);
        this.admissao = admissao;
        this.departmento = departmento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public Departamento getDepartmento() {
        return departmento;
    }

    public void setDepartmento(Departamento departmento) {
        this.departmento = departmento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        return "Funcionario{" + "admissao=" + admissao + ", departmento=" + departmento + ", cargo=" + cargo + ", demissao=" + demissao + ", salario=" + salario + '}';
    }

   

   

}
