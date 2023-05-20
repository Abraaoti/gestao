package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where  u.nome =:nome")
    Optional<Usuario> findByNome(String nome);

    @Query("select u from Usuario u where  u.email= :email")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("select u from Usuario u where  u.email = :email OR u.nome= :nome")
    Optional<Usuario> findByEmailORNome(String email, String nome);

    @Query("select u from Usuario u where  u.nome like :nome")
    Boolean existsByNome(String nome);

    @Query("select u from Usuario u where  u.email like :email")
    Boolean existsByEmail(String email);

    @Query("select distinct u from Usuario u join u.perfis p ")
    List<Usuario> usuarios();

    @Query("select u from Usuario u join u.perfis p where u.id = :usuarioId AND p.id IN :perfisId")
    Optional<Usuario> findByIdAndPerfis(Long usuarioId, Long[] perfisId);

    @Query("select u from Usuario u JOIN FETCH u.perfis p where u.id = :usuarioId")
    Optional<Usuario> findByUsuarioId(Long usuarioId);

    @Query("select u from Usuario u where  u.email like :email AND ativo = true")
    Optional<Usuario> findByEmailAndAtivo(String email);

    @Query(value = "SELECT obj FROM Usuario obj JOIN FETCH obj.perfis")
    List<Usuario> searchAll();

    @Query(value = "SELECT obj FROM Usuario obj JOIN FETCH obj.perfis",
            countQuery = "SELECT COUNT(obj) FROM Usuario obj JOIN obj.perfis")
    Page<Usuario> searchAll(Pageable pageable);

    @Query(value = "SELECT obj FROM Usuario obj JOIN FETCH obj.perfis ",
            countQuery = "SELECT COUNT(obj) FROM Usuario obj JOIN obj.perfis "
            + "WHERE LOWER(obj.nome) like %:searchTerm% "
            + "OR LOWER(obj.email) like %:searchTerm%")
    Page<Usuario> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
