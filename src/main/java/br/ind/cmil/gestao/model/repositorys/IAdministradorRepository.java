package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Administrador;
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
public interface IAdministradorRepository extends JpaRepository<Administrador, Long> {

    @Query(value = "SELECT obj FROM Administrador obj ")
    List<Administrador> searchAll();

    @Query(value = "SELECT obj FROM Administrador obj ",
            countQuery = "SELECT COUNT(obj) FROM Administrador obj ")
    Page<Administrador> searchAll(Pageable pageable);

    @Query("SELECT obj FROM Administrador obj where obj.nome =: search")
    Page<?> searchAll(String search, Pageable pageable);

    Optional<Administrador> findByNome(String nome);

    @Query("select obj from Administrador obj INNER JOIN  obj.usuario u where u.nome =:nome OR u.email =:email")
    Optional<Administrador> findByUsuarioEmail(String nome, String email);

    @Query("select a from Administrador a where a.nome like :search%")
    Page<?> findAllByTitulo(String search, Pageable pageable);

    @Query("select a.nome from Administrador a where a.nome like :termo%")
    List<String> findByAdministradorTermo(String termo);

    @Query("select a from Administrador a where a.usuario.nome IN :titulos")
    Set<Administrador> findByAdministradorNome(String[] titulos);

}
