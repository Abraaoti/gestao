package br.ind.cmil.gestao.frequencia.repository;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import java.util.List;
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
public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    @Query("select f.id as id,"
            + "f.data as dia,"
            + "f.entradaManha as entradaManha,"
            + "f.saidaManha as saidaManha,"
            + "f.entradaTarde as entradaTarde,"
            + "f.saidaTarde as saidaTarde,"
            + "f.entradaExtra as entradaExtra,"
            + "f.saidaExtra as saidaExtra,"
            + "f.funcionarios as funcionario "
            + "from Frequencia f  "
            + "where f.status like :search%")
    Page<FrequenciaProjection> searchAll(TipoFrequencia search, Pageable pageable);

    @Query("SELECT  f FROM Frequencia f join  f.funcionarios as fu where f.status =:status")
    Optional<Frequencia> findByStatus(TipoFrequencia status);

    Frequencia findFirstByFuncionarios(Funcionario funcionario);

    List<Frequencia> findAllByFuncionarios(Funcionario funcionario);

}
