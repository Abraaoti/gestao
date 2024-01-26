package br.ind.cmil.gestao.controller;


import br.ind.cmil.gestao.administrador.domain.Administrador;
import br.ind.cmil.gestao.administrador.model.AdministradorDTO;
import br.ind.cmil.gestao.administrador.services.AdministradorService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ind.cmil.gestao.usuario.service.UsuarioService;

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("administrador")
public class AdministradorControlle {

    private final AdministradorService administradorService;
    private final UsuarioService usuarioService;

    @GetMapping("/lista")
    public String list() {
        return "administrador/administradores";
    }

    @GetMapping("/dados")
    public String form(Administrador administradorDTO, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("administrador", administradorService.checarDados(user.getUsername()));
        return "administrador/cadastro";
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute AdministradorDTO administradorDTO, @AuthenticationPrincipal User user, RedirectAttributes redir) {

        administradorService.salvar(administradorDTO);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        redir.addFlashAttribute("administrador", administradorDTO);
        return new ModelAndView("redirect:/administrador/dados");
    }

    @PutMapping("/editar")
    public ModelAndView update(@ModelAttribute AdministradorDTO a, RedirectAttributes redir) {
        administradorService.salvar(a);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/administrador/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("administrador", administradorService.buscarPorUsuarioId(id));
        return "administrador/cadastro";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        administradorService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("administrador/administradores", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> administradores(HttpServletRequest request) {
        return ResponseEntity.ok(administradorService.administradores(request));
    }
}
