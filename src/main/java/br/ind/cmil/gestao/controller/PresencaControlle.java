package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.presenca.domain.Presenca;
import br.ind.cmil.gestao.presenca.service.PresencaService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */
@Controller
@RequestMapping("presenca")
public class PresencaControlle {

    private final PresencaService presencaService;
    private final FuncionarioService funcionarioService;

    public PresencaControlle(PresencaService presencaService, FuncionarioService funcionarioService) {
        this.presencaService = presencaService;
        this.funcionarioService = funcionarioService;
    }

   
  

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("funcionarios", funcionarioService.list());
    }

    @GetMapping("/funcionario/{funcionario_id}")
    public String form(@ModelAttribute("presenca") Presenca presenca, Model model) {
        
       
        return "presenca/presenca";
    }

    @GetMapping
    public String getFrequencia() {
        
        return "presenca/presencas";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("presenca") Presenca presenca, RedirectAttributes redir) {
        presencaService.salvar(presenca);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("presenca.create.success"));
        return null;//"redirect:/frequencia/funcionario/" + frequencia.funcionario();

    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("presenca") Presenca presenca, RedirectAttributes redir) {
        presencaService.salvar(presenca);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("presenca.create.success"));
        return null;// "redirect:/frequencia/funcionario/" + frequencia.funcionario();
    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("presenca", presencaService.findById(id));
        return "presenca/presenca";
    }

    @GetMapping("/excluir/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        presencaService.delete(id);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> frequencia(HttpServletRequest request) {
        return ResponseEntity.ok(presencaService.frequencias(request));
    }

}
