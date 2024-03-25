
package br.ind.cmil.gestao.frequencia.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_funcionarios_frequencias")
public class FuncionarioFrequencia extends Entidade {

    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id", nullable = false)
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "frequencia_id", referencedColumnName = "id", nullable = false)
    private Frequencia frequencia;

    public FuncionarioFrequencia() {
        data = LocalDate.now();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FuncionarioFrequencia{");
        sb.append("data=").append(data);
        sb.append(", funcionario=").append(funcionario);
        sb.append(", frequencia=").append(frequencia);
        sb.append('}');
        return sb.toString();
    }

}
