
package br.ind.cmil.gestao.model.enums.converters;

import br.ind.cmil.gestao.model.enums.TipoPresenca;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class TipoPresencaConvert implements AttributeConverter<TipoPresenca, String> {

    @Override
    public String convertToDatabaseColumn(TipoPresenca attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoPresenca convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoPresenca.values())
                .filter((tel) -> tel.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}