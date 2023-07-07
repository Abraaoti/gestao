package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.entidades.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author abraao
 */
public class TokenService {

    @Value("${app.jwt-secret}")
    private String jwtSigningKey;

    public String gerarToken(Usuario usuario) {
        String token = Jwts.builder().setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
        return token;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
