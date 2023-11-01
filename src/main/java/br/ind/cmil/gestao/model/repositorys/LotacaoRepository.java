package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Lotacao;
import br.ind.cmil.gestao.model.entidades.Projeto;
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
public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {

    @Query(value = "SELECT obj FROM Lotacao obj ")
    List<Projeto> searchAll();

    @Query("select l from Lotacao l where l.nome like :search%")
    Page<Lotacao> searchAll(String search, Pageable pageable);

    Optional<Lotacao> findByNome(String contrato);

    List<Lotacao> findAllByOrderByNomeAscIdDesc();

}
