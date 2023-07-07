
package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.entidades.UsuarioLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface IUsuarioLocationRepository extends JpaRepository<UsuarioLocation, Long>{
     UsuarioLocation findByCountryAndUser(String country, Usuario user);
}
