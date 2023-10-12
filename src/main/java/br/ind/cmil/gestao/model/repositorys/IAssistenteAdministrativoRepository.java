package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.AssistenteAdministrativo;
import java.util.List;
import java.util.Optional;
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
public interface IAssistenteAdministrativoRepository extends JpaRepository<AssistenteAdministrativo, Long> {

    @Query(value = "SELECT obj FROM AssistenteAdministrativo obj INNER JOIN  obj.usuario u ")
    List<AssistenteAdministrativo> searchAll();

    @Query(value = "SELECT obj FROM AssistenteAdministrativo obj INNER JOIN  obj.usuario u ",
            countQuery = "SELECT COUNT(obj) FROM AssistenteAdministrativo obj INNER JOIN  obj.usuario u")
    Page<AssistenteAdministrativo> searchAll(Pageable pageable);

    Optional<AssistenteAdministrativo> findByNome(String nome);

    @Query("SELECT obj FROM AssistenteAdministrativo obj where obj.nome like :search%")
    Page<AssistenteAdministrativo> searchAll(String search, Pageable pageable);

    @Query("select obj from AssistenteAdministrativo obj INNER JOIN  obj.usuario u where u.nome =:nome OR u.email =:email")
    Optional<AssistenteAdministrativo> findByUsuarioNomeOrEmail(String nome, String email);

}
