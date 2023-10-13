package br.ind.cmil.gestao.web;

import br.ind.cmil.gestao.model.dto.AdministradorDTO;
import br.ind.cmil.gestao.model.services.interfaces.IAdministradorService;
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

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("administrador")
public class AdministradorControlle {

    private final IAdministradorService as;

    @GetMapping("/lista")
    public String list() {
        return "administrador/administradores";
    }

    @GetMapping("/dados")
    public String form(AdministradorDTO administrador, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("administrador", as.form(administrador, user));
        return "administrador/cadastro";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute AdministradorDTO c, RedirectAttributes redir) {
        as.create(c);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/administrador/dados");
    }

    @PutMapping("/update")
    public ModelAndView update(@ModelAttribute AdministradorDTO a, RedirectAttributes redir) {
        as.create(a);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/administrador/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("administrador", as.findById(id));
        return "administrador/cadastro";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        as.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("administrador/administradores", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> administradores(HttpServletRequest request) {       
        return ResponseEntity.ok(as.administradores(request));
    }
}
