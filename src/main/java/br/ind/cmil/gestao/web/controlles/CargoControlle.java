package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.services.interfaces.ICargoService;
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
@RequestMapping("cargo")
public class CargoControlle {

    private final ICargoService cs;

    @GetMapping("/lista")
    public String list() {
        return "rh/cargos/cargos";
    }

    @GetMapping("/add")
    public String form(CargoDTO cargo, Model model) {
        model.addAttribute("cargo", cargo);
        return "rh/cargos/cargo";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute CargoDTO c, RedirectAttributes redir) {
        cs.create(c);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/cargo/add");
    }

    @PutMapping("/update")
    public ModelAndView update(@ModelAttribute CargoDTO c, RedirectAttributes redir) {
        cs.create(c);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/cargo/add");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("cargo", cs.findById(id));
        return "rh/cargos/cargo";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        cs.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("rh/cargos/cargos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> perfis(HttpServletRequest request) {       
        return ResponseEntity.ok(cs.buscarTodos(request));
    }
}
