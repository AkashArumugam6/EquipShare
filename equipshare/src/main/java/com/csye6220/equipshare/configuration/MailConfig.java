package com.csye6220.equipshare.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("equipshareapp@gmail.com"); 
        mailSender.setPassword("ya29.a0Ad52N38zkxDUPYifHUDLxCueItWiJQ0aXFOoKhXWdPE2nHkh3-D_NKoC_a5NXQjehgemRvC7MpBaK2DjNN1TEkhV4js31Gk5LeH-5EhdbeCJe7ado5OPJ8eM9qUHxn_7r0WoM9AWYz1JPFlXhuzSNZuPvb0awsMlx7fAaCgYKAX0SARESFQHGX2MivmaJqxE-bpbuIKBPdlWcjw0171");


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
        
        return mailSender;
    }
}
