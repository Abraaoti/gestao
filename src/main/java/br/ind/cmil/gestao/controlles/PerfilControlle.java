package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@RestController
@RequestMapping("/api/perfil")
@CrossOrigin(origins = "http://localhost:4200/")
public class PerfilControlle {

    private final IPerfilService ps;

    public PerfilControlle(IPerfilService ps) {
        this.ps = ps;
    }

   

    @PostMapping("/add")
    public ResponseEntity<PerfilDTO> save(@RequestBody PerfilDTO perfilDTO) {        
        return new ResponseEntity<>(ps.create(perfilDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> fetchUserWithId(@PathVariable @NotNull @Positive Long id) {
        return new ResponseEntity<>(ps.findById(id), HttpStatus.OK);
    }
    @GetMapping("/search/{p}")
    public ResponseEntity<PerfilDTO> buscarPorNome(@PathVariable("p") String p) {
        return new ResponseEntity<>(ps.buscarPerfilPorNome(p), HttpStatus.OK);
    }

}
