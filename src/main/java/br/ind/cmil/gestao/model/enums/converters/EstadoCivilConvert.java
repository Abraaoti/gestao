package br.ind.cmil.gestao.model.enums.converters;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class EstadoCivilConvert implements AttributeConverter<EstadoCivil, String> {

    @Override
    public String convertToDatabaseColumn(EstadoCivil ec) {
        if (ec == null) {
            return null;
        }
        return ec.getValue();
    }

    @Override
    public EstadoCivil convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(EstadoCivil.values())
                .filter((ec) -> ec.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
