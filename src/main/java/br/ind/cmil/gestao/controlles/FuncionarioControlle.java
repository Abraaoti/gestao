package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.PessoaDTO;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author abraao
 */
@RestController
@RequestMapping("/api/p")
@CrossOrigin(origins = "http://localhost:4200/")
public class FuncionarioControlle {

    private final IFuncionarioService fs;
    private final IDepartamentoService ds;

    public FuncionarioControlle(IFuncionarioService fs, IDepartamentoService ds) {
        this.fs = fs;
        this.ds = ds;
    }

    @GetMapping("/pessoas")
    public List<PessoaDTO> list(Pageable pageable) {
        List<PessoaDTO> lis = fs.list(pageable);
        return lis;
    }

    @GetMapping("/departamentos")
    public Set<DepartamentoDTO> getDepartamentos() {
        return ds.lista();
    }

    @GetMapping("/generos")
    public Set<String> getGeneros() {
        Set<String> generos = new HashSet<>();
        for (Genero value : Genero.values()) {
            Genero g = value;
            // fm.convertGeneroValue(g.getValue());
            generos.add(g.getValue().toLowerCase());
        }
        return generos;
    }

    @GetMapping("/estadoCivil")
    public Set<String> getEstadoCivil() {
        Set<String> ec = new HashSet<>();
        for (EstadoCivil value : EstadoCivil.values()) {
            EstadoCivil c = value;
            ec.add(c.getValue().toLowerCase());
        }

        return ec;
    }

    @GetMapping("/{id}")
    public PessoaDTO findById(@PathVariable Long id) {
        return fs.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid FuncionarioDTO funcionaro) {
        //  Departamento d = ds.findBy(funcionaro.departamento().nome())
        return ResponseEntity.status(HttpStatus.CREATED).body(fs.create(funcionaro));
    }

    @PutMapping("/update")
    public ResponseEntity<PessoaDTO> update(@PathVariable(value = "id") Long id,@RequestBody @Valid FuncionarioDTO funcionario) {
        return ResponseEntity.ok(fs.create(funcionario));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        fs.delete(id);
        return new ResponseEntity<>("Funcionário deletado!", HttpStatus.OK);
    }
}
