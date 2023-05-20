package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Perfil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("select p from Perfil p where  p.tp like :tp")
    Optional<Perfil> findByTipoPerfil(String tp);
}
