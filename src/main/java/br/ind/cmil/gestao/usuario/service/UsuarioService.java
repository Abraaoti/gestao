
package br.ind.cmil.gestao.usuario.service;

import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.model.CadastroExternoDTO;
import br.ind.cmil.gestao.usuario.model.CriarUsuarioDTO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ti
 */
public interface UsuarioService {
      void register(CriarUsuarioDTO request);

    void salvarUsuarioGeral(CriarUsuarioDTO request, String siteURL) throws MessagingException;

    CriarUsuarioDTO buscarPorId(Long id);

    Set<CriarUsuarioDTO> getUsuarios(Pageable pageable);

    public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
        return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    }

    CriarUsuarioDTO preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId);

    void alterarSenha(Usuario usuario, String s1);

    CriarUsuarioDTO buscarEmailAtivo(String email);

    CriarUsuarioDTO buscarPorEmail(String email);

    void redefinirSenha(String email) throws MessagingException;

    void ativarCadastro(String codigo);

    boolean verify(String verificationCode);

    void salvarUsuarioExterno(CadastroExternoDTO usuario) throws MessagingException;

    Map<String, Object> buscarTodos(HttpServletRequest request);

  
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
