package br.ind.cmil.gestao.model.dto.mappers;

/**
 *
 * @author abraao
 */
//@Component
public class TokenMapper {
/**
    public TokenDTO toDTO(Token t) {
        if (t == null) {
            return null;
        }
        RegistroUsuarioMapper lm = new RegistroUsuarioMapper();
        UsuarioDATA usuario = lm.toDTO(t.getUser());
        return new TokenDTO(t.getToken(),t.getTimeStamp(),t.getExpireAt(), t.getTokenType().getValue());
    }

    public Token toEntity(TokenDTO dto) {
       
        
        if (dto == null) {
            return null;
        }
        Token t = new Token();
        t.setToken(dto.token());
        t.setTimeStamp(dto.timeStamp());
        t.setExpireAt(dto.expireAt());
        t.setTokenType(convertGeneroValue(dto.tokenType()));
        
       
        return t;
    }

    public TipoToken convertGeneroValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Bearer " ->
                TipoToken.BEARER;
           
            default ->
                throw new IllegalArgumentException(" TipoToken invalido " + value);
        };
    }
*/
   
}
