package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class FuncionarioMapper {

    public FuncionarioDTO toDTO(Funcionario f) {
        if (f == null) {
            return null;
        }
        return new FuncionarioDTO(f.getId(), f.getNome(), f.getSobrenome(), f.getNascimento(), f.getCpf(), f.getRg(), f.getMae(), f.getPai(), f.getPassaporte(), f.getGenero().getValue(), f.getEstado_civil().getValue(), f.getNaturalidade(), f.getAdmissao(), f.getMatricula(), f.getDemissao(), f.getSalario());
    }

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Funcionario f = new Funcionario();
        f.setId(dto.id());
        f.setNome(dto.nome());
        f.setSobrenome(dto.sobrenome());
        f.setNascimento(dto.nascimento());
        f.setCpf(dto.cpf());
        f.setRg(dto.rg());
        f.setMae(dto.mae());
        f.setPai(dto.pai());
        f.setPassaporte(dto.passaporte());

        f.setGenero(convertGeneroValue(dto.genero()));
        f.setEstado_civil(convertECValue(dto.estado_civil()));
        f.setNaturalidade(dto.naturalidade());
        f.setAdmissao(LocalDate.now());
        f.setMatricula(dto.matricula());
        f.setDemissao(dto.demissao());
        f.setSalario(dto.salario());
        return f;
    }

    public Genero convertGeneroValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "feminino" ->
                Genero.FEMININO;
            case "masculino" ->
                Genero.MASCULINO;
            case "outros" ->
                Genero.OUTROS;
            default ->
                throw new IllegalArgumentException(" Genero invalido " + value);
        };
    }

    public EstadoCivil convertECValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "solteiro(a)" ->
                EstadoCivil.SOLTEIRO;
            case "casado(a)" ->
                EstadoCivil.CASADO;
            case "divorciado(a)" ->
                EstadoCivil.DIVORCIADO;
            case "viúva(o)" ->
                EstadoCivil.VIUVA;
            default ->
                throw new IllegalArgumentException(" Estado civil invalido " + value);
        };
    }
}
