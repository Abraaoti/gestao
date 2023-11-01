package br.ind.cmil.gestao.web;

import br.ind.cmil.gestao.model.dto.LotacaoDTO;
import br.ind.cmil.gestao.model.services.interfaces.LotacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
@RequestMapping("lotacao")
public class LotacaoControlle {

    private final LotacaoService ls;

    @GetMapping("/lista")
    public String list() {
        return "lotacao/lotacoes";
    }

    @GetMapping("/add")
    public String form(LotacaoDTO lotacao, Model model) {
        model.addAttribute("lotacao", lotacao);
        return "lotacao/lotacao";
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute @Valid LotacaoDTO lotacao, RedirectAttributes redir) {
        ls.save(lotacao);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/lotacao/add");
    }
    @PostMapping("/editar")
    public ModelAndView update(@ModelAttribute LotacaoDTO lotacao, RedirectAttributes redir) {
        ls.save(lotacao);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/lotacao/add");
    }

   

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id) {
        model.addAttribute("lotacao", ls.findById(id));
        return "lotacao/lotacao";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ls.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("lotacao/lotacoes", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> projetos(HttpServletRequest request) {   
        return ResponseEntity.ok(ls.buscarTodos(request));
    }
}
