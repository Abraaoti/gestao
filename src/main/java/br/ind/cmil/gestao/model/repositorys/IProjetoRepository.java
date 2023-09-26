
package br.ind.cmil.gestao.model.repositorys;

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
public interface IProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT obj FROM Projeto obj ")
    List<Projeto> searchAll();

    @Query("select p from Projeto p where p.contrato like :search%")
    Page<Projeto> searchAll(String search, Pageable pageable);

    Optional<Projeto> findByContrato(String contrato);

  //  @Query("select p.responsavel from Projeto  where p.numero like :termo%")
   // List<String> findByProjetoNumero(String termo);

    @Query("select p from Projeto p where p.contrato IN :titulos")
    Set<Projeto> findByProjetoContrato(String[] titulo);
    
}
