package br.ind.cmil.gestao.convert;

import br.ind.cmil.gestao.enums.Genero;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class GeneroConvert implements AttributeConverter<Genero, String> {

    @Override
    public String convertToDatabaseColumn(Genero genero) {
        if (genero == null) {
            return null;
        }
        return genero.getValue();
    }

    @Override
    public Genero convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Genero.values())
                .filter((g) -> g.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
