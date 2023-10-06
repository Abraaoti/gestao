package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import br.ind.cmil.gestao.model.enums.EPeriodo;
import br.ind.cmil.gestao.model.enums.TipoPresenca;
import br.ind.cmil.gestao.model.enums.converters.EPeridoConvert;
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
@Table(name = "tbl_cartao_pontos")
public class CartaoPonto extends Entidade {

    @Column(name = "dia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dia;
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private Funcionario funcionario;

    @Column(name = "periodo", nullable = false)
    @Convert(converter = EPeridoConvert.class)
    private EPeriodo periodo;

    @Column(name = "presenca", nullable = false)
    @Convert(converter = TipoPresencaConvert.class)
    private TipoPresenca status;

    @ManyToOne
    @JoinColumn(name = "auxiliar_id")
    private AuxiliarAdministrativo auxiliar;

    public CartaoPonto() {
    }

    public CartaoPonto(LocalDate dia, Horario horario, Funcionario funcionario, EPeriodo periodo) {
        this.dia = dia;
        this.horario = horario;
        this.funcionario = funcionario;
        this.periodo = periodo;
    }

}
