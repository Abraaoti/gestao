package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.model.dto.DependenteDTO;
import br.ind.cmil.gestao.domain.Funcionario;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import br.ind.cmil.gestao.services.DependenteService;
import br.ind.cmil.gestao.util.CustomCollectors;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
 * @author abraao
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/dependente")
public class DependenteController {

    private final DependenteService dependenteService;
    private final FuncionarioRepository funcionarioRepository;

    

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("funcionarioValues", funcionarioRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Funcionario::getId, Funcionario::getNome)));
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("dependentes", dependenteService.findAll());
        return "dependente/list";
    }

    @GetMapping("/form")
    public String form(@ModelAttribute("dependente") DependenteDTO dependenteDTO) {
        return "dependente/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("dependente") @Valid DependenteDTO dependenteDTO,
            final BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("nome") && dependenteService.nomeExists(dependenteDTO.nome())) {
            bindingResult.rejectValue("nome", "Exists.dependente.nome");
        }
        if (bindingResult.hasErrors()) {
            return "dependente/add";
        }
        dependenteService.create(dependenteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("dependente.create.success"));
        return "redirect:/dependentes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("dependente", dependenteService.get(id));
        return "dependente/edit";
    }

    @PostMapping("/edit/{id}")
    public String up(@PathVariable Long id,
            @ModelAttribute("dependente") @Valid DependenteDTO dependenteDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        DependenteDTO currentDependenteDTO = dependenteService.get(id);
        if (!bindingResult.hasFieldErrors("nome")
                && !dependenteDTO.nome().equalsIgnoreCase(currentDependenteDTO.nome())
                && dependenteService.nomeExists(dependenteDTO.nome())) {
            bindingResult.rejectValue("nome", "Exists.dependente.nome");
        }
        if (bindingResult.hasErrors()) {
            return "dependente/edit";
        }
        dependenteService.create(dependenteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("dependente.update.success"));
        return "redirect:/dependentes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable  Long id,  RedirectAttributes redirectAttributes) {
        dependenteService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("dependente.delete.success"));
        return "redirect:/dependentes";
    }
}
