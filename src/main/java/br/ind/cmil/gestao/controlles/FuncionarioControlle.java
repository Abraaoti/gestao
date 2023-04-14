
package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioservice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

/**
 *
 * @author abraao
 */
@Validated
@RestController
@RequestMapping("/api/funcionario/")
@CrossOrigin(origins = "http://localhost:4200/")
public class FuncionarioControlle {
    private final IFuncionarioservice fs;

    public FuncionarioControlle(IFuncionarioservice fs) {
        this.fs = fs;
    }   

    @GetMapping("/lista")
    public  List<FuncionarioDTO> list() {
        return fs.list();
    }

    @GetMapping("/{id}")
    public FuncionarioDTO findById(@PathVariable @NotNull @Positive Long id) {
        return fs.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FuncionarioDTO create(@RequestBody @Valid @NotNull FuncionarioDTO funcionaro) {
        return fs.create(funcionaro);
    }

    @PutMapping("/update/{id}")
    public FuncionarioDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull FuncionarioDTO funcionario) {
        return fs.update(id, funcionario);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        fs.delete(id);
    }
}
