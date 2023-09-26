package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.AssistenteAdministrativoDTO;
import br.ind.cmil.gestao.model.services.interfaces.IAssistenteAdministrativoService;
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
@RequestMapping("assistente")
public class AssistenteAdministrativoControlle {

    private final IAssistenteAdministrativoService as;

    @GetMapping("/lista")
    public String list() {
        return "assistente/assistentes";
    }

    @GetMapping("/dados")
    public String form(AssistenteAdministrativoDTO assistente, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("assistente", as.form(assistente, user));
        return "assistente/assistente";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute AssistenteAdministrativoDTO assisntente, RedirectAttributes redir) {
        as.create(assisntente);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/assisnte/dados");
    }

    @PutMapping("/update")
    public ModelAndView update(@ModelAttribute AssistenteAdministrativoDTO a, RedirectAttributes redir) {
        as.create(a);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/assistente/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("assistente", as.findById(id));
        return "assistente/assistente";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        as.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("assistente/assistentes", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> assisntes(HttpServletRequest request) {       
        return ResponseEntity.ok(as.assistentes(request));
    }
}
