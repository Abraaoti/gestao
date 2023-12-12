package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.model.dto.CentroCustoDTO;
import br.ind.cmil.gestao.services.CentroCustoService;
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
@RequestMapping("/centroCusto")
public class CentroCustoControlle {

    private final CentroCustoService centroCustoService;

    @GetMapping
    public String list() {
        return "centro_custo/centroCustos";
    }

    @GetMapping("/add")
    public String form(@ModelAttribute("centroCusto") CentroCustoDTO centroCusto) {
        return "centro_custo/centroCusto";
    }

    @PostMapping("/salvar")
    public String save(@ModelAttribute("centroCusto") @Valid CentroCustoDTO centroCusto, RedirectAttributes redir) {
        centroCustoService.create(centroCusto);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return "redirect:/centroCusto/add";
    }

    @PostMapping("/editar")
    public String update(@ModelAttribute CentroCustoDTO centroCusto, RedirectAttributes redir) {
        centroCustoService.create(centroCusto);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return "redirect:/centroCusto/add";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id) {
        model.addAttribute("centroCusto", centroCustoService.get(id));
        return "centro_custo/centroCusto";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        centroCustoService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("centro_custo/centroCustos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> projetos(HttpServletRequest request) {
        return ResponseEntity.ok(centroCustoService.buscarTodos(request));
    }
}
