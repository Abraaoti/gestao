package br.ind.cmil.gestao.auxiliar.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "tbl_auxiliar_administrativos")
public class AuxiliarAdministrativo extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
    //@JsonIgnore
    //@OneToMany(mappedBy = "auxiliar_rh")
    //private List<String> presencas;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public AuxiliarAdministrativo() {
    }

    public AuxiliarAdministrativo(Long id) {
        super.setId(id);
    }

    public AuxiliarAdministrativo(Usuario usuario) {
        this.usuario = usuario;
    }

}
