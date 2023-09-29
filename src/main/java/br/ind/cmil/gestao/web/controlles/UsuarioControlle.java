package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.dto.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.services.interfaces.IAdministradorService;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
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
    private final IAdministradorService ds;

    @GetMapping("/abrir/form")
    public String abrirFormGeral(Model model, @ModelAttribute RegistrarUsuario usuario) {

        model.addAttribute("usuario", usuario);
        model.addAttribute("perfis", perfis.perfis());
        return "usuario/cadastro";
    }

    @GetMapping("/lista")
    public String listarUsuarios() {

        return "usuario/lista";
    }

    @GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> listarUsuariosDatatables(HttpServletRequest request) {

        return ResponseEntity.ok(service.buscarTodos(request));
    }

    @PostMapping("/registrar")
    public ModelAndView registrarUsuario(@ModelAttribute RegistrarUsuario usuario, RedirectAttributes attr) {
        
        Set<Perfil> perfil = this.perfis.perfis(usuario.perfis());
        Set<String> admin_usuario = new HashSet<>();
        admin_usuario.add(perfis.tipoPerfil("admin"));
        admin_usuario.add(perfis.tipoPerfil("usuário"));

        Set<String> adm_usuario = new HashSet<>();
        
        
        adm_usuario.add(perfis.tipoPerfil("administrador"));
        adm_usuario.add(perfis.tipoPerfil("usuário"));
       
        if (usuario.perfis().size() > 2 || perfil.containsAll(admin_usuario) || perfil.containsAll(adm_usuario)) {

            attr.addFlashAttribute("falha", "usuário não pode ser Admin e/ou administrador.");
            attr.addFlashAttribute("usuario", usuario);
           
        } else {
            try {
                service.register(usuario);
                attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
            } catch (DataIntegrityViolationException ex) {
                attr.addFlashAttribute("falha", "Cadastro não realizado, email já existente.");
            }
        }

        return new ModelAndView("redirect:/u/abrir/form");

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
    public String preEditarCredenciais(Model model, @PathVariable("id") Long id) {

        model.addAttribute("usuario", service.buscarPorId(id));
        model.addAttribute("perfis", perfis.perfis());
        return "usuario/cadastro";
    }

    @GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
    public ModelAndView dadosPessoais(Model model, @PathVariable("id") Long usuarioId, @PathVariable("perfis") Long[] perfisId) {
        RegistrarUsuario us = service.preEditarCadastroDadosPessoais(usuarioId, perfisId);
        if (us.perfis().contains(new Perfil(perfis.tipoPerfil("admin"))) && us.perfis().contains(new Perfil(perfis.tipoPerfil("administrador")))) {
            model.addAttribute("usuario", us);
            model.addAttribute("perfis", perfis.perfis());
            return new ModelAndView("usuario/cadastro");
        } else if (us.perfis().contains(new Perfil(perfis.tipoPerfil("administrador")))) {
            AdministradorDTO administrador = ds.buscarPorUsuarioId(usuarioId);
            
            
            model.addAttribute("usuario", us);
            model.addAttribute("perfis", perfis.perfis());
            return (administrador.id() == null ) ? new ModelAndView("administrador/cadastro", "administrador", new Administrador(new Usuario(usuarioId)))
    				: new ModelAndView("administrador/cadastro", "administrador", administrador);
       
        } else if (us.perfis().contains(new Perfil(perfis.tipoPerfil("usuário")))) {

            model.addAttribute("status", 403);
            model.addAttribute("error", "Área Restrita");
            model.addAttribute("message", "Os dados de pacientes são restritos a ele.");
            return new ModelAndView("error");
        }
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
