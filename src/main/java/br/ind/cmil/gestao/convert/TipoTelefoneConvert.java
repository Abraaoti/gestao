package br.ind.cmil.gestao.convert;

import br.ind.cmil.gestao.enums.TipoTelefone;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class TipoTelefoneConvert implements AttributeConverter<TipoTelefone, String> {

    @Override
    public String convertToDatabaseColumn(TipoTelefone attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoTelefone convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoTelefone.values())
                .filter((tel) -> tel.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
