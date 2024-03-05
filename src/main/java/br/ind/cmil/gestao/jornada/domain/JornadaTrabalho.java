package br.ind.cmil.gestao.jornada.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author abraaocalelessocassi
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_jornada_trabalho")
public class JornadaTrabalho extends Entidade {

    private String descricao;

}
