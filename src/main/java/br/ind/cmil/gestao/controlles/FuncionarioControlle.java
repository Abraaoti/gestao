package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author abraao
 */
@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200/")
public class FuncionarioControlle {

    private final IFuncionarioService fs;

    public FuncionarioControlle(IFuncionarioService fs) {
        this.fs = fs;
    }

    @GetMapping("/lista")
    public List<FuncionarioDTO> list(Pageable pageable) {
        return fs.list(pageable);
    }

    @GetMapping("/generos")
    public Genero[] getGeneros() {
        return Genero.values();
    }

    @GetMapping("/estadoCivil")
    public EstadoCivil[] getEstadoCivil() {

        return EstadoCivil.values();
    }

    @GetMapping("/{id}")
    public FuncionarioDTO findById(@PathVariable @NotNull @Positive Long id) {
        return fs.findById(id);
    }

    @PostMapping("/salvar")
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Valid @NotNull FuncionarioDTO funcionaro) {
        System.out.println("Estamos aqui? "+ funcionaro.toString());
        //return null; //fs.create(funcionaro);
         return  new ResponseEntity<>(fs.create(funcionaro), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public FuncionarioDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull FuncionarioDTO funcionario) {
        return fs.update(id, funcionario);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        fs.delete(id);
    }
}
