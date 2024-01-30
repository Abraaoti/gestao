package br.ind.cmil.gestao.endereco.controle;

import br.ind.cmil.gestao.endereco.model.EnderecoDTO;
import br.ind.cmil.gestao.endereco.service.EnderecoService;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final FuncionarioService funcionarioService;

    public EnderecoController(EnderecoService enderecoService, FuncionarioService funcionarioService) {
        this.enderecoService = enderecoService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public String enderecos() {
        return "endereco/enderecos";
    }

    @GetMapping("/enderecos")
    public List<EnderecoDTO> list(Pageable pageable) {
        return enderecoService.list(pageable);
    }

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("pessoas", funcionarioService.list());
    }

    @GetMapping("/add/{pessoa_id}")
    public String formEndereco(@ModelAttribute("endereco") EnderecoDTO endereco, Model model, @PathVariable("pessoa_id") Long pessoa_id) {
        model.addAttribute("endereco", enderecoService.criar(pessoa_id, endereco));
        return "endereco/endereco";
    }

    @PostMapping("/add")
    public String salvar(@ModelAttribute("endereco") EnderecoDTO enderecoDTO, RedirectAttributes redir, Model model) {

        Long pessoa = enderecoService.salvar(enderecoDTO);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("endereco.create.success"));
        return "redirect:/enderecos/add/" + pessoa;

    }

    @PostMapping("/editar")
    public String atualizar(@ModelAttribute("endereco") EnderecoDTO enderecoDTO, RedirectAttributes redir, Model model) {
        Long pessoa = enderecoService.salvar(enderecoDTO);

        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("endereco.create.success"));
        return "redirect:/enderecos/add/" + pessoa;

    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("endereco", enderecoService.buscarEnderecoPorId(id));
        return "endereco/endereco";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        enderecoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("endereco.delete.success"));
        return "redirect:/enderecos";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> telefones(HttpServletRequest request) {
        return ResponseEntity.ok(enderecoService.buscarTodos(request));
    }

}
