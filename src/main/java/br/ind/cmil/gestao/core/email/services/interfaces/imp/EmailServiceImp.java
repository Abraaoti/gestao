package br.ind.cmil.gestao.core.email.services.interfaces.imp;

import br.ind.cmil.gestao.core.email.services.interfaces.IEmailService;
import br.ind.cmil.gestao.model.entidades.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 *
 * @author abraao
 */
@Service
public class EmailServiceImp implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine template;

    @Override
    public void confirmarCadastro(Usuario user, String siteUR) throws MessagingException {

        String toAddress = user.getEmail();
        String fromAddress = "Your email address";
        String senderName = "cmil";
        String content = "Prezado [[name]],<br>Por gentileza clique no link para registrar seu cadastro:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFIQUE</a></h3>"
                + "gratidão,<br>"
                + "cmil.";

        String codigo = Base64.getEncoder().encodeToString(toAddress.getBytes());

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        //Context context = new Context();
        //context.setVariable("titulo", "Bem vindo a Plataforma CMIL");
       // context.setVariable("texto", "Precisamos que confirme seu cadastro, clicando no link abaixo");
       // context.setVariable("linkConfirmacao", "https://app-cmil.herokuapp.com/api/u/confirmacao/cadastro?codigo=" + codigo);

        content = content.replace("[[name]]", user.getNome());
        String verifyURL = siteUR + "/api/u/confirmacao/cadastro?codigo=" + codigo;

        content = content.replace("[[URL]]", verifyURL);

        // String html = template.process("email/confirmacao", context);
        helper.setTo(toAddress);
        helper.setText(content, true);
        helper.setSubject("Por gentileza, verifique o seu e-mail");
        helper.setFrom("nao_responder@cmil.com.br");

       // helper.addInline("logo", new ClassPathResource("/static/image/logo.png"));

        emailSender.send(message);
    }

    @Override
    public void redefinirSenha(String destino, String verificador) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Redefinição de Senha");
        context.setVariable("texto", "Para redefinir sua senha use o código de verficação "
                + "quando exigido no formulário.");
        context.setVariable("verificador", verificador);

        String html = template.process("email/confirmacao", context);
        helper.setTo(destino);
        helper.setText(html, true);
        helper.setSubject("Redefinição de Senha");
        helper.setFrom("nao_respoder@gmail.com");

        helper.addInline("logo", new ClassPathResource("/static/image/LOGO.png"));

        emailSender.send(message);
    }

    
}
