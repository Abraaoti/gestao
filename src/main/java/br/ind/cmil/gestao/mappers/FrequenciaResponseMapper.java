package br.ind.cmil.gestao.mappers;

/**
 *
 * @author abraao
 */
//@Component
public class FrequenciaResponseMapper {
/**
    public FrequenciaResponseDTO toDTO(Frequencia frequencia) {
        List<FuncionarioDTO> funcionarios = frequencia.getFuncionarios().stream().map(funcionario -> new FuncionarioDTO(Long.MIN_VALUE, nome, sobrenome, LocalDate.MIN, cpf, rg, mae, pai, clt, genero, estado_civil, naturalidade, LocalDate.MIN, LocalDate.MIN, BigDecimal.ZERO, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE)).collect(Collectors.toList());
        return new FrequenciaResponseDTO(frequencia.getId(), frequencia.getData(), frequencia.getStatus().getValue(), funcionarios);
    }

    public Frequencia toEntity(FrequenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        LocalDate data = (dto.data() == null) ? LocalDate.now() : dto.data();
        frequencia.setData(data);
        frequencia.setStatus(TipoFrequencia.convertTipoTipoFrequencia(dto.status()));
        List<Funcionario> funcionarios = dto.funcionario().stream().map(funcionaro -> new Funcionario(funcionaro)).toList();
        frequencia.setFuncionarios(funcionarios);
        return frequencia;
    }
**/
}
