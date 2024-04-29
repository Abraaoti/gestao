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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abraaocalelessocassi
 */
@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    @Query("select  f from Frequencia f join f.funcionario fu where fu.nome like :search%")
    Page<Frequencia> searchAll(String search, Pageable pageable);

    @Query("select f from Frequencia f where f.entrada =:entrada")
    Optional<Frequencia> findByEntrada(LocalTime entrada);

    @Query("select f from Frequencia f where f.intervalo =:intervalo")
    Optional<Frequencia> findByIntervalo(LocalTime intervalo);

    @Query("select f from Frequencia f where f.retorno =:retorno")
    Optional<Frequencia> findByRetorno(LocalTime retorno);

    @Query("select f from Frequencia f where f.saida =:saida")
    Optional<Frequencia> findBySaida(LocalTime saida);

    @Query("select f from Frequencia f where f.funcionario.id =:funcionario_id")
    Optional<Frequencia> findByFuncionarioId(@Param("funcionario_id") Long funcionario_id);

    Optional<Frequencia> findFirstByFuncionario(Funcionario funcionario);

    // List<Frequencia> findAllByFuncionario(Funcionario funcionario);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update Frequencia f set f.entrada = ?1 where f.id= ?2 ")
    void updateEntrada(LocalTime entrada, Long id);
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
