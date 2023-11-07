package br.ind.cmil.gestao.web;

import br.ind.cmil.gestao.dto.CargoDTO;
import br.ind.cmil.gestao.model.entidades.Cargo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
import br.ind.cmil.gestao.model.services.interfaces.CargoService;

/**
 *
 * @author abraao
 */

@RequiredArgsConstructor
@Controller
@RequestMapping("cargo")
public class CargoControlle {

    private final CargoService cs;

    @GetMapping("/lista")
    public String list() {
        return "cargos/cargos";
    }

    @GetMapping("/add")
    public String form(Model model,Cargo cargo) {
        model.addAttribute("cargo", cargo);
        return "cargos/cargo";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Cargo cargo, RedirectAttributes redir) {
        cs.salvar(cargo);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/cargo/add");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Cargo cargo, RedirectAttributes redir) {
        cs.salvar(cargo);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/cargo/add");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("cargo", cs.findById(id));
        return "cargos/cargo";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable @NotNull @Positive Long id) {
        Map<String, Object> model = new HashMap<>();
        cs.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("cargos/cargos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> cargos(HttpServletRequest request) {       
        return ResponseEntity.ok(cs.buscarTodos(request));
    }
}
