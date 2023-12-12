package br.ind.cmil.gestao.mappers;

/**
 *
 * @author abraao
 */
//@Component
public class PresencaMapper {
  /**  
    public PresencaDTO toDTO(Presenca presenca) {
         if (presenca == null) {
            return null;
        }
        List<FrequenciaDTO> frequencias = presenca.getFrequencias()
                .stream()
                .map(frequencia -> new FrequenciaDTO(frequencia.getId(), frequencia.getData(),presenca.getId(), frequencia.getFuncionario().getId()))
                .toList();
        return new PresencaDTO(presenca.getId(), presenca.getStatus(), frequencias);
        
    }
    
    public Presenca toEntity(PresencaRequestDTO dto) {
        Presenca presenca = new Presenca();
        presenca.setStatus(dto.status());
        
        Set<Frequencia> frequencias = dto.frequencias()
                .stream()
                .map(frequenciaDTO -> {
                    Frequencia frequencia = new Frequencia();
                    if (frequencia.getId() > 0) {
                        frequencia.setId(frequenciaDTO.id());
                        
                    }
                    frequencia.setData(frequenciaDTO.data());
                    frequencia.setPresenca(presenca);
                    frequencia.setFuncionario(new Funcionario(frequenciaDTO.funcionario()));
                    return frequencia;
                    
                }).collect(Collectors.toSet());
        
        presenca.setFrequencias(frequencias);
        //LocalDate data = (dto.dataPresenca()) == null ? LocalDate.now() : dto.dataPresenca();
        //presenca.setDataPresenca(data);
        // presenca.setTipo(TipoControle.convertTipoControle(dto.tipo()));
        //presenca.setFuncionario(new Funcionario(dto.funcionario()));
        
        return presenca;
    }
    
    public Frequencia convertFrequenciaDTOToFrequencia(FrequenciaDTO dto) {
        Frequencia frequencia = new Frequencia();
        frequencia.setId(dto.id());
        LocalDate data = (dto.data()) == null ? LocalDate.now() : dto.data();
        frequencia.setData(data);
        frequencia.setFuncionario(new Funcionario(dto.funcionario()));
        
        return frequencia;
    }
    */
}
