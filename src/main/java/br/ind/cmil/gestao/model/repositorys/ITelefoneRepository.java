package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Telefone;
import java.util.Optional;
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

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByPessoaId(Long pessoaId);

}
