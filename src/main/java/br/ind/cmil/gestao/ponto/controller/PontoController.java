
package br.ind.cmil.gestao.ponto.controller;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.ponto.model.PontoDTO;
import br.ind.cmil.gestao.ponto.service.PontoService;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.util.CustomCollectors;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ti
 */
@Controller
@RequestMapping("/pontos")
public class PontoController {

    private final PontoService pontoService;
    private final FuncionarioRepository funcionarioRepository;

    public PontoController(final PontoService pontoService,
            final FuncionarioRepository funcionarioRepository) {
        this.pontoService = pontoService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("funcionarioValues", funcionarioRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Funcionario::getId, Funcionario::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("pontoes", pontoService.getPontos());
        return "ponto/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("ponto") final PontoDTO pontoDTO) {
        return "ponto/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("ponto") @Valid final PontoDTO pontoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ponto/add";
        }
        pontoService.create(pontoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ponto.create.success"));
        return "redirect:/pontos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("ponto", pontoService.buscarPorId(id));
        return "ponto/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("ponto") @Valid final PontoDTO pontoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ponto/edit";
        }
        pontoService.update(id, pontoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ponto.update.success"));
        return "redirect:/pontos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        pontoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("ponto.delete.success"));
        return "redirect:/pontos";
    }

}
