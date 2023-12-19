
package br.ind.cmil.gestao.convert;

import br.ind.cmil.gestao.enums.TipoFrequencia;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class TipoAusenciaConvert implements AttributeConverter<TipoFrequencia, String> {

    @Override
    public String convertToDatabaseColumn(TipoFrequencia attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoFrequencia convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoFrequencia.values())
                .filter((tp) -> tp.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}