package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("select p from Perfil p where  p.tp =:tp")
    Optional<Perfil> findByTipoPerfil(TipoPerfil tp);

    @Query("select p from Perfil p where  p.tp IN :tp")
    Set<Perfil> findByIdAndPerfis(Set<String> tp);

    //List<Perfil> findPerfisByUsuariosId(Long usuarioFId);
}
