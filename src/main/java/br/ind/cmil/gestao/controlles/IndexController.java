package br.ind.cmil.gestao.controlles;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@Validated
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200/")
public class IndexController {

    @GetMapping({"/", "/login"})
    public String home() {
        return "login";
    }

}
