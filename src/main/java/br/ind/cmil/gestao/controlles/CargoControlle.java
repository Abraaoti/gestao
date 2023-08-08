package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.services.interfaces.ICargoService;
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
@RequestMapping("/api/c")
@CrossOrigin(origins = "http://localhost:4200/")
public class CargoControlle {

    private final ICargoService cs;

    public CargoControlle(ICargoService cs) {
        this.cs = cs;
    }

  

    @GetMapping("/lista")
    public List<CargoDTO> list(Pageable pageable) {
        return cs.list(pageable);
    }

  
    @GetMapping("/{id}")
    public CargoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cs.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CargoDTO c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cs.create(c));
    }

    @PutMapping("/update")
   public ResponseEntity<?>  update(@RequestBody  CargoDTO c) {
       return ResponseEntity.status(HttpStatus.CREATED).body(cs.create(c));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        cs.delete(id);
    }
}
