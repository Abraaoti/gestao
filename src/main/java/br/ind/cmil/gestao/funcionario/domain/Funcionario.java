package br.ind.cmil.gestao.funcionario.domain;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.jornada.domain.JornadaTrabalho;
import br.ind.cmil.gestao.pessoa.domain.PessoaFisica;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import br.ind.cmil.gestao.ponto.domain.Ponto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_funcionarios")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends PessoaFisica {

    private String clt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissao;
    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;
    //@ManyToOne
    //@JoinColumn(name = "centro_custo_id", referencedColumnName = "id")
    // private CentroCusto centro;

  
    @ManyToOne
    private PessoaJuridica empresa;
    @ManyToOne
    private JornadaTrabalho jornadaTrabalho;
    private BigDecimal tolerancia;
    private LocalDateTime inicioJornada;
    private LocalDateTime finalJornada;

    public Funcionario() {
    }

    public Funcionario(Long id) {
        super.setId(id);
    }

    public Funcionario(String nome) {
        super.setNome(nome);
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, JornadaTrabalho jornadaTrabalho, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada) {
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
        this.jornadaTrabalho = jornadaTrabalho;
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, JornadaTrabalho jornadaTrabalho, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada, Long id) {
        super(id);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
        this.jornadaTrabalho = jornadaTrabalho;
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, JornadaTrabalho jornadaTrabalho, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
        this.jornadaTrabalho = jornadaTrabalho;
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, JornadaTrabalho jornadaTrabalho, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, id);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
        this.jornadaTrabalho = jornadaTrabalho;
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, JornadaTrabalho jornadaTrabalho, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, nome);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
        this.jornadaTrabalho = jornadaTrabalho;
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, JornadaTrabalho jornadaTrabalho, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id, String nome, String sobrenome, LocalDate nascimento) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, id, nome, sobrenome, nascimento);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
        this.jornadaTrabalho = jornadaTrabalho;
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public String getClt() {
        return clt;
    }

    public void setClt(String clt) {
        this.clt = clt;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }

    public JornadaTrabalho getJornadaTrabalho() {
        return jornadaTrabalho;
    }

    public void setJornadaTrabalho(JornadaTrabalho jornadaTrabalho) {
        this.jornadaTrabalho = jornadaTrabalho;
    }

    public BigDecimal getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(BigDecimal tolerancia) {
        this.tolerancia = tolerancia;
    }

    public LocalDateTime getInicioJornada() {
        return inicioJornada;
    }

    public void setInicioJornada(LocalDateTime inicioJornada) {
        this.inicioJornada = inicioJornada;
    }

    public LocalDateTime getFinalJornada() {
        return finalJornada;
    }

    public void setFinalJornada(LocalDateTime finalJornada) {
        this.finalJornada = finalJornada;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario{");
        sb.append("clt=").append(clt);
        sb.append(", admissao=").append(admissao);
        sb.append(", departamento=").append(departamento);
        sb.append(", cargo=").append(cargo);
        sb.append(", demissao=").append(demissao);

        sb.append(", empresa=").append(empresa);
        sb.append(", jornadaTrabalho=").append(jornadaTrabalho);
        sb.append(", tolerancia=").append(tolerancia);
        sb.append(", inicioJornada=").append(inicioJornada);
        sb.append(", finalJornada=").append(finalJornada);
        sb.append('}');
        return sb.toString();
    }

}
