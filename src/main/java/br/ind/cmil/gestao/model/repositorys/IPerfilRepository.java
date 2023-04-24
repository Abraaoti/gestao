
package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long>{
    
}
