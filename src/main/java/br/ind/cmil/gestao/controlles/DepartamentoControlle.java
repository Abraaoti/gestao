package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
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
import org.springframework.http.ResponseEntity;

/**
 *
 * @author abraao
 */
@RestController
@RequestMapping("/api/departamento")
@CrossOrigin(origins = "http://localhost:4200/")
public class DepartamentoControlle {

    private final IDepartamentoService ds;

    public DepartamentoControlle(IDepartamentoService ds) {
        this.ds = ds;
    }   

    @GetMapping("/lista")
    public List<DepartamentoDTO> list(Pageable pageable) {
        return ds.list(pageable);
    }

  
    @GetMapping("/{id}")
    public DepartamentoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return ds.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid DepartamentoDTO d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ds.create(d));
    }

    @PutMapping("/update")
   public ResponseEntity<?>  update(@RequestBody  DepartamentoDTO d) {
       return ResponseEntity.status(HttpStatus.CREATED).body(ds.create(d));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        ds.delete(id);
    }
}
