package br.ind.cmil.gestao.convert;

import br.ind.cmil.gestao.enums.TipoPrivilegio;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class TipoPrivilegioConvert implements AttributeConverter<TipoPrivilegio, String> {

    @Override
    public String convertToDatabaseColumn(TipoPrivilegio attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoPrivilegio convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoPrivilegio.values())
                .filter((tp) -> tp.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
