package br.ind.cmil.gestao.frequencia.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.convert.TipoAusenciaConvert;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_frequencias")
public class Frequencia extends Entidade {

    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "status", nullable = false)
    @Convert(converter = TipoAusenciaConvert.class)
    private TipoFrequencia status = TipoFrequencia.FALTA;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entradaManha;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saidaManha;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entradaTarde;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saidaTarde;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entradaExtra;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saidaExtra;
    @ManyToMany
    @JoinTable(
            name = "tnl_frequencia_funcionarios",
            joinColumns = @JoinColumn(name = "frequencia_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private Set<Funcionario> funcionarios;

    public Frequencia() {
    }

    public Frequencia(Long id) {
        super.setId(id);
    }

    public Frequencia(TipoFrequencia tipo) {
        this.status = TipoFrequencia.valueOf(tipo.getValue());
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoFrequencia getStatus() {
        return status;
    }

    public void setStatus(TipoFrequencia status) {
        this.status = status;
    }

    public LocalDateTime getEntradaManha() {
        return entradaManha;
    }

    public void setEntradaManha(LocalDateTime entradaManha) {
        this.entradaManha = entradaManha;
    }

    public LocalDateTime getSaidaManha() {
        return saidaManha;
    }

    public void setSaidaManha(LocalDateTime saidaManha) {
        this.saidaManha = saidaManha;
    }

    public LocalDateTime getEntradaTarde() {
        return entradaTarde;
    }

    public void setEntradaTarde(LocalDateTime entradaTarde) {
        this.entradaTarde = entradaTarde;
    }

    public LocalDateTime getSaidaTarde() {
        return saidaTarde;
    }

    public void setSaidaTarde(LocalDateTime saidaTarde) {
        this.saidaTarde = saidaTarde;
    }

    public LocalDateTime getEntradaExtra() {
        return entradaExtra;
    }

    public void setEntradaExtra(LocalDateTime entradaExtra) {
        this.entradaExtra = entradaExtra;
    }

    public LocalDateTime getSaidaExtra() {
        return saidaExtra;
    }

    public void setSaidaExtra(LocalDateTime saidaExtra) {
        this.saidaExtra = saidaExtra;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
