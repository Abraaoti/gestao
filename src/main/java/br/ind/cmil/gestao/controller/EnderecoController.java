package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.ind.cmil.gestao.services.EnderecoService;
import br.ind.cmil.gestao.services.FuncionarioService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final FuncionarioService funcionarioService;

    public EnderecoController(EnderecoService enderecoService, FuncionarioService funcionarioService) {
        this.enderecoService = enderecoService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/enderecos")
    public List<EnderecoDTO> list(Pageable pageable) {
        return enderecoService.list(pageable);
    }

    @GetMapping("/pessoa/{pessoa_id}")
    public String formEndereco(@PathVariable("pessoa_id") Long pessoa_id, EnderecoDTO endereco, Model model) {
        model.addAttribute("endereco", enderecoService.criar(pessoa_id, endereco));
        return "endereco/endereco";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute EnderecoDTO endereco, RedirectAttributes redir, Model model) {

        enderecoService.salvar(endereco);
        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("endereco.create.success"));
        model.addAttribute("pessoa", funcionarioService.buscarFuncionarioPorId(endereco.pessoa()));
        return "funcionarios/detalhepessoa";

    }

    @PostMapping("/editar")
    public String atualizar(@ModelAttribute EnderecoDTO endereco, RedirectAttributes redir, Model model) {
        enderecoService.salvar(endereco);
        model.addAttribute("pessoa", funcionarioService.buscarFuncionarioPorId(endereco.pessoa()));
        return "funcionarios/detalhepessoa";

    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("endereco", enderecoService.buscarEnderecoPorId(id));
        return "endereco/endereco";
    }

    @GetMapping("/excluir/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        enderecoService.delete(id);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> telefones(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.lista(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(enderecoService.buscarTodos(request));
    }

}
