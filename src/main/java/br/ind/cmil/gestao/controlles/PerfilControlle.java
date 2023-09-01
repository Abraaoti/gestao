package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/perfil")
public class PerfilControlle {

    private final IPerfilService ps;


    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody PerfilDTO perfilDTO) {
        return new ResponseEntity<>(ps.create(perfilDTO), HttpStatus.CREATED);
    }

    @GetMapping("/perfis")
    public Set<PerfilDTO> list(Pageable pageable) {
        return ps.list(pageable);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PerfilDTO perfilDTO) {
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
