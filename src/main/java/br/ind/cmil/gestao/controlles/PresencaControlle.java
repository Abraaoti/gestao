package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.PresencaDTO;
import br.ind.cmil.gestao.model.services.interfaces.IPresencaService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/presenca")
public class PresencaControlle {

    private final IPresencaService ps;

    @GetMapping("/add")
    public String form(Model model, @ModelAttribute PresencaDTO presenca) {
        model.addAttribute("presenca", presenca);
        return "/rh/funcionario/presencas/presenca";
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute PresencaDTO presenca, RedirectAttributes redir) {
        ps.create(presenca);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/presenca/add", "presenca", presenca);
    }

    @GetMapping("/lista")
    public String lista() {

        return "/rh/funcionario/presencas/presencas";

    }

    @PostMapping("/editar")
    public ModelAndView editarPerfil(@ModelAttribute PresencaDTO presenca, RedirectAttributes redir) {
        ps.create(presenca);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("/rh/funcionario/presencas/presenca");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("presenca", ps.findById(id));
        return "/rh/funcionario/presencas/presenca";
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ps.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("/rh/funcionario/presencas/presencas", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> presencas(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.list(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(ps.presencas(request));
    }

}
