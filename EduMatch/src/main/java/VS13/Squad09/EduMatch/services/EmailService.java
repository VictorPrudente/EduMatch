package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.entities.Usuario;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;


    public void sendEmail(Usuario usuario, int numeroTemplate) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(usuario.getEmail());
            mimeMessageHelper.setSubject("Assunto Teste Template");
            mimeMessageHelper.setText(geContentFromTemplate(usuario, numeroTemplate ), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public String geContentFromTemplate(Usuario usuario, int numeroTemplate) throws Exception {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", usuario.getNome());
        dados.put("id", usuario.getId());
        dados.put("certificado", usuario.getCertificados());
        dados.put("email", from);
        Template tipoTemplate = switch (numeroTemplate){
            case 1 -> fmConfiguration.getTemplate("email-criar-usuario-template.ftl");
            case 2 -> fmConfiguration.getTemplate("email-atualizar-usuario-template.ftl");
            case 3 -> fmConfiguration.getTemplate("email-deletar-usuario-template.ftl");
            case 4 -> fmConfiguration.getTemplate("email-emitir-certificado-template.ftl");
            default -> throw new Exception();
        };

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(tipoTemplate, dados);
        return html;
    }


}
