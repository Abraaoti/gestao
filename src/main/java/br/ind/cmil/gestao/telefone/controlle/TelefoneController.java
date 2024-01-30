
package br.ind.cmil.gestao.telefone.controlle;

import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.telefone.model.TelefoneDTO;
import br.ind.cmil.gestao.telefone.service.TelefoneService;
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
 * @author ti
 */
@Controller
@RequestMapping("telefones")
public class TelefoneController {

    private final TelefoneService telService;
    private final FuncionarioService funcionarioService;

    public TelefoneController(TelefoneService telService, FuncionarioService funcionarioService) {
        this.telService = telService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public String list() {
        return "telefone/telefones";
    }

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("pessoas", funcionarioService.list());
        model.addAttribute("tipos", TipoTelefone.tipoTelefones());
    }

    @GetMapping("/add/{pessoa_id}")
    public String formTelefone(@ModelAttribute("telefone") TelefoneDTO telefone, Model model, @PathVariable("pessoa_id") Long pessoa_id) {
        model.addAttribute("telefone", telService.criar(pessoa_id, telefone));
        return "telefone/telefone";
    }

    @PostMapping("/add")
    public String salvar(@ModelAttribute("telefone") TelefoneDTO telefoneDTO, RedirectAttributes redir) {

        Long pessoa = telService.salvar(telefoneDTO);

        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("telefone.create.success"));
        return "redirect:/telefones/add/" + pessoa;
    }

    @PostMapping("/editar")
    public String atualizar(@ModelAttribute("telefone") TelefoneDTO telefoneDTO, RedirectAttributes redir) {

        Long pessoa = telService.salvar(telefoneDTO);

        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("telefone.create.success"));
        return "redirect:/telefones/add/" + pessoa;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        telService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("telefone.delete.success"));
        return "redirect:/telefones";
    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("telefone", telService.buscarTelefonePorId(id));
        return "telefone/telefone";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> telefones(HttpServletRequest request) {

        return ResponseEntity.ok(telService.buscarTodos(request));
    }

}
