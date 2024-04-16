
package br.ind.cmil.gestao.usuario.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ti
 */
public record RecoveryUsuarioDTO(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCadastro,
        LocalDateTime updatedAt,
        Boolean ativo,
        String verificador,
        List<String> perfis) {

}
