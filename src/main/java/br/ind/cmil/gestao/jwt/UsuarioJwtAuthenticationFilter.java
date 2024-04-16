package br.ind.cmil.gestao.jwt;

import br.ind.cmil.gestao.jwt.service.JwtService;
import br.ind.cmil.gestao.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioJwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    public JwtService jwtService;
    @Autowired
    public UsuarioRepository ur;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //String token = header.substring("HTTP_TOKEN".length() + 1);
        String token = header.substring(7);
        jwtService.isTokenValid(token);

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            HttpSession session = request.getSession();

            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        String userEmail = jwtService.parseToken(token);

        UserDetails userDetails = (UserDetails) ur.findByLogin(userEmail).get();

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

}
