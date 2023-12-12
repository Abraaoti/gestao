
package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_horarios", indexes = {@Index(name = "idx_hora_minuto", columnList = "hora_minuto")})
public class Horario extends Entidade {

    @Column(name = "hora_minuto", unique = true, nullable = false)
    private LocalTime horaMinuto;

    public Horario() {
    }
    
}
