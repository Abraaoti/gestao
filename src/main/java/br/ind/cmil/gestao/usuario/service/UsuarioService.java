
package br.ind.cmil.gestao.usuario.service;

import br.ind.cmil.gestao.enums.TipoPerfil;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.model.CadastroExternoDTO;
import br.ind.cmil.gestao.usuario.model.UsuarioRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ti
 */
public interface UsuarioService {
      void register(UsuarioRequest request);

    void salvarUsuarioGeral(UsuarioRequest request, String siteURL) throws MessagingException;

    UsuarioRequest buscarPorId(Long id);

    Set<UsuarioRequest> getUsuarios(Pageable pageable);

    public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
        return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    }

    UsuarioRequest preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId);

    void alterarSenha(Usuario usuario, String s1);

    UsuarioRequest buscarEmailAtivo(String email);

    UsuarioRequest buscarPorEmail(String email);

    void redefinirSenha(String email) throws MessagingException;

    void ativarCadastro(String codigo);

    boolean verify(String verificationCode);

    void salvarUsuarioExterno(CadastroExternoDTO usuario) throws MessagingException;

    Map<String, Object> buscarTodos(HttpServletRequest request);

    static boolean admin(UsuarioRequest usuario) {
        return usuario.perfis().size() > 3 &&  usuario.perfis().containsAll(Arrays.asList(TipoPerfil.ADMIN));
    }

    static boolean administrador(UsuarioRequest usuario) {
        return  usuario.perfis().containsAll(Arrays.asList(TipoPerfil.ADMINISTRADOR));
    }

    static boolean assistente(UsuarioRequest usuario) {
        return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.ASSISTENTE));
    }

    static boolean auxiliar(UsuarioRequest usuario) {
        return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.AUXILIAR));
    }

    static boolean comprador(UsuarioRequest usuario) {
         return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.COMPRADOR));
    }

    static boolean diretor(UsuarioRequest usuario) {
        return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.DIRETOR));
    }

    static boolean engenheiro(UsuarioRequest usuario) {
        return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.ENGENHEIRO));
    }

    static boolean gerente(UsuarioRequest usuario) {
        return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.GERENTE));
    }

    static boolean técnico(UsuarioRequest usuario) {
       return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.TECNICO));
    }

    static boolean usuário(UsuarioRequest usuario) {
        return usuario.perfis().containsAll(Arrays.asList(TipoPerfil.USUARIO));
    }

    /*

    boolean checkIfUserExist(final String email);

    void sendRegistrationConfirmationEmail(final Usuario user);

    boolean verifyUser(final String token);

    Usuario getUserById(final Long id);

    MfaTokenData mfaSetup(final String email);*/
    //UsuarioDTO save(UsuarioDTO userDTO);
    //UsuarioDTO fetch(Long userId);
    //AuthenticationResponse register(RegistroUsuarioDTO request);
    //AuthenticationResponse authenticate(LoginDTO request);
    // void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    //List<RegistroUsuarioDTO> getUsuarios();
    // List<RegistroUsuarioDTO> search(String searchTerm, int page, int size);
    //RegistroUsuarioDTO buscarPorId(Long id);
    //Boolean existsByUsuarioNome(String nome);
    // Boolean existsByUsuarioEmail(String email);
    // RegistroUsuarioDTO buscarPorNome(String nome);
    // RegistroUsuarioDTO buscarPorEmail(String email);
    // RegistroUsuarioDTO buscarPorEmailOUNome(String email, String nome);
    // void delete(Long id);
    // RegistroUsuarioDTO update(UsuarioDTO usuario);
    // UsuarioDTO updatePassword(@AuthenticationPrincipal User user, String s1, String s2, String s3);
    // public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
    // return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    //}
    //Usuario buscarPorIdEPerfil(Long usuarioId, Long[] perfisId);
    // UsuarioDTO preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId);
    //UsuarioDTO buscarFoto(String foto) throws FileNotFoundException;
    //void alterarSenha(UsuarioDTO usuario, String s1);
    //UsuarioDTO buscarEmailAtivo(String email);
    //void saveCadastroFinanceiro(Usuario usuario) throws MessagingException;
    //void redefinirSenha(String email);
    //void ativarCadastro(String codigo);*/
}
