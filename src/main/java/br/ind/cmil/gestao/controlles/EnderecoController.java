package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import br.ind.cmil.gestao.model.services.interfaces.IEnderecoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cmilseg
 */
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final IEnderecoService es;

    public EnderecoController(IEnderecoService enderecoService) {
        this.es = enderecoService;
    }

    @GetMapping("/lista")
    public List<EnderecoDTO> list(Pageable pageable) {
        return es.list(pageable);
    }

    @GetMapping("/{id}")
    public EnderecoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return es.buscarEnderecoPorId(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EnderecoDTO e) {
        //  Departamento d = ds.findBy(funcionaro.departamento().nome())
        return ResponseEntity.status(HttpStatus.CREATED).body(es.create(e));
    }

    @PutMapping("/update/{id}")
    public EnderecoDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull EnderecoDTO e) {
        return es.update(id, e);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        es.delete(id);
    }

}