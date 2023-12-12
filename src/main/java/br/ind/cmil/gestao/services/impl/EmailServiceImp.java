package br.ind.cmil.gestao.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
public class EmailServiceImp {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine template;

    @Autowired
    public EmailServiceImp(JavaMailSender mailSender, SpringTemplateEngine template) {
        this.emailSender = mailSender;
        this.template = template;
    }

    public void enviarEmail(String emailDestino, String siteUR, String codigo) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Link de confirmação de cadastro");
        context.setVariable("texto", "Precisamos que confirme seu cadastro, clicando no link abaixo");
        context.setVariable("linkConfirmacao", siteUR + "/u/confirmacao/cadastro?codigo="+codigo);

        String html = template.process("email/confirmacao", context);
        helper.setTo(emailDestino);
        helper.setText(html, true);
        helper.setSubject("Confirmação de Cadastro");
        helper.setFrom("nao_responder@cmil.com.br");

        helper.addInline("logo", new ClassPathResource("/static/image/logo.png"));
        emailSender.send(message);
    }

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
        helper.setFrom("elavokokassinda@gmail.com");

       helper.addInline("logo", new ClassPathResource("/static/image/logo.png"));

        emailSender.send(message);
    }

}
