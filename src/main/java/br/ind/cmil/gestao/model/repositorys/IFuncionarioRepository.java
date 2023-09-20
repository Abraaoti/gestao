package br.ind.cmil.gestao.model.repositorys;

import br.ind.cmil.gestao.model.entidades.Funcionario;
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
public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT obj FROM Funcionario obj JOIN  obj.departmento  d INNER JOIN obj.projeto p  INNER JOIN obj.cargo c  ")
    List<Funcionario> searchAll();

    @Query(value = "SELECT obj FROM Funcionario obj JOIN  obj.departmento d  INNER JOIN obj.projeto p INNER JOIN obj.cargo c   ",
            countQuery = "SELECT COUNT(obj) FROM Funcionario obj JOIN obj.departmento d  INNER JOIN obj.projeto p INNER JOIN obj.cargo c  ")
    Page<Funcionario> searchAll(Pageable pageable);
    @Query(value = "SELECT obj FROM Funcionario obj INNER JOIN obj.departmento d INNER JOIN obj.projeto p INNER JOIN obj.cargo c where obj.nome like :search% OR d.nome like :search%")
    Page<Funcionario> searchAll(String search, Pageable pageable);
    
    

    Optional<Funcionario> findByNome(String nome);

    Optional<Funcionario> findBySobrenome(String sobronome);

    //Optional<Funcionario> findByNascimento(String nascimento);
   // @Query("SELECT obj FROM Usuario obj INNER JOIN FETCH obj.departmento p where  obj.nome= :nome OR  obj.cpf = :cpf")
   // Optional<Funcionario> findByNomeOrCpf(@Param("nome") String nome, @Param("cpf") String cpf);

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByRg(String rg);
}
