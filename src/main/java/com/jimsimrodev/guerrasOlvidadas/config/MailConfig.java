package com.jimsimrodev.guerrasOlvidadas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
    mailSenderImpl.setHost("smtp.gmail.com");
    mailSenderImpl.setPort(587);
    mailSenderImpl.setUsername("jhoangd.jgd@gmail.com");
    mailSenderImpl.setPassword("odbm qjqw dwfw zofy");

    Properties props = mailSenderImpl.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debuh", "debug");

    return mailSenderImpl;
  }
}
