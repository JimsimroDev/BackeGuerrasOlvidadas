package com.jimsimrodev.guerrasOlvidadas.usecase.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;

@Service
public class EmailServerImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void recordarUsuario(String destinatario, String asunto, String nombre, String usuario) {
        MimeMessage mailMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mailMessage,
                    true, "UTF-8"
            );
            messageHelper.setFrom("jhoangd.jgd@gmail.com");
            messageHelper.setTo(destinatario);
            messageHelper.setSubject(asunto);

            Context context = new Context();
            context.setVariable("nombre", nombre);
            context.setVariable("usuario", usuario);

            String htmlContent = templateEngine.process("email", context);
            messageHelper.setText(htmlContent, true);
            //Adjuntar la imagen
            ClassPathResource imagen = new ClassPathResource("images/gerras-olvidadas.png");
            messageHelper.addInline("logo", imagen);

            mailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enviarLink(String destinatario, String asunto, String nombre) {

        MimeMessage mailMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mailMessage,
                    true, "UTF-8"
            );
            messageHelper.setFrom("jhoangd.jgd@gmail.com");
            messageHelper.setTo(destinatario);
            messageHelper.setSubject(asunto);

            Context context = new Context();
            context.setVariable("nombre", nombre);

            String htmlContent = templateEngine.process("reestablecerContrasena", context);
            messageHelper.setText(htmlContent, true);
            //Adjuntar la imagen
            ClassPathResource imagen = new ClassPathResource("images/gerras-olvidadas.png");
            messageHelper.addInline("logo", imagen);

            mailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
