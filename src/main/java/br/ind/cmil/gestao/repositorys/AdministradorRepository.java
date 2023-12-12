package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Administrador;
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
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    @Query(value = "SELECT obj FROM Administrador obj ")
    List<Administrador> searchAll();

    @Query(value = "SELECT obj FROM Administrador obj ",
            countQuery = "SELECT COUNT(obj) FROM Administrador obj ")
    Page<Administrador> searchAll(Pageable pageable);

    @Query("SELECT obj FROM Administrador obj where obj.nome like :search%")
    Page<Administrador> searchAll(String search, Pageable pageable);

    Optional<Administrador> findByNome(String nome);

    @Query("select obj from Administrador obj where obj.usuario.email like :email")
    Optional<Administrador> findByAdministradorEmail(String email);

    @Query("select a from Administrador a where a.nome like :search%")
    Page<Administrador> findAllByTitulo(String search, Pageable pageable);

    @Query("select a.nome from Administrador a where a.nome like :termo%")
    List<String> findByAdministradorTermo(String termo);

    @Query("select a from Administrador a where a.usuario.nome IN :titulos")
    Set<Administrador> findByAdministradorNome(String[] titulos);

}
