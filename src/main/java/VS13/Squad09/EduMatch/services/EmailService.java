package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Usuario;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;


    public void sendWithAttachment(Certificado certificado) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper;

        try{
            mimeMessageHelper = new MimeMessageHelper(message, true);
        } catch (MessagingException e){
            throw new Exception(e.getMessage());
        }

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(certificado.getUsuario().getEmail());
        mimeMessageHelper.setSubject("Certificado de conclusão");
        mimeMessageHelper.setText("teste");

        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("/resources/images/certificado.png").getFile();
        log.info(path);
        File file = new File(path);
        FileSystemResource files = new FileSystemResource(file);
        mimeMessageHelper.addAttachment(file.getName(), files);
        System.out.println("File: " + file.getPath());

        emailSender.send(message);
    }



    public void sendEmail(Usuario usuario, Certificado certificado, int numeroTemplate) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(usuario.getEmail());
            mimeMessageHelper.setSubject("Email ao usuário");
            mimeMessageHelper.setText(geContentFromTemplate(usuario, certificado, numeroTemplate ), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public String geContentFromTemplate(Usuario usuario, Certificado certificado,  int numeroTemplate) throws Exception {
        Map<String, Object> dados = new HashMap<>();

        if (usuario != null){
            dados.put("nome", usuario.getNome());
            dados.put("id", usuario.getId());
        }
        if (certificado != null){
            dados.put("nome", certificado.getUsuario().getNome());
            dados.put("sobrenome", certificado.getUsuario().getSobrenome());
            dados.put("trilha", certificado.getTrilha().name());
            dados.put("dificuldade", certificado.getDificuldade().name());
            dados.put("data", certificado.getConclusao());
        }
        dados.put("email", from);




        Template tipoTemplate = switch (numeroTemplate){
            case 1 -> fmConfiguration.getTemplate("email-criar-usuario-template.ftl");
            case 2 -> fmConfiguration.getTemplate("email-atualizar-usuario-template.ftl");
            case 3 -> fmConfiguration.getTemplate("email-deletar-usuario-template.ftl");
            case 4 -> fmConfiguration.getTemplate("emitir-certificado-template.ftl");
            default -> throw new Exception("Número de template inválido:" + numeroTemplate);
        };
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(tipoTemplate, dados);
        return html;
    }


}
