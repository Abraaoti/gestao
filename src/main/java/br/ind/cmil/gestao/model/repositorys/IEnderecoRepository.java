package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Endereco;
import java.util.Optional;
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
}
