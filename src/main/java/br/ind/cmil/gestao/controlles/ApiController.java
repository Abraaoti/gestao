package br.ind.cmil.gestao.controlles;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /**
 *
 * @author abraao
 */
@RestController
public class ApiController {
    @RequestMapping("/free")
    public String auth() {
        return "estamos aqui!";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
