
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_projetos")
public class Projeto extends Entidade {

    @Column(length = 80, unique = true, nullable = false)
    private String contrato;
    @Column(length = 80, unique = true, nullable = false)
    private String numero;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fim;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
    @Column(name = "responsavel", unique = true, nullable = false)
    private String responsavel;
    @Column(name = "seguranca", unique = true, nullable = false)
    private String seguranca;
    @Column(name = "gestor", unique = true, nullable = false)
    private String gestor;
    @ManyToOne
    @JoinColumn(name = "administrador_id")
    private Administrador administrador;

    public Projeto() {
    }
    
}
