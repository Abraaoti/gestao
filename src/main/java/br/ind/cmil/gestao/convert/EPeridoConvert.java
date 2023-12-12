
package br.ind.cmil.gestao.convert;
import br.ind.cmil.gestao.enums.EPeriodo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 *
 * @author abraao
 */
@Converter(autoApply = true)
public class EPeridoConvert implements AttributeConverter<EPeriodo, String> {

    @Override
    public String convertToDatabaseColumn(EPeriodo attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public EPeriodo convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(EPeriodo.values())
                .filter((p) -> p.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}