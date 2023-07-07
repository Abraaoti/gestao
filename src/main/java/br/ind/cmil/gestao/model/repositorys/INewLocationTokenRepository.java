
package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.NewLocationToken;
import br.ind.cmil.gestao.model.entidades.UsuarioLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface INewLocationTokenRepository extends JpaRepository<NewLocationToken,Long> {
    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UsuarioLocation userLocation );
}
