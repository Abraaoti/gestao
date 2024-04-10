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
import jakarta.persistence.Transient;
import java.time.LocalDate;
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

    @Transient
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horaAtual;
    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "status", nullable = false)
    @Convert(converter = TipoAusenciaConvert.class)
    private TipoFrequencia status = TipoFrequencia.FALTA;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime entrada;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime intervalo;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime retorno;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime saida;
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

    public LocalTime getHoraAtual() {
        return horaAtual;
    }

    public void setHoraAtual(LocalTime horaAtual) {
        this.horaAtual = horaAtual;
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

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(LocalTime intervalo) {
        this.intervalo = intervalo;
    }

    public LocalTime getRetorno() {
        return retorno;
    }

    public void setRetorno(LocalTime retorno) {
        this.retorno = retorno;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
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
        sb.append("horaAtual=").append(horaAtual);
        sb.append(", data=").append(data);
        sb.append(", status=").append(status);
        sb.append(", entrada=").append(entrada);
        sb.append(", intervalo=").append(intervalo);
        sb.append(", retorno=").append(retorno);
        sb.append(", saida=").append(saida);
        sb.append(", funcionario=").append(funcionario);
        sb.append('}');
        return sb.toString();
    }

   

}
