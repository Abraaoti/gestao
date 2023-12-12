package br.ind.cmil.gestao.repositorys;

import br.ind.cmil.gestao.domain.Cartao;
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
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Query(value = "SELECT obj FROM Cartao obj ")
    List<Cartao> searchAll();

    @Query(value = "SELECT obj FROM Cartao obj ",
            countQuery = "SELECT COUNT(obj) FROM Cartao obj ")
    Page<Cartao> searchAll(Pageable pageable);

    @Query("SELECT obj FROM Cartao obj  where obj.numero like :search%")
    Page<Cartao> searchAll(String search, Pageable pageable);

    Optional<Cartao> findByNumero(String numero);

}
