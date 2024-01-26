package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.departamento.model.DepartamentoDTO;
import br.ind.cmil.gestao.departamento.service.DepartamentoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/departamentos")
public class DepartamentoControlle {

    private final DepartamentoService departamentoService;


    @GetMapping("/add")
    public String form(@ModelAttribute("departamento")DepartamentoDTO departamento) {
        return "departamentos/departamento";
    }

    @GetMapping
    public String lista() {
        return "departamentos/departamentos";
    }

    @GetMapping("/{id}")
    public DepartamentoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return departamentoService.findById(id);
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("departamento") DepartamentoDTO departamentoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "departamento/add";
        }
        departamentoService.salvar(departamentoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("departamento.create.success"));
        return "redirect:/departamentos";
    }
      @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id) {
       
        model.addAttribute("departamento", departamentoService.findById(id));
        return "departamentos/departamento";
    }


    @PostMapping("/update")
    public String editar(@ModelAttribute("departamento") @Valid final DepartamentoDTO departamentoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "departamento/edit";
        }
        departamentoService.salvar(departamentoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("departamento.update.success"));
        return "redirect:/departamentos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = departamentoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            departamentoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("departamento.delete.success"));
        }
        return "redirect:/departamentos";
    }
    
   
   

    @GetMapping("/datatables/server")
    public ResponseEntity<?> departamentos(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.lista(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(departamentoService.buscarTodos(request));
    }
}
