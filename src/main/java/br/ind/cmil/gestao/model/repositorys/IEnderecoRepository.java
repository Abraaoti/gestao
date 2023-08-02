package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.entidades.Pessoa;
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
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Endereco e where e.cep =:cep")
    Optional<Endereco> findByCep(String cep);

    @Query("select e from Endereco e where e.pessoa.id =:pessoa_id")
    Optional<Endereco> findByPessoa(Long pessoa_id);

    @Query(value = "SELECT obj FROM Endereco obj JOIN  obj.pessoa")
    List<Endereco> searchAll();

    @Query(value = "SELECT obj FROM Endereco obj  JOIN  obj.pessoa",
            countQuery = "SELECT COUNT(obj) FROM Endereco obj  JOIN obj.pessoa")
    Page<Endereco> searchAll(Pageable pageable);
}
