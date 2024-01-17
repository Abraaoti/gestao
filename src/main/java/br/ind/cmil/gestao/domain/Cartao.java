package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "tbl_cartoes")
public class Cartao extends Entidade {

    @Column(name = "numero", unique = true)
    private String numero;  
    @ManyToOne
    @JoinColumn(name = "periodo_id", referencedColumnName = "id", nullable = false)
    protected Periodo periodo;
    @ManyToOne
    @JoinColumn(name = "frequencia_id", referencedColumnName = "id", nullable = false)
    private Frequencia frequencia;
    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id", nullable = false)
    private Funcionario funcionario;

    public Cartao() {
    }

    public Cartao(Long id) {
        super.setId(id);
    }

    public Cartao(String numero) {
        this.numero = numero;
    }

}
