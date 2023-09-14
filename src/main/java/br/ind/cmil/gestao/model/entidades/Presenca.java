
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import br.ind.cmil.gestao.model.enums.TipoPresenca;
import br.ind.cmil.gestao.model.enums.converters.TipoPresencaConvert;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_presencas")
public class Presenca extends Entidade {

    @Column(name = "data_presenca")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPresenca;
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
    @ManyToOne
    @JoinColumn(name = "auxiliar_rh_id")
    private AuxiliarAdministrativo auxiliar_rh;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private Funcionario funcionario;

    @Column(name = "presenca", nullable = false)
    @Convert(converter = TipoPresencaConvert.class)
    private TipoPresenca status;

    public Presenca() {
    }

    public Presenca(LocalDate data_presenca, Horario horario, AuxiliarAdministrativo auxiliar_rh, Funcionario funcionario, TipoPresenca status) {
        this.dataPresenca = data_presenca;
        this.horario = horario;
        this.auxiliar_rh = auxiliar_rh;
        this.funcionario = funcionario;
        this.status = status;
    }

    
}
