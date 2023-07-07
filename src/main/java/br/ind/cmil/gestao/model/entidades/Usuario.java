package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nome"),
    @UniqueConstraint(columnNames = "email")
})
public class Usuario extends Entidade implements UserDetails{

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    // @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String password;
    @Column(name = "data_cadastro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataCadastro;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
    @Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ativo;
    @Column(name = "verificador", length = 64)
    private String verificador;
    private String token;
    private boolean accountVerified;
    private int failedLoginAttempts;
    private boolean loginDisabled;
    // @OneToMany(mappedBy = "user")
    //private Set<Token> tokens;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_usuario_perfis",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
    private Set<Perfil> perfis = new HashSet<>();

    public Usuario() {
        // this.secret = SECRET_KEYS;
    }

    public Usuario(Long id) {
        super.setId(id);
    }

    public Usuario(String nome, String email, String password, LocalDateTime dataCadastro, LocalDateTime updatedAt, boolean ativo, String verificador, String token, boolean accountVerified, int failedLoginAttempts, boolean loginDisabled) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.dataCadastro = dataCadastro;
        this.updatedAt = updatedAt;
        this.ativo = ativo;
        this.verificador = verificador;
        this.token = token;
        this.accountVerified = accountVerified;
        this.failedLoginAttempts = failedLoginAttempts;
        this.loginDisabled = loginDisabled;
    }

    public void addUsuarioPerfis(Perfil perfil) {
        this.perfis.add(perfil);
        perfil.getUsuarios().add(this);
    }

    public void removeUserGroups(Perfil group) {
        this.perfis.remove(group);
        group.getUsuarios().remove(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getVerificador() {
        return verificador;
    }

    public void setVerificador(String verificador) {
        this.verificador = verificador;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean isLoginDisabled() {
        return loginDisabled;
    }

    public void setLoginDisabled(boolean loginDisabled) {
        this.loginDisabled = loginDisabled;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", password=" + password + ", dataCadastro=" + dataCadastro + ", updatedAt=" + updatedAt + ", ativo=" + ativo + ", verificador=" + verificador + ", token=" + token + ", accountVerified=" + accountVerified + ", failedLoginAttempts=" + failedLoginAttempts + ", loginDisabled=" + loginDisabled + ", perfis=" + perfis + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.perfis;
    }

   @Override
    public String getUsername() {
        return this.email;
    }

   @Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
   

}
