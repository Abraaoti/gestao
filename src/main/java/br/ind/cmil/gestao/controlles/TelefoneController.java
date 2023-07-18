package br.ind.cmil.gestao.controlles;


import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.entidades.Pessoa;
import br.ind.cmil.gestao.model.entidades.Telefone;
import br.ind.cmil.gestao.model.services.interfaces.IPessoaService;
import br.ind.cmil.gestao.model.services.interfaces.ITelefoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("api/tel")
public class TelefoneController {

    private final ITelefoneService telefoneService;
    private final IPessoaService pessoaService;

    public TelefoneController(ITelefoneService telefoneService, IPessoaService pessoaService) {
        this.telefoneService = telefoneService;
        this.pessoaService = pessoaService;
    }

   


    @PostMapping("/salvar/pessoa/{id}/telefone")
    public ResponseEntity<Telefone>  salvar(@PathVariable("id") Long id, @RequestBody Telefone telefone) {

            Pessoa pessoa = pessoaService.getPessoaById(id);
        if (pessoa.getId() != null) {
            telefone.setPessoa(pessoa);
        }
       
        return new ResponseEntity<>(telefoneService.save(telefone), HttpStatus.CREATED);
    }

    @PutMapping("/endereco/{id}/")
    public ResponseEntity<Telefone> update(@PathVariable("id") Long id,  @RequestBody Telefone telefone, RedirectAttributes red) {
           
        
    return new ResponseEntity<>(telefoneService.update(telefone), HttpStatus.OK);
    }

    @DeleteMapping("/endereco/{id}")
  public ResponseEntity<HttpStatus> deleteTelefone(@PathVariable("id") long id) {
    telefoneService.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/pessoa/{pessoaId}/endereco")
  public ResponseEntity<Endereco> deleteDetailsOfPessoa(@PathVariable(value = "pessoaId") Long pessoaId) {
    if (!pessoaService.equals(pessoaId)) {
      throw new ObjectNotFoundException("Not found Tutorial with id = " + pessoaId);
    }

    telefoneService.deleteByTutorialId(pessoaId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
   


}