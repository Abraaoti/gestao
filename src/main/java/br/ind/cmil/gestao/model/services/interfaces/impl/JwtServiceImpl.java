package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.JwtTokenMalformedException;
import br.ind.cmil.gestao.exceptions.JwtTokenMissingException;
import br.ind.cmil.gestao.model.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.model.services.interfaces.JwtService;

/**
 *
 * @author abraao
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String jwtSigningKey;
    @Value("${jwt.token.validity}")
    private long tokenValidity;

    @Override
    public String parseToken(String token) {
        try {

            Claims body = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();

            return body.getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    @Override
    public String generateToken(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getNome());

        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + tokenValidity;

        Date exp = new Date(expMillis);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    @Override
    public void isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }

    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
