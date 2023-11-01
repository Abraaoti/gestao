package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
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
@Table(name = "tbl_assistente_administrativos")
public class AssistenteAdministrativo extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "assistente")
    private List<Agendamento> agendamentos;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public AssistenteAdministrativo() {
    }

    public AssistenteAdministrativo(Long id) {
        super.setId(id);
    }

    public AssistenteAdministrativo(Usuario usuario) {
        this.usuario = usuario;
    }

}
