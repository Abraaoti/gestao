package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.LoginDTO;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.RegistroUsuarioDTO;
import br.ind.cmil.gestao.model.dto.UsuarioDTO;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@Validated
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioControlle {

    private final IUsuarioService us;
    private final IPerfilService ps;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioControlle(IUsuarioService us, IPerfilService ps) {
        this.us = us;
        this.ps = ps;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.emailOUNome(), loginDTO.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @GetMapping("/lista")
    public List<UsuarioDTO> list() {
        return us.getUsuarios();
    }

    @GetMapping("/search")
    public List<UsuarioDTO> search(@RequestParam("searchTerm") String searchTerm,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return us.getUsuarios();
    }

    @GetMapping("/perfis")
    public List<PerfilDTO> getperfis() {
        return this.ps.list();
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable @NotNull @Positive Long id) {
        return us.buscarPorId(id);
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid @NotNull RegistroUsuarioDTO usuariodto) {
        us.save(usuariodto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public UsuarioDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull UsuarioDTO uDTO) {
        return us.update(uDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        us.delete(id);
    }
    /**
     * @GetMapping(path = UsuarioUri.CREDENCIAIS) public UsuarioDTO
     * preEditarCredenciais(ModelMap model, @PathVariable("id") Long id) {
     * return us.buscarPorId(id); }
     *
     * @GetMapping(path = UsuarioUri.EDITAR_PERFIL) public UsuarioDTO
     * preEditarCadastroDadosPessoas(@PathVariable("id") Long usuarioId,
     * @PathVariable("id") Long[] perfisId) { return
     * us.preEditarCadastroDadosPessoais(usuarioId, perfisId); }
     *
     * @GetMapping(path = UsuarioUri.EDITAR_SENHA) public String pre() { return
     * "editar-senha"; }
     *
     * @PostMapping(path = UsuarioUri.CONFIRMASENHA) public UsuarioDTO
     * confirmarSenha(ModelMap model, @RequestParam("senha1") String s1,
     * @RequestParam("senha2") String s2, @RequestParam("senha3") String s3,
     * @AuthenticationPrincipal User user) { return us.updatePassword(user, s1,
     * s2, s3); }
     *
     * @GetMapping(path = UsuarioUri.MENSAGEM) public String cadastroRealizado()
     * {
     *
     * return "fragments/mensagem"; }
     *
     * // recebe a requisicao de confirmacao de cadastro
     * @GetMapping(path = UsuarioUri.CONFIRMACADASTRO) public String
     * respostaConfirmacaoCadastroPaciente(@RequestParam("codigo") String
     * codigo, RedirectAttributes attr) { us.ativarCadastroFuncionario(codigo);
     * attr.addFlashAttribute("alerta", "sucesso");
     * attr.addFlashAttribute("titulo", "Cadastro Ativado!");
     * attr.addFlashAttribute("texto", "Parabéns, seu cadastro está ativo.");
     * attr.addFlashAttribute("subtexto", "Siga com seu login e senha"); return
     * "login"; }
     *
     * // abre a pagina de pedido de redefinicao de senha
     * @GetMapping(path = UsuarioUri.REDEFINIRSENHA) public String
     * pedidoRedefinirSenha() {
     *
     * return "usuario/pedido-recuperar-senha"; }
     *
     * // form de pedido de recuperar senha
     * @GetMapping(path = UsuarioUri.RECUPERARSENHA) public String
     * redefinirSenha(String email, ModelMap model) throws MessagingException {
     * us.pedidoRedefinicaoDeSenha(email); model.addAttribute("sucesso", "Em
     * instantes você reberá um e-mail para " + "prosseguir com a redefinição de
     * sua senha."); model.addAttribute("usuario", new Usuario(Long.MIN_VALUE));
     * return "recuperar-senha"; }
     *
     * // salvar a nova senha via recuperacao de senha
     * @PostMapping(path = UsuarioUri.NOVASENHA) public String
     * confirmacaoDeRedefinicaoDeSenha(UsuarioDTO usuario, ModelMap model) {
     * UsuarioDTO u = us.buscarPorEmail(usuario.email()); if
     * (!usuario.verificador().equals(u.verificador())) {
     * model.addAttribute("falha", "Código verificador não confere."); return
     * "usuario/recuperar-senha"; } u.verificador(); us.alterarSenha(u,
     * usuario.password()); model.addAttribute("alerta", "sucesso");
     * model.addAttribute("titulo", "Senha redefinida!");
     * model.addAttribute("texto", "Você já pode logar no sistema."); return
     * "login"; }
     *
     */
}
