package br.ind.cmil.gestao.ponto.repositorys;


import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.ponto.domain.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ti
 */
@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

    Ponto findFirstByFuncionario(Funcionario funcionario);
    /** @Query("SELECT c FROM Ponto c ")
    List<Ponto> searchAll();

    @Query("SELECT c FROM Cargo c  ")
    Page<Ponto> searchAll(Pageable pageable);

    @Query("SELECT c FROM Ponto c  where c.nome like :search%")
    Page<Ponto> searchAll(String search, Pageable pageable);*/
}
