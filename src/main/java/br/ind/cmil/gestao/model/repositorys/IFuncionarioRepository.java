package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Funcionario;
import java.util.List;
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
public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT obj FROM Funcionario obj JOIN FETCH obj.departmento")
    List<Funcionario> searchAll();

    @Query(value = "SELECT obj FROM Funcionario obj JOIN FETCH obj.departmento",
            countQuery = "SELECT COUNT(obj) FROM Funcionario obj JOIN obj.departmento")
    Page<Funcionario> searchAll(Pageable pageable);
}
