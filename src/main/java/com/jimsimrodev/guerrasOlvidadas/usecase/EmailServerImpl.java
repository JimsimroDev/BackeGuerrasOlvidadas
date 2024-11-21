package com.jimsimrodev.guerrasOlvidadas.usecase;

import com.jimsimrodev.guerrasOlvidadas.infra.repository.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServerImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorro(String destinatario, String asunto, String mensaje) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("jhoangd.jgd@gmail.com");
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);

        mailSender.send(mailMessage);
    }
}
