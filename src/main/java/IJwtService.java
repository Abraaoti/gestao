

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author abraao
 */
public interface IJwtService {

    String parseToken(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
