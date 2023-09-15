package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nome"),
    @UniqueConstraint(columnNames = "email")
})
public class Usuario extends Entidade{

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
    // @OneToMany(mappedBy = "user")
    //private Set<Token> tokens;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_usuario_perfis",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
    private Set<Perfil> perfis = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id) {
        super.setId(id);
    }

    public Usuario(String nome, String email, String password, LocalDateTime dataCadastro, LocalDateTime updatedAt, boolean ativo, String verificador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.dataCadastro = dataCadastro;
        this.updatedAt = updatedAt;
        this.ativo = ativo;
        this.verificador = verificador;
    }

    public void addPerfis(TipoPerfil perfil) {
        if (this.perfis == null) {
            this.perfis = new HashSet<>();
        }
        this.perfis.add(new Perfil(perfil.getValue()));
    }

    public void removePerfis(Perfil perfil) {
        this.perfis.remove(perfil);
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

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", password=" + password + ", dataCadastro=" + dataCadastro + ", updatedAt=" + updatedAt + ", ativo=" + ativo + ", verificador=" + verificador + ", perfis=" + perfis + '}';
    }

   /** @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Perfil perfil : perfis) {
            authorities.add(new SimpleGrantedAuthority(perfil.getTp().getValue()));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }**/

}
