
package br.ind.cmil.gestao.auxiliar.respository;

import br.ind.cmil.gestao.auxiliar.domain.AuxiliarAdministrativo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ti
 */
@Repository
public interface AuxiliarAdministrativoRepository extends JpaRepository<AuxiliarAdministrativo, Long> {

    @Query(value = "SELECT obj FROM AuxiliarAdministrativo obj INNER JOIN  obj.usuario ")
    List<AuxiliarAdministrativo> searchAll(Pageable pageable);

    @Query(value = "SELECT obj FROM AuxiliarAdministrativo obj INNER JOIN  obj.usuario where obj.nome like :search%",
            countQuery = "SELECT COUNT(obj) FROM AuxiliarAdministrativo obj INNER JOIN  obj.usuario where obj.nome like :search%")
    Page<?> searchAll(String search, Pageable pageable);

    Optional<AuxiliarAdministrativo> findByNome(String nome);

    @Query("select obj from AuxiliarAdministrativo obj INNER JOIN  obj.usuario u where u.nome =:nome OR u.email =:email")
    Optional<AuxiliarAdministrativo> findByUsuarioEmail(String nome,String email);
    
}
