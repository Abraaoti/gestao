package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioFrequenciaDTO;
import br.ind.cmil.gestao.services.FrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioFrequenciaService;
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
@RequestMapping("funcionarioFrequencias")
public class FuncionarioFrequenciaControlle {

    private final FrequenciaService frequenciaService;
    private final FuncionarioService funcionarioService;
    private final FuncionarioFrequenciaService funcionarioFrequenciaService;

    public FuncionarioFrequenciaControlle(FrequenciaService frequenciaService, FuncionarioService funcionarioService, FuncionarioFrequenciaService funcionarioFrequenciaService) {
        this.frequenciaService = frequenciaService;
        this.funcionarioService = funcionarioService;
        this.funcionarioFrequenciaService = funcionarioFrequenciaService;
    }

    @GetMapping
    public String getFrequencia() {

        return "frequencia/frequencias";
    }

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("frequencias", frequenciaService.getFrequencias());
        model.addAttribute("funcionarios", funcionarioService.funcionarios());
    }

  

    @GetMapping("/add/funcionario/{funcionarioId}/frequencia/{frequenciaId}")
    public String openFrom(@PathVariable("funcionarioId") Long funcionarioId, @PathVariable("frequenciaId") Long frequenciaId, Model model, @ModelAttribute("funcionarioFrequencia") FuncionarioFrequenciaDTO funcionarioFrequenciaDTO) {
        model.addAttribute("funcionarioFrequencia", funcionarioFrequenciaService.form(funcionarioId, frequenciaId, funcionarioFrequenciaDTO));
        return "frequencia/add";
    }

    @PostMapping("/add")
    public String salvar(@ModelAttribute("funcionarioFrequencia") FuncionarioFrequenciaDTO funcionarioFrequenciaDTO, RedirectAttributes redir) {
        funcionarioFrequenciaService.salvar(funcionarioFrequenciaDTO);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return "redirect:/frequencias";

    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("funcionarioFrequencia") FuncionarioFrequenciaDTO funcionarioFrequenciaDTO, RedirectAttributes redir) {
        funcionarioFrequenciaService.salvar(funcionarioFrequenciaDTO);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return "redirect:/frequencias";
    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("funcionarioFrequencia", frequenciaService.buscarPorId(id));
        return "frequencia/frequencia";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id, final RedirectAttributes redirectAttributes) {
        funcionarioFrequenciaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("frequencia.delete.success"));
        return "redirect:/frequencias";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> frequencia(HttpServletRequest request) {
        return ResponseEntity.ok(funcionarioFrequenciaService.buscarFuncionarioFrequencias(request));
    }

}
