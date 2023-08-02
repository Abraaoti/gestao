package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.TelefoneDTO;
import br.ind.cmil.gestao.model.services.interfaces.ITelefoneService;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@RestController
@RequestMapping("api/t")
public class TelefoneController {

    private final ITelefoneService telService;

    public TelefoneController(ITelefoneService telefoneService) {
        this.telService = telefoneService;
    }

    @GetMapping("/telefones")
    public List<TelefoneDTO> list(Pageable pageable) {
        return telService.list(pageable);
    }

    @PostMapping("/pessoa/{pessoa_id}/telefone")
    public ResponseEntity<TelefoneDTO> salvaTelefone(@PathVariable("pessoa_id") Long pessoa_id,@RequestBody TelefoneDTO telefone) {

         return ResponseEntity.status(HttpStatus.CREATED).body(telService.save(pessoa_id,telefone));
    }

    @PutMapping("/telefone/{id}")
    public ResponseEntity<TelefoneDTO> update(@PathVariable("id") Long id,@RequestBody TelefoneDTO telefone, RedirectAttributes red) {

         return ResponseEntity.status(HttpStatus.CREATED).body(telService.save(id,telefone));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTelefone(@PathVariable("id") Long id) {
        telService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
