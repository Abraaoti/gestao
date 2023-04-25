package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Usuario;
import java.util.List;
import java.util.Optional;
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

    @Query("select u from Usuario u where  u.email like :email")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("select distinct u from Usuario u "
      + "join u.perfis p ")
    List<Usuario> usuarios();
    @Query("select u from Usuario u "
            + "join u.perfis p "
            + "where u.id = :usuarioId AND p.id IN :perfisId")
    Optional<Usuario> findByIdAndPerfis(Long usuarioId, Long[] perfisId);

    @Query("select u from Usuario u where  u.email like :email AND ativo = true")
    Optional<Usuario> findByEmailAndAtivo(String email);

}
