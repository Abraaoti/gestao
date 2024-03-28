package br.ind.cmil.gestao.funcionario.repository;

import br.ind.cmil.gestao.frequencia.domain.Frequencia;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.funcionario.repository.projections.FuncionarioBaseProjection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author abraao
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT distinct f FROM Funcionario f join  f.departamento as d join  f.empresa as e join  f.cargo as c",
            countQuery = "SELECT COUNT(obj) FROM Funcionario obj  where obj.clt like :search%")
    Page<Funcionario> searchAll(String search, Pageable pageable);

    @Query(value = "SELECT distinct f FROM Funcionario f  join  f.departamento as d join  f.empresa as e join  f.cargo as c where f.clt like :search%")
    Page<FuncionarioBaseProjection> funcionarios(String search, Pageable pageable);

    Optional<Funcionario> findByClt(String clt);

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByRg(String rg);

    Optional<Funcionario> findByNome(String nome);

    Funcionario findFirstByFrequencias(Frequencia frequencia);

    /**
     * @Transactional(readOnly = true)
     * @Query("SELECT obj FROM Funcionario obj where obj.nome like :search%")
     * Page<Funcionario> findByCargo(String search, Pageable pageable);
     * Funcionario findFirstByDepartamento(Departamento departamento);
     *
     * Funcionario findFirstByCargo(Cargo cargo);
     *
     * Funcionario findFirstByCentro(CentroCusto centro);
     *
     * boolean existsByCpfIgnoreCase(String cpf);
     *
     * boolean existsByCltIgnoreCase(String clt);
     *
     * @Query("select fum from Funcionario fum join fum.cargo c " + "where
     * fum.cargo.nome like :nome%") Page<HistoricoFuncionario>
     * findHistoricoByFuncionarioNome(String nome, Pageable pageable);
     */
}
