package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_usuario_new_location_tokens")
public class NewLocationToken extends Entidade {

    private String token;

    @OneToOne(targetEntity = UsuarioLocation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_location_id")
    private UsuarioLocation userLocation;

    public NewLocationToken() {
    }

    public NewLocationToken(String token) {
        this.token = token;
    }

    public NewLocationToken(String token, UsuarioLocation userLocation) {
        this.token = token;
        this.userLocation = userLocation;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioLocation getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(UsuarioLocation userLocation) {
        this.userLocation = userLocation;
    }

}
