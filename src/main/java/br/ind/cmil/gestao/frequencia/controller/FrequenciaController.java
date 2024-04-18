package br.ind.cmil.gestao.frequencia.controller;

import br.ind.cmil.gestao.frequencia.model.FrequenciaDTO;
import br.ind.cmil.gestao.frequencia.service.FrequenciaService;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraaocalelessocassi
 */
@Controller
@RequestMapping("/frequencias")
public class FrequenciaController {

    private final FrequenciaService frequenciaService;
    private final FuncionarioService funcionarioService;

    public FrequenciaController(FrequenciaService frequenciaService, FuncionarioService funcionarioService) {
        this.frequenciaService = frequenciaService;
        this.funcionarioService = funcionarioService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("funcionarios", funcionarioService.list());
    }

    @GetMapping
    public String list(final Model model) {
        // model.addAttribute("frequencias", frequenciaService.getFrequencias());
        return "frequencia/frequencias";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("frequencia") final FrequenciaDTO frequenciaDTO) {
        return "frequencia/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("frequencia")  FrequenciaDTO frequenciaDTO, final RedirectAttributes redirectAttributes) {

        frequenciaService.salvar(frequenciaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return "redirect:/frequencias/add";
    }
    @PostMapping("/editar")
    public String edit(@ModelAttribute("frequencia") FrequenciaDTO frequenciaDTO, final RedirectAttributes redirectAttributes) {

        frequenciaService.salvar(frequenciaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("frequencia.create.success"));
        return "redirect:/frequencias/add";
    }

    @GetMapping("/editar/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("frequencia", frequenciaService.buscarPorId(id));
         return "frequencia/add";
    }

   

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id, final RedirectAttributes redirectAttributes) {
        frequenciaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("frequencia.delete.success"));
        return "redirect:/frequencias";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getFrequencias(HttpServletRequest request) {
        return ResponseEntity.ok(frequenciaService.buscarFrequencias(request));
    }

}
