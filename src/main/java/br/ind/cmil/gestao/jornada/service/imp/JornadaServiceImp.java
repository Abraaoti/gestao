
package br.ind.cmil.gestao.jornada.service.imp;

import br.ind.cmil.gestao.jornada.domain.JornadaTrabalho;
import br.ind.cmil.gestao.jornada.repository.JornadaRepository;
import br.ind.cmil.gestao.jornada.service.JornadaService;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author abraaocalelessocassi
 */
public class JornadaServiceImp implements JornadaService {

   private final JornadaRepository jornadaRepository;

  
    public JornadaServiceImp(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

   @Override
    public JornadaTrabalho saveJornada(JornadaTrabalho jornadaTrabalho){
       return jornadaRepository.save(jornadaTrabalho);

    }

   @Override
    public List<JornadaTrabalho> findAll() {
       return   jornadaRepository.findAll();
    }

   @Override
    public Optional<JornadaTrabalho> getById(Long idJornada) {
        return jornadaRepository.findById(idJornada);
    }

   @Override
    public JornadaTrabalho updateJornada(JornadaTrabalho jornadaTrabalho){
        return jornadaRepository.save(jornadaTrabalho);

    }

   @Override
    public void deleteJornada(Long idJornada) {
        jornadaRepository.deleteById(idJornada);
    }
    
}
