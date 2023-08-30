package br.ind.cmil.gestao.controlles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /**
 *
 * @author abraao
 */
@RestController
public class ApiController {
    @GetMapping("/free")
    public String auth() {
        return "estamos aqui!";
    }
    @GetMapping("/login")
    public String login() {
         return "redirect:/login";
    }

}
