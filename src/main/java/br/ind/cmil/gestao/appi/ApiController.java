package br.ind.cmil.gestao.appi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * /**
 *
 * @author abraao
 */
@Controller
public class ApiController {
   
   
    
     @PostMapping("/login_success_handler")
  public String loginSuccessHandler() {
    //perform audit action
    return "/home";
  }

  @PostMapping("/login_failure_handler")
  public String loginFailureHandler() {
    //perform audit action
    return "login";
  }

}
