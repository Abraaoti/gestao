package br.ind.cmil.gestao.frequencia.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.convert.TipoAusenciaConvert;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entradaManha;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saidaManha;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entradaTarde;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saidaTarde;   
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frequencia{");
        sb.append("data=").append(data);
        sb.append(", status=").append(status);
        sb.append(", entradaManha=").append(entradaManha);
        sb.append(", saidaManha=").append(saidaManha);
        sb.append(", entradaTarde=").append(entradaTarde);
        sb.append(", saidaTarde=").append(saidaTarde);
        sb.append(", funcionario=").append(funcionario);
        sb.append('}');
        return sb.toString();
    }

   
}
