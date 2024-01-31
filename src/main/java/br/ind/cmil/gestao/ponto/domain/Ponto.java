package br.ind.cmil.gestao.ponto.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_pontos")
public class Ponto extends Entidade {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entradaManha;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saidaManha;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entradaTarde;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saidaTarde;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entradaExtra;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saidaExtra;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entradaNoite;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saidaNoite;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Funcionario funcionario;

    public Ponto() {
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getEntradaManha() {
        return entradaManha;
    }

    public void setEntradaManha(LocalTime entradaManha) {
        this.entradaManha = entradaManha;
    }

    public LocalTime getSaidaManha() {
        return saidaManha;
    }

    public void setSaidaManha(LocalTime saidaManha) {
        this.saidaManha = saidaManha;
    }

    public LocalTime getEntradaTarde() {
        return entradaTarde;
    }

    public void setEntradaTarde(LocalTime entradaTarde) {
        this.entradaTarde = entradaTarde;
    }

    public LocalTime getSaidaTarde() {
        return saidaTarde;
    }

    public void setSaidaTarde(LocalTime saidaTarde) {
        this.saidaTarde = saidaTarde;
    }

    public LocalTime getEntradaExtra() {
        return entradaExtra;
    }

    public void setEntradaExtra(LocalTime entradaExtra) {
        this.entradaExtra = entradaExtra;
    }

    public LocalTime getSaidaExtra() {
        return saidaExtra;
    }

    public void setSaidaExtra(LocalTime saidaExtra) {
        this.saidaExtra = saidaExtra;
    }

    public LocalTime getEntradaNoite() {
        return entradaNoite;
    }

    public void setEntradaNoite(LocalTime entradaNoite) {
        this.entradaNoite = entradaNoite;
    }

    public LocalTime getSaidaNoite() {
        return saidaNoite;
    }

    public void setSaidaNoite(LocalTime saidaNoite) {
        this.saidaNoite = saidaNoite;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ponto{");
        sb.append("data=").append(data);
        sb.append(", entradaManha=").append(entradaManha);
        sb.append(", saidaManha=").append(saidaManha);
        sb.append(", entradaTarde=").append(entradaTarde);
        sb.append(", saidaTarde=").append(saidaTarde);
        sb.append(", entradaExtra=").append(entradaExtra);
        sb.append(", saidaExtra=").append(saidaExtra);
        sb.append(", entradaNoite=").append(entradaNoite);
        sb.append(", saidaNoite=").append(saidaNoite);
        sb.append(", funcionario=").append(funcionario);
        sb.append('}');
        return sb.toString();
    }

}
