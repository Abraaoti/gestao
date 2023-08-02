package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Telefone;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Repository
public interface ITelefoneRepository extends JpaRepository<Telefone, Long> {

    @Query("select t from Telefone t where t.numero = :numero")
    Optional<Telefone> findByNumero(String numero);

    @Query("select t from Telefone t where t.pessoa.id =:pessoa_id")
    Optional<Telefone> findByPessoa(Long pessoa_id);

    @Transactional
    void deleteByPessoaId(Long pessoaId);

    @Query("select t from Telefone t where  t.pessoa.id =:id")
    Optional<Telefone> findPessoaById(Long id);

    @Query("select t from Telefone t where  t.pessoa.id =:id")
    Page<Telefone> findByIdPessoa(Long id, Pageable pageable);

    @Query("select t.numero from Telefone t where  t.pessoa.id =:id")
    Page<Telefone> findPessoa(Long id, Pageable pageable);

    @Query("select t from Telefone t join t.pessoa p where p.id = ?1")
    List<Telefone> findTelefonesByPessoaId(Long id);

    @Query("select t from Telefone t join fetch t.pessoa where t.id = ?1")
    Telefone findTelefone(Long id);

    @Query(value = "SELECT obj FROM Telefone obj JOIN  obj.pessoa")
    List<Telefone> searchAll();

    @Query(value = "SELECT obj FROM Telefone obj JOIN FETCH  obj.pessoa",
            countQuery = "SELECT COUNT(obj) FROM Telefone obj  JOIN obj.pessoa")
    Page<Telefone> searchAll(Pageable pageable);

}
