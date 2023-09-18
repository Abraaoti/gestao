package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/departamento")
public class DepartamentoControlle {

    private final IDepartamentoService ds;


    @GetMapping("/add")
    public String form(Model model, DepartamentoDTO departamento) {
        model.addAttribute("departamento", departamento);
        return "departamentos/departamento";
    }

    @GetMapping("/lista")
    public String lista() {
        return "departamentos/departamentos";
    }

    @GetMapping("/{id}")
    public DepartamentoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return ds.findById(id);
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute DepartamentoDTO d, RedirectAttributes redir) {
        ds.create(d);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/departamento/add", "departamento", d);
    }

    @PutMapping("/update")
    public ModelAndView update(@RequestBody DepartamentoDTO d, RedirectAttributes redir) {
        ds.create(d);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/departamento/add", "departamento", d);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        ds.delete(id);
    }
    
     @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id) {
       
        model.addAttribute("departamento", ds.findById(id));
        return "perfis/addPerfil";
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ds.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("departamento/departamentos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> departamentos(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.list(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(ds.buscarTodos(request));
    }
}
