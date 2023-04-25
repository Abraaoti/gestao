package br.ind.cmil.gestao.model.converter;

import br.ind.cmil.gestao.model.entidades.Perfil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class PerfilConverter implements Converter<String[], List<Perfil>> {

    @Override
    public List<Perfil> convert(String[] source) {
        List<Perfil> perfis = new ArrayList<>();
        for (String id : source) {
            if (!id.equals("0")) {
                perfis.add(new Perfil(Long.parseLong(id)));
            }
        }
        return perfis;
    }

}
