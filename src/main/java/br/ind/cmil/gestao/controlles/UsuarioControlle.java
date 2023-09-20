package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.RegistrarUsuario;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
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
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/u")
public class UsuarioControlle {

    private final IUsuarioService service;
    private final IPerfilService perfis;

    @GetMapping("/abrir/form")
    public String abrirFormGeral(Model model, @ModelAttribute RegistrarUsuario usuario) {

        model.addAttribute("usuario", usuario);
        model.addAttribute("perfis", perfis.perfis());
        return "usuario/cadastro";
    }

    @PostMapping(path = "/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") RegistrarUsuario usuario, HttpServletRequest request, BindingResult result) throws MessagingException {

        try {

            service.register(usuario, getSiteURL(request));
        } catch (RuntimeException ex) {
            result.reject("email", "Ups... Este e-mail já existe na base de dados.");
          return "usuario/cadastro";
        }
        return "redirect:/u/abrir/form";

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
    public String list(Model model, Pageable pageable) {
        Set<RegistrarUsuario> lis = service.getUsuarios(pageable);
        model.addAttribute("usuarios", lis);
        return "usuario/usuarios";

    }

    @GetMapping("/novo/cadastro")
    public String novoCadastro(Model model, @ModelAttribute RegistrarUsuario usuario) {

        model.addAttribute("usuario", usuario);
        model.addAttribute("perfis", perfis.perfis());

        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String salvarCadastroAuxiliar(@ModelAttribute RegistrarUsuario usuario, HttpServletRequest request, BindingResult result) throws MessagingException {
        try {
            service.salvarUsuarioGeral(usuario, getSiteURL(request));
        } catch (DataIntegrityViolationException ex) {
            result.reject("email", "Ops... Este e-mail já existe na base de dados.");
            return "cadastro";
        }
        return "redirect:/u/cadastro/realizado";
    }

    @GetMapping("/cadastro/realizado")
    public String cadastroRealizado() {

        return "fragments/mensagem";
    }

    @GetMapping("/confirmacao/cadastro")
    public ModelAndView confirmarCadastro(@RequestParam("codigo") String codigo, RedirectAttributes attr) {
        service.ativarCadastro(codigo);
        attr.addFlashAttribute("alerta", "sucesso");
        attr.addFlashAttribute("titulo", "Cadastro Ativado!");
        attr.addFlashAttribute("texto", "Parabéns, seu cadastro está ativo.");
        attr.addFlashAttribute("subtexto", "Singa com seu login/senha");
        return new ModelAndView("login");
    }

    @GetMapping("/editar/credenciais/usuario/{id}")
    public ModelAndView preEditarCredenciais(@PathVariable("id") Long id) {

        return new ModelAndView("usuario/cadastro", "usuario", service.buscarPorId(id));
    }

    @GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
    public ModelAndView dadosPessoais(@PathVariable("id") Long usuarioId, @PathVariable("perfis") Long[] perfisId) {
        //RegistrarUsuario us = service.buscarPorIdEPerfis(usuarioId, perfisId);

        return new ModelAndView("redirect:/u/lista");
    }

    @GetMapping("/editar/senha")
    public String abrirEditarSenha() {

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

        RegistrarUsuario u = service.buscarPorEmail(user.getUsername());
        if (!IUsuarioService.isSenhaCorreta(s3, u.password())) {
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
    public String confirmacaoDeRedefinicaoDeSenha(RegistrarUsuario usuario, ModelMap model) {
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

}
