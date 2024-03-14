package br.ind.cmil.gestao.pessoa.repository;

import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraaocalelessocassi
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByNome(String nome);

    Optional<Pessoa> findBySobrenome(String sobronome);

    @Query(value = "SELECT distinct p FROM Pessoa p  where p.nome like :search%")
    Page<Pessoa> pessoas(String search, Pageable pageable);

}
