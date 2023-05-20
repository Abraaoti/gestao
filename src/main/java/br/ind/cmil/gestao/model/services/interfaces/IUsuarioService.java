package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.RegistroUsuarioDTO;
import br.ind.cmil.gestao.model.dto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author abraao
 */
public interface IUsuarioService {

    List<UsuarioDTO> getUsuarios();

    List<UsuarioDTO> search(String searchTerm, int page, int size);

    UsuarioDTO buscarPorId(Long id);

    Boolean existsByUsuarioNome(String nome);

    Boolean existsByUsuarioEmail(String email);

    UsuarioDTO buscarPorNome(String nome);

    UsuarioDTO buscarPorEmail(String email);

    UsuarioDTO buscarPorEmailOUNome(String email, String nome);

    void delete(Long id);

    void save(RegistroUsuarioDTO usuario);

    UsuarioDTO update(UsuarioDTO usuario);

    // UsuarioDTO updatePassword(@AuthenticationPrincipal User user, String s1, String s2, String s3);
    // public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
    // return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    //}
    //Usuario buscarPorIdEPerfil(Long usuarioId, Long[] perfisId);
    // UsuarioDTO preEditarCadastroDadosPessoais(Long usuarioId, Long[] perfisId);
    //UsuarioDTO buscarFoto(String foto) throws FileNotFoundException;
    //void alterarSenha(UsuarioDTO usuario, String s1);
    //UsuarioDTO buscarPorEmailEAtivo(String email);
    //void saveCadastroFinanceiro(Usuario usuario) throws MessagingException;
    //void pedidoRedefinicaoDeSenha(String email);
    //void ativarCadastroFuncionario(String codigo);*/
}
