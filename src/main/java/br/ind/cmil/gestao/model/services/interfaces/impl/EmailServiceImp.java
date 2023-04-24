package br.ind.cmil.gestao.model.services.interfaces.impl;



/**
 *
 * @author abraao
 */
//@Service
public class EmailServiceImp {
/**
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine template;

    @Autowired
    public EmailServiceImp(JavaMailSender mailSender, SpringTemplateEngine template) {
        this.mailSender = mailSender;
        this.template = template;
    }

    public void enviarPedidoDeConfirmacaoDeCadastro(String destino, String codigo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,"UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Bem vindo a Plataforma CMIL");
        context.setVariable("texto", "Precisamos que confirme seu cadastro, clicando no link abaixo");
        context.setVariable("linkConfirmacao", "https://app-cmil.herokuapp.com/u/confirmacao/cadastro?codigo=" + codigo);

        String html = template.process("email/confirmacao", context);
        helper.setTo(destino);
        helper.setText(html, true);
        helper.setSubject("Confirmacao de Cadastro");
        helper.setFrom("nao_responder@cmil.com.br");

        helper.addInline("logo", new ClassPathResource("/static/image/logo.png"));

        mailSender.send(message);
    }

    public void enviarPedidoRedefinicaoSenha(String destino, String verificador) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Redefinição de Senha");
        context.setVariable("texto", "Para redefinir sua senha use o código de verficação quando exigido no formulário.");
        context.setVariable("verificador", verificador);

        String html = template.process("email/confirmacao", context);
        helper.setTo(destino);
        helper.setText(html, true);
        helper.setSubject("Redefinição de Senha");
        helper.setFrom("nao_respoder@gmail.com");

        helper.addInline("logo", new ClassPathResource("/static/image/LOGO.png"));

        mailSender.send(message);
    }**/
}
