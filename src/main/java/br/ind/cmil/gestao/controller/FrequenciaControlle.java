package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.domain.Frequencia;
import br.ind.cmil.gestao.domain.Presenca;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import br.ind.cmil.gestao.services.FrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioService;
import br.ind.cmil.gestao.services.PresencaService;
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
@RequestMapping("frequencia")
public class FrequenciaControlle {

    private final FrequenciaService frequenciaService;
    private final FuncionarioService funcionarioService;

    public FrequenciaControlle(FrequenciaService presencaService, FuncionarioService funcionarioService) {
        this.frequenciaService = presencaService;
        this.funcionarioService = funcionarioService;
    }
  

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("funcionarios", funcionarioService.list());
    }

    @GetMapping("/funcionario/{funcionario_id}")
    public String form(@ModelAttribute("frequencia") FrequenciaDTO presenca, Model model) {
        
       
        return "frequencia/frequencia";
    }

    @GetMapping
    public String getFrequencia() {
        
        return "frequencia/frequencias";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("frequencia") FrequenciaDTO frequencia, RedirectAttributes redir) {
        frequenciaService.salvar(frequencia);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return null;//"redirect:/frequencia/funcionario/" + frequencia.funcionario();

    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("frequencia") FrequenciaDTO frequencia, RedirectAttributes redir) {
        frequenciaService.salvar(frequencia);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return null;// "redirect:/frequencia/funcionario/" + frequencia.funcionario();
    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("frequencia", frequenciaService.findById(id));
        return "frequencia/frequencia";
    }

    @GetMapping("/excluir/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        frequenciaService.delete(id);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> frequencia(HttpServletRequest request) {
        return ResponseEntity.ok(frequenciaService.frequencias(request));
    }

}
