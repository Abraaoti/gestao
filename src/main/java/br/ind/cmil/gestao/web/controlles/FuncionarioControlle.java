package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.PessoaDTO;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import br.ind.cmil.gestao.model.services.interfaces.ICargoService;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/funcionario")
public class FuncionarioControlle {

    private final IFuncionarioService fs;
    private final IDepartamentoService ds;
    private final ICargoService cs;

    @GetMapping("/add")
    public String form(Model model,FuncionarioDTO funcionario) {
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", ds.lista());
        model.addAttribute("cargos",cs.lista());
        model.addAttribute("estadoCivil", getEstadoCivil());
        model.addAttribute("generos", getGeneros());
        return "rh/funcionarios/funcionario";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute FuncionarioDTO funcionario, RedirectAttributes redir) {
        fs.create(funcionario);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/funcionario/add", "funcionario", funcionario);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute FuncionarioDTO funcionario, RedirectAttributes redir) {
        fs.create(funcionario);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/funcionario/add", "funcionario", funcionario);
    }

    @GetMapping("/lista")
    public String lista() {
        return "rh/funcinarios/funcionarios";

    }

    @GetMapping("/pessoas")
    public List<PessoaDTO> list(Pageable pageable) {
        List<PessoaDTO> lis = fs.list(pageable);
        return lis;
    }

    @GetMapping("/graficofuncionario")
    public ResponseEntity<?> getDataForMultipleLine(Pageable pageable) {
        List<PessoaDTO> dataList = fs.list(pageable);
        Map<String, List<PessoaDTO>> mappedData = new HashMap<>();
        for (PessoaDTO data : dataList) {

            if (mappedData.containsKey(data.getNome())) {
                mappedData.get(data.getNome()).add(data);
            } else {
                List<PessoaDTO> tempList = new ArrayList<>();
                tempList.add(data);
                mappedData.put(data.getNome(), tempList);
            }

        }
        return new ResponseEntity<>(mappedData, HttpStatus.OK);
    }

    private Set<String> getGeneros() {
        Set<String> generos = new HashSet<>();
        for (Genero value : Genero.values()) {
            Genero g = value;
            // fm.convertGeneroValue(g.getValue());
            generos.add(g.getValue().toLowerCase());
        }
        return generos;
    }

    private Set<String> getEstadoCivil() {
        Set<String> ec = new HashSet<>();
        for (EstadoCivil value : EstadoCivil.values()) {
            EstadoCivil c = value;
            ec.add(c.getValue().toLowerCase());
        }

        return ec;
    }


    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable("id") Long id) {
        model.addAttribute("funcionario", fs.findById(id));
        model.addAttribute("departamentos", ds.lista());
        model.addAttribute("estadoCivil", getEstadoCivil());
        model.addAttribute("generos", getGeneros());
        return "rh/funcinarios/funcionario";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        fs.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");

        return new ModelAndView("rh/funcinarios/funcionarios", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> funcionarios(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.list(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(fs.buscarTodos(request));
    }

}
