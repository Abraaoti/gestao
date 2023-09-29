package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Diretor;
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
public interface IDiretorRepository extends JpaRepository<Diretor, Long> {

    @Query(value = "SELECT obj FROM Diretor obj ")
    List<Diretor> searchAll();

    @Query(value = "SELECT obj FROM Diretor obj ",
            countQuery = "SELECT COUNT(obj) FROM Diretor obj ")
    Page<Diretor> searchAll(Pageable pageable);

    @Query("SELECT obj FROM Diretor obj where obj.nome like :search%")
    Page<Diretor> searchAll(String search, Pageable pageable);

    Optional<Diretor> findByNome(String nome);

    @Query("select obj from Diretor obj INNER JOIN  obj.usuario u where u.nome =:nome OR u.email =:email")
    Optional<Diretor> findByUsuarioEmail(String nome, String email);

    @Query("select a from Diretor a where a.nome like :search%")
    Page<Diretor> findAllByTitulo(String search, Pageable pageable);

    @Query("select a.nome from Diretor a where a.nome like :termo%")
    List<String> findByDiretorTermo(String termo);

    @Query("select a from Diretor a where a.usuario.nome IN :titulos")
    Set<Diretor> findByDiretorNome(String[] titulos);

}
