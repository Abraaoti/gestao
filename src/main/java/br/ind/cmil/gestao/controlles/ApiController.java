package br.ind.cmil.gestao.controlles;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /**
 *
 * @author abraao
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/test")
    public Authentication getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/free")
    public String auth() {
        return "estamos aqui!";
    }

}
