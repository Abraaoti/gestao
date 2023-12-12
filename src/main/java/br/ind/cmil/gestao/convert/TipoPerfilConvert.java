package br.ind.cmil.gestao.convert;

import br.ind.cmil.gestao.enums.TipoPerfil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class TipoPerfilConvert implements AttributeConverter<TipoPerfil, String> {

    @Override
    public String convertToDatabaseColumn(TipoPerfil attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoPerfil convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoPerfil.values())
                .filter((tp) -> tp.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
