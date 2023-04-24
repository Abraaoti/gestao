package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.UsuarioDTO;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author abraao
 */
public interface IUsuarioService extends UserDetailsService {

    List<UsuarioDTO> list();

    UsuarioDTO save(UsuarioDTO usuario);

    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO updatePassword(@AuthenticationPrincipal User user, String s1, String s2, String s3);

    public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
        return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    }

    //Usuario buscarPorIdEPerfil(Long usuarioId, Long[] perfisId);
    UsuarioDTO preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId);

    UsuarioDTO buscarPorEmail(String email);

    //UsuarioDTO buscarFoto(String foto) throws FileNotFoundException;
    void delete(Long id);

    void alterarSenha(UsuarioDTO usuario, String s1);

    UsuarioDTO update(UsuarioDTO usuario);

    UsuarioDTO buscarPorEmailEAtivo(String email);

    //void saveCadastroFinanceiro(Usuario usuario) throws MessagingException;
    void pedidoRedefinicaoDeSenha(String email);

    void ativarCadastroFuncionario(String codigo);
}
