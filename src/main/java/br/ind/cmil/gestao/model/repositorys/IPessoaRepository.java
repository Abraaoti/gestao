package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraao
 */
@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContaining(String nome);

    Optional<Pessoa> findByNome(String nome);
}
