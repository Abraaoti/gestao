package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import br.ind.cmil.gestao.services.FrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */
@Controller
@RequestMapping("frequencias")
public class FrequenciaControlle {
    
    private final FrequenciaService frequenciaService;
    private final FuncionarioService funcionarioService;
    
    public FrequenciaControlle(FrequenciaService presencaService, FuncionarioService funcionarioService) {
        this.frequenciaService = presencaService;
        this.funcionarioService = funcionarioService;
    }
    
    @GetMapping
    public String getFrequencia(Model model) {
        // model.addAttribute("frequencias", frequenciaService.getFrequencias());
        return "frequencia/frequencias";
    }
    
    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("funcionariosValues", funcionarioService.funcionarios());
        model.addAttribute("allTypes", TipoFrequencia.tipoFrequencias());
    }
    
    @GetMapping("/add")
    public String add(@ModelAttribute("frequencia") FrequenciaDTO frequenciaDTO) {
        
        return "frequencia/add";
    }

    @GetMapping("/add/funcionario/{pessoa_id}")
    public String openFrom(Model model, @PathVariable("pessoa_id") Long pessoa, @ModelAttribute("frequencia") FrequenciaDTO frequenciaDTO) {
        model.addAttribute("frequencia", frequenciaService.form(pessoa, frequenciaDTO));
        return "frequencia/frequencia";
    }
    
    @PostMapping("/addFrequenciaFuncionario")
    public String salvar(@ModelAttribute("frequencia") FrequenciaDTO frequenciaDTO, RedirectAttributes redir) {        
        frequenciaService.salvar(frequenciaDTO);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return "redirect:/frequencias";
        
    }
    
    @PostMapping("/editar")
    public String editar(@ModelAttribute("frequencia") FrequenciaDTO frequenciaDTO, RedirectAttributes redir) {
        frequenciaService.salvar(frequenciaDTO);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return "redirect:/frequencias";
    }
    
    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("frequencia", frequenciaService.findById(id));
        return "frequencia/frequencia";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id, final RedirectAttributes redirectAttributes) {
        frequenciaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("frequencia.delete.success"));
        return "redirect:/frequencias";
    }
    
    @GetMapping("/datatables/server")
    public ResponseEntity<?> frequencia(HttpServletRequest request) {
        return ResponseEntity.ok(frequenciaService.funcionariosFrequencias(request));
    }
    
}
