package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.AuxiliarAdministrativoDTO;
import br.ind.cmil.gestao.model.services.interfaces.IAuxiliarAdministrativoService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("auxiliar")
public class AuxiliarAdministrativoControlle {

    private final IAuxiliarAdministrativoService as;

    @GetMapping("/lista")
    public String list() {
        return "aux/auxliares";
    }

    @GetMapping("/dados")
    public String form(AuxiliarAdministrativoDTO auxiliar, Model model, @AuthenticationPrincipal User user) {        
        model.addAttribute("auxiliar", as.form(auxiliar, user));
        return "aux/auxiliar";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute AuxiliarAdministrativoDTO c, RedirectAttributes redir) {
        as.create(c);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/auxiliar/dados");
    }

    @PutMapping("/update")
    public ModelAndView update(@ModelAttribute AuxiliarAdministrativoDTO a, RedirectAttributes redir) {
        as.create(a);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/auxiliar/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("auxiliar", as.findById(id));
        return "aux/auxiliar";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        as.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("aux/auxiliares", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> auxiliares(HttpServletRequest request) {       
        return ResponseEntity.ok(as.aux(request));
    }
}
