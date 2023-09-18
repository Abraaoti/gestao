
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "tbl_contratos")
public class Contrato extends Entidade {
    @Column(length = 80, unique = true, nullable = false)
    private String numero;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fim;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime assinatura;
    @Column(name = "local", unique = true, nullable = false)
    private String local;
    @Column(name = "funcoes", unique = true, nullable = false)
    private String funcoes;
  

    public Contrato() {
    }
    
}
