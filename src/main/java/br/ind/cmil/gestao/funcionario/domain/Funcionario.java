package br.ind.cmil.gestao.funcionario.domain;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.pessoa.domain.PessoaFisica;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private PessoaJuridica empresa;

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa) {
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, Long id) {
        super(id);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, id);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, nome);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, PessoaJuridica empresa, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id, String nome, String sobrenome, LocalDate nascimento) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, id, nome, sobrenome, nascimento);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.empresa = empresa;
    }

    /**
     * public void addFrequencia(Frequencia frequencia) {
     * this.frequencias.add(frequencia); if (frequencia.getFuncionario()!= this)
     * { frequencia.setFuncionario(this); }
    }
     */
    public Funcionario() {
    }

    public Funcionario(Long id) {
        super.setId(id);
    }

    public Funcionario(String nome) {
        super.setNome(nome);
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

    @Override
    public String toString() {
        return "Funcionario{" + "clt=" + clt + ", admissao=" + admissao + ", departamento=" + departamento + ", cargo=" + cargo + ", demissao=" + demissao + ", empresa=" + empresa + '}';
    }

}
