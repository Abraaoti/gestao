
package br.ind.cmil.gestao.usuario.controlle;

import br.ind.cmil.gestao.perfil.model.PerfilDTO;
import br.ind.cmil.gestao.perfil.service.PerfilService;
import br.ind.cmil.gestao.usuario.model.CriarUsuarioDTO;
import br.ind.cmil.gestao.usuario.service.UsuarioService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ti
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/u")
public class UsuarioControlle {

    private final UsuarioService service;
    private final PerfilService perfil;

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("perfis", perfis());
    }

    @GetMapping("/add")
    public String form(@ModelAttribute("usuario") CriarUsuarioDTO usuario) {
        return "usuario/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute CriarUsuarioDTO resquest, RedirectAttributes attr) {

        try {
            service.register(resquest);
            attr.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.create.success"));
        } catch (DataIntegrityViolationException ex) {
            attr.addFlashAttribute("falha", "Cadastro não realizado, email já existente.");
        }

        return "redirect:/u/add";

    }

    private List<String> perfis() {
        List<String> perfis = new ArrayList<>();
        for (PerfilDTO perfi : perfil.perfis()) {
            perfis.add(perfi.p());
        }
        return perfis;
    }

    @GetMapping
    public String usuarios() {
        return "usuario/lista";
    }

    @GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> listarUsuariosDatatables(HttpServletRequest request) {

        return ResponseEntity.ok(service.buscarTodos(request));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/usuarios")
    public String listaUsuarios(Model model, Pageable pageable) {
        Set<CriarUsuarioDTO> lis = service.getUsuarios(pageable);
        model.addAttribute("usuarios", lis);
        return "usuario/usuarios";

    }

    @GetMapping("/novo/cadastro")
    public String addExterno(Model model, @ModelAttribute CriarUsuarioDTO usuario) {

        model.addAttribute("usuario", usuario);
        model.addAttribute("perfis", perfil.perfis());

        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String salvarUsuarioExterno(@ModelAttribute CriarUsuarioDTO usuario, HttpServletRequest request, BindingResult result) throws MessagingException {
        try {
            service.salvarUsuarioGeral(usuario, getSiteURL(request));
        } catch (DataIntegrityViolationException ex) {
            result.reject("email", "Ops... Este e-mail já existe na base de dados.");
            return "cadastro";
        }
        return "redirect:/u/cadastro/realizado";
    }

    @GetMapping("/cadastro/realizado")
    public String exibirMensagemDeSucesso() {

        return "fragments/mensagem";
    }

    @GetMapping("/confirmacao/cadastro")
    public ModelAndView AtivarUsuario(@RequestParam("codigo") String codigo, RedirectAttributes attr) {
        service.ativarCadastro(codigo);
        attr.addFlashAttribute("alerta", "sucesso");
        attr.addFlashAttribute("titulo", "Cadastro Ativado!");
        attr.addFlashAttribute("texto", "Parabéns, seu cadastro está ativo.");
        attr.addFlashAttribute("subtexto", "Singa com seu login/senha");
        return new ModelAndView("login");
    }

    @GetMapping("/editar/credenciais/usuario/{id}")
    public String buscarCredenciaisPorId(Model model, @PathVariable("id") Long id) {
        model.addAttribute("usuario", service.buscarPorId(id));
        model.addAttribute("perfis", perfis());
        
        
        return "usuario/cadastro";
    }

    @GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
    public String buscarDadosPorUsuarioIdEPerfilId(Model model, @PathVariable("id") Long usuarioId, @PathVariable("perfis") Long[] perfisId) {
        /**
         * UsuarioRequest us = service.preEditarCadastroDadosPessoais(usuarioId,
         * perfisId); if (us.perfis().contains(new
         * Perfil(TipoPerfil.convertPerfilValue("admin"))) &&
         * us.perfis().contains(new
         * Perfil(TipoPerfil.convertPerfilValue("administrador")))) {
         * model.addAttribute("usuario", us); model.addAttribute("perfis",
         * perfil.perfis()); return new ModelAndView("usuario/cadastro"); } else
         * if (us.perfis().contains(new
         * Perfil(TipoPerfil.convertPerfilValue("administrador")))) {
         * Administrador administrador = ds.buscarPorUsuarioId(usuarioId);
         *
         * model.addAttribute("usuario", us); model.addAttribute("perfis",
         * perfil.perfis()); return (administrador.getId() == null) ? new
         * ModelAndView("administrador/cadastro", "administrador", new
         * Administrador(new Usuario(usuarioId))) : new
         * ModelAndView("administrador/cadastro", "administrador",
         * administrador);
         *
         * }
         * else if (us.perfis().contains(new
         * Perfil(TipoPerfil.convertPerfilValue("assistente")))) {
         * AssistenteAdministrativo administrador =
         * assistenteService.buscarPorUsuarioId(usuarioId);
         *
         * model.addAttribute("usuario", us); model.addAttribute("perfis",
         * perfil.perfis()); return (administrador.getId() == null) ? new
         * ModelAndView("assistente/assistente", "assistente", new
         * AssistenteAdministrativo(new Usuario(usuarioId))) : new
         * ModelAndView("assistente/assistente", "assistente", administrador);
         *
         * }
         * else if (us.perfis().contains(new
         * Perfil(TipoPerfil.convertPerfilValue("auxiliar")))) {
         * AuxiliarAdministrativo auxiliar =
         * auxiliarService.buscarPorUsuarioId(usuarioId);
         *
         * model.addAttribute("usuario", us); model.addAttribute("perfis",
         * perfil.perfis()); return (auxiliar.getId() == null) ? new
         * ModelAndView("assistente/assistente", "assistente", new
         * AssistenteAdministrativo(new Usuario(usuarioId))) : new
         * ModelAndView("assistente/assistente", "assistente", auxiliar);
         *
         * }
         * else if (us.perfis().contains(new
         * Perfil(TipoPerfil.convertPerfilValue("usuário")))) {
         *
         * model.addAttribute("status", 403); model.addAttribute("error", "Área
         * Restrita"); model.addAttribute("message", "Os dados de pacientes são
         * restritos a ele."); return new ModelAndView("error"); }*
         */
        return "redirect:/u/lista";
    }

    @GetMapping("/editar/senha")
    public String editarSenha() {

        return "usuario/editar-senha";
    }

    @PostMapping("/confirmar/senha")
    public String editarSenha(@RequestParam("senha1") String s1, @RequestParam("senha2") String s2,
            @RequestParam("senha3") String s3, @AuthenticationPrincipal User user,
            RedirectAttributes attr) {

        if (!s1.equals(s2)) {
            attr.addFlashAttribute("falha", "Senhas não conferem, tente novamente");
            return "redirect:/u/editar/senha";
        }

        CriarUsuarioDTO u = service.buscarPorEmail(user.getUsername());
        if (!UsuarioService.isSenhaCorreta(s3, u.password())) {
            attr.addFlashAttribute("falha", "Senha atual não confere, tente novamente");
            return "redirect:/u/editar/senha";
        }

        //service.alterarSenha(u, s1);
        attr.addFlashAttribute("sucesso", "Senha alterada com sucesso.");
        return "redirect:/u/editar/senha";
    }

    // abre a pagina de pedido de redefinicao de senha
    @GetMapping("/p/redefinir/senha")
    public String pedidoRedefinirSenha() {

        return "usuario/pedido-recuperar-senha";
    }

    // form de pedido de recuperar senha
    @GetMapping("/p/recuperar/senha")
    public String redefinirSenha(String email, ModelMap model) throws MessagingException {
        service.redefinirSenha(email);
        model.addAttribute("sucesso", "Em instantes você reberá um e-mail para "
                + "prosseguir com a redefinição de sua senha.");
        //model.addAttribute("usuario", new Usuario(email));
        model.addAttribute("usuario", null);
        return "usuario/recuperar-senha";
    }

    // salvar a nova senha via recuperacao de senha
    @PostMapping("/p/nova/senha")
    public String confirmacaoDeRedefinicaoDeSenha(CriarUsuarioDTO usuario, ModelMap model) {
        /**
         * Usuario u = service.buscarPorEmail(usuario.getEmail()); if
         * (!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())) {
         * model.addAttribute("falha", "Código verificador não confere.");
         * return "usuario/recuperar-senha"; } u.setCodigoVerificador(null);
         * service.alterarSenha(u, usuario.getSenha());*
         */
        model.addAttribute("alerta", "sucesso");
        model.addAttribute("titulo", "Senha redefinida!");
        model.addAttribute("texto", "Você já pode logar no sistema.");
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {

        return "fragments/mensagem";
    }

    @GetMapping("/administrador")
    public String administrador() {

        return "fragments/mensagem";
    }

    @GetMapping("/assistente")
    public String assistente() {

        return "fragments/mensagem";
    }
}

