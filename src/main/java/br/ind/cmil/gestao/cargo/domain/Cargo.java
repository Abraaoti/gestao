package br.ind.cmil.gestao.cargo.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_cargos")
public class Cargo extends Entidade {

    @Column(length = 80, unique = true, nullable = false)
    private String nome;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;

    public Cargo() {

    }

    public Cargo(Long id) {
        super.setId(id);
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

}
