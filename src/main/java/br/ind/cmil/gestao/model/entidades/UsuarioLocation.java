package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_usuario_locations")
public class UsuarioLocation extends Entidade {

    private String country;

    private boolean enabled;

    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario user;

    public UsuarioLocation() {
        super();
        enabled = false;
    }

    public UsuarioLocation(String country, boolean enabled, Usuario user) {
        this.country = country;
        this.enabled = enabled;
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UsuarioLocation{" + "country=" + country + ", enabled=" + enabled + ", user=" + user + '}';
    }

}
