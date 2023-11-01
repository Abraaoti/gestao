package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.ProjetoDTO;
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

    private DepartamentoMapper departamentoMapper;
    private CargoMapper cargoMapper;
    private ProjetoMapper projetoMapper;

    public FuncionarioDTO toDTO(Funcionario f) {
        if (f == null) {
            return null;
        }
        // List<TelefoneDTO> telefones = f.getTelefones()
        //.stream()
        //.map(telefone -> new TelefoneDTO(telefone.getId(), telefone.getNumero(),
        //telefone.getTipo().getValue(), telefone.getPessoa()))
        //.collect(Collectors.toList());

        DepartamentoDTO departamento = this.departamentoMapper.toDTO(f.getDepartmento());
        CargoDTO cargo = this.cargoMapper.toDTO(f.getCargo());
        ProjetoDTO projeto = this.projetoMapper.toDTO(f.getProjeto());

        return new FuncionarioDTO(f.getAdmissao(), f.getDemissao(), f.getSalario(), departamento, cargo, projeto, f.getCpf(), f.getRg(), f.getMae(), f.getPai(), f.getPassaporte(), f.getGenero().getValue(), f.getEstado_civil().getValue(), f.getNaturalidade(), f.getId(), f.getNome(), f.getSobrenome(), f.getNascimento());
        
    }

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Funcionario f = new Funcionario();
        f.setId(dto.getId());
        f.setNome(dto.getNome());
        f.setSobrenome(dto.getSobrenome());
        f.setNascimento(dto.getNascimento());
        f.setCpf(dto.getCpf());
        f.setRg(dto.getRg());
        f.setMae(dto.getMae());
        f.setPai(dto.getPai());
        f.setPassaporte(dto.getPassaporte());
        f.setGenero(convertGeneroValue(dto.getGenero()));
        f.setEstado_civil(convertECValue(dto.getEstado_civil()));
        f.setNaturalidade(dto.getNaturalidade());
        LocalDate data = (dto.getAdmissao()) == null ? LocalDate.now() : dto.getAdmissao();
        f.setAdmissao(data);
        f.setDemissao(dto.getDemissao());
        f.setSalario(dto.getSalario());
        f.setDepartmento(this.departamentoMapper.toEntity(dto.getDepartamento()));
        f.setCargo(this.cargoMapper.toEntity(dto.getCargo()));
        f.setProjeto(this.projetoMapper.toEntity(dto.getProjeto()));
        //TelefoneMapper tm = new TelefoneMapper();
        //List<Telefone> telefones = dto.getTelefones().stream().map(telefoneDTO -> {
        // var telefone = new Telefone();
        //telefone.setId(telefoneDTO.id());
        //telefone.setNumero(telefoneDTO.numero());
        //telefone.setTipo(tm.convertTipoTelefoneValue(telefoneDTO.tipo()));
        //telefone.setPessoa(f);
        //return telefone;
        //}).collect(Collectors.toList());
        //f.setTelefones(telefones);
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
