package br.ind.cmil.gestao.configs.securitys;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

/**
 *
 * @author abraao
 */
public class SecurityConstants {

    public static final long EXPIRATION_TIME = 86400000; // 15 mins
    public static final long REFRESH_EXPIRATION = 604800000; // 15 mins
    public static final String EMISSOR = "cmil.ind.br";
    public final static String SECRET_KEYS = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public final static Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}
