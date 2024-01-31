package br.ind.cmil.gestao.telefone.repository;

import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.telefone.domain.Telefone;
import br.ind.cmil.gestao.telefone.projections.TelefoneProjecyion;
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
 * @author ti
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    @Query("select t from Telefone t where t.numero = :numero")
    Optional<Telefone> findByNumero(String numero);

    @Query("select t from Telefone t join t.pessoa p where p.id  =:pessoa_id")
    Optional<Telefone> findByPessoa(Long pessoa_id);

    @Transactional
    void deleteByPessoaId(Long pessoaId);

    @Query("select t from Telefone t where  t.pessoa.id  =:pessoa_id")
    Optional<Telefone> findByPessoaId(Long pessoa_id);

    @Query("select t from Telefone t where  t.pessoa.id =:pessoa_id")
    Page<Telefone> findByIdPessoa(Long pessoa_id, Pageable pageable);

    @Query("select t.numero from Telefone t JOIN t.pessoa p where  p.id  =:pessoa_id")
    Page<Telefone> findPessoa(Long pessoa_id, Pageable pageable);

    @Query("select t from Telefone t  where t.pessoa.id =:pessoa_id")
    List<Telefone> findTelefonesByPessoaId(Long pessoa_id);

    @Query("select t from Telefone t join fetch t.pessoa p where p.id  =:pessoa_id")
    Telefone findTelefone(Long pessoa_id);

    @Query(value = "SELECT obj FROM Telefone obj JOIN  obj.pessoa p ")
    List<Telefone> searchAll();

    @Query(value = "SELECT obj FROM Telefone obj JOIN   obj.pessoa",
            countQuery = "SELECT COUNT(obj) FROM Telefone obj  JOIN obj.pessoa")
    Page<Telefone> searchAll(Pageable pageable);

    @Query(value = "SELECT obj FROM Telefone obj left join  obj.pessoa p where  obj.tipo like :tipo%")
    Page<Telefone> findAllByTelefone(TipoTelefone tipo, Pageable pageable);

    @Query(value = "SELECT t.id as id, t.numero as numero, t.tipo as tipo, t.pessoa.nome as nome FROM Telefone t  where  t.tipo like :tipo%")
    Page<TelefoneProjecyion> telefones(TipoTelefone tipo, Pageable pageable);

    Telefone findFirstByPessoa(Funcionario funcionario);

}
