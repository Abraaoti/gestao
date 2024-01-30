package br.ind.cmil.gestao.assistente.controlle;

import br.ind.cmil.gestao.assistente.model.AssistenteAdministrativoDTO;
import br.ind.cmil.gestao.assistente.service.AssistenteAdministrativoService;
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
import br.ind.cmil.gestao.usuario.service.UsuarioService;

/**
 *
 * @author abraao
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("assistente")
public class AssistenteAdministrativoControlle {

    private final AssistenteAdministrativoService assistenteService;
    private final UsuarioService usuarioService;

    @GetMapping("/lista")
    public String list() {
        return "assistente/assistentes";
    }

    @GetMapping("/dados")
    public String form(@ModelAttribute("assistente") AssistenteAdministrativoDTO assistente, Model model, @AuthenticationPrincipal User user) {
     
        model.addAttribute("assistente", assistenteService.buscarPorEmail(assistente, user));
    
        return "assistente/assistente";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("assistente") AssistenteAdministrativoDTO assistenteAdministrativoDTO, RedirectAttributes redir, @AuthenticationPrincipal User user) {
      
        assistenteService.create(assistenteAdministrativoDTO);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/assistente/dados");
    }

    @PutMapping("/update")
    public ModelAndView update(@ModelAttribute AssistenteAdministrativoDTO a, RedirectAttributes redir) {
        assistenteService.create(a);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/assistente/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("assistente", assistenteService.buscarPorUsuarioId(id));
        return "assistente/assistente";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        assistenteService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("assistente/assistentes", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> assisntes(HttpServletRequest request) {
        return ResponseEntity.ok(assistenteService.assistentes(request));
    }
}
