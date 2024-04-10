package br.ind.cmil.gestao.frequencia.repository;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import jakarta.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraaocalelessocassi
 */
@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    @Query("select  f from Frequencia f join f.funcionario fu "
            + "where f.status like :search%")
    Page<Frequencia> searchAll(TipoFrequencia search, Pageable pageable);

    /**@Query("select f.id as id,"
            + "f.data as dia,"
            + "f.status as status,"
            + "f.intervalo as intervalo,"
            + "f.retorno as retorno,"
            + "f.saida as saida"
            + "from Frequencia f  "
            + "where f.funcionario.id =:funcionario_id ORDER BY id DESC")
    Optional<Frequencia> findByFrequenciaForFuncionarioId(Long funcionario_id);*/

    @Query("SELECT  f FROM Frequencia f join  f.funcionario as fu where f.status =:status")
    Optional<Frequencia> findByStatus(TipoFrequencia status);

    Optional<Frequencia>  findFirstByFuncionario(Funcionario funcionario);

    List<Frequencia> findAllByFuncionario(Funcionario funcionario);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Frequencia f set f.intervalo = ?1 where f.id= ?2 ")
    void updateIntervalo(LocalTime intervalo, Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Frequencia f set f.retorno = ?1 where f.id= ?2 ")
    void updateRetorno(LocalTime retorno, Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Frequencia f set f.saida = ?1 where f.id= ?2 ")
    void updateSaida(LocalTime saida, Long id);

}
