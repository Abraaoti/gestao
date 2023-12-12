package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Perfil;
import br.ind.cmil.gestao.enums.TipoPerfil;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("select p from Perfil p where  p.tp =:tp")
    Optional<Perfil> findByTipoPerfil(TipoPerfil tp);

    @Query("select p from Perfil p where  p.tp IN :tp")
    Set<Perfil> findByIdAndPerfis(Set<String> tp);

    @Query(value = "SELECT obj FROM Perfil obj ",
            countQuery = "SELECT COUNT(obj) FROM Perfil obj ")
    Page<Perfil> searchAll(Pageable pageable);

    @Query(value = "SELECT obj FROM Perfil obj ",
            countQuery = "SELECT COUNT(obj) FROM Perfil obj ")
    List<Perfil> searchAll();

    @Query("select p from Perfil p where  p.tp like :tp%")
    Page<Perfil> findAllByPerfil(TipoPerfil tp, Pageable pageable);
}
