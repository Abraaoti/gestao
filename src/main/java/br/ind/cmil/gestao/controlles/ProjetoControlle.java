package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.ProjetoDTO;
import br.ind.cmil.gestao.model.services.interfaces.IProjetoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("projeto")
public class ProjetoControlle {

    private final IProjetoService ps;

    @GetMapping("/lista")
    public String list() {
        return "projetos/projetos";
    }

    @GetMapping("/add")
    public String form(@ModelAttribute ProjetoDTO projeto, Model model) {
        model.addAttribute("projeto", projeto);
        return "projetos/projeto";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute ProjetoDTO projeto, RedirectAttributes redir) {
        ps.create(projeto);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/projeto/add");
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute ProjetoDTO projeto, RedirectAttributes redir) {
        ps.create(projeto);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/projeto/add");
    }

   

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id) {

        model.addAttribute("projeto", ps.findById(id));
        return "projetos/projeto";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ps.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("projetos/projetos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> projetos(HttpServletRequest request) {       
        return ResponseEntity.ok(ps.buscarTodos(request));
    }
}
