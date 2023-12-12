
package br.ind.cmil.gestao.convert;

import br.ind.cmil.gestao.enums.TipoControle;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class TipoAusenciaConvert implements AttributeConverter<TipoControle, String> {

    @Override
    public String convertToDatabaseColumn(TipoControle attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoControle convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoControle.values())
                .filter((tp) -> tp.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}