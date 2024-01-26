
package br.ind.cmil.gestao.ponto.rest;

import br.ind.cmil.gestao.ponto.model.PontoDTO;
import br.ind.cmil.gestao.ponto.service.PontoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ti
 */
@RestController
@RequestMapping(value = "/api/pontos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PontoResource {

    private final PontoService pontoService;

    public PontoResource(final PontoService pontoService) {
        this.pontoService = pontoService;
    }

    @GetMapping
    public ResponseEntity<List<PontoDTO>> getAllPontos() {
        return ResponseEntity.ok(pontoService.getPontos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoDTO> getPonto(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(pontoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Long> createPonto(@RequestBody @Valid final PontoDTO pontoDTO) {
        final Long createdId = pontoService.create(pontoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePonto(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final PontoDTO pontoDTO) {
        pontoService.update(id, pontoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePonto(@PathVariable(name = "id") final Long id) {
        pontoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
 
