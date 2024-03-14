package br.ind.cmil.gestao.pessoa.repository;

import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
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
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    @Query(value = "SELECT  obj  FROM PessoaJuridica obj  where obj.cnpj like :search%")
    Page<PessoaJuridica> searchAll(String search, Pageable pageable);
}
