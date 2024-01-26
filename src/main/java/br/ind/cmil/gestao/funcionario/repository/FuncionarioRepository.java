package br.ind.cmil.gestao.funcionario.repository;


import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.centro.domain.CentroCusto;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.repositorys.projections.HistoricoFuncionario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author abraao
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

 
    @Query(value = "SELECT distinct f FROM Funcionario f join  f.departamento as d join  f.centro as ce join  f.cargo as c",
            countQuery = "SELECT COUNT(obj) FROM Funcionario obj  where obj.nome like :search%")
    Page<Funcionario> searchAll(String search, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Funcionario obj  where obj.nome like :search%")
    Page<Funcionario> findByCargo(String search, Pageable pageable);

    Optional<Funcionario> findByNome(String nome);

    Optional<Funcionario> findBySobrenome(String sobronome);

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByRg(String rg);

    Funcionario findFirstByDepartamento(Departamento departamento);

    Funcionario findFirstByCargo(Cargo cargo);

    Funcionario findFirstByCentro(CentroCusto centro);

    boolean existsByCpfIgnoreCase(String cpf);

    boolean existsByCltIgnoreCase(String clt);
    
    @Query("select fum from Funcionario fum join fum.cargo c "
		+ "where fum.cargo.nome like :nome%")	
	Page<HistoricoFuncionario> findHistoricoByFuncionarioNome(String nome, Pageable pageable);


}
