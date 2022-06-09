package com.salesianostriana.dam.proyectofinal.servicios;

import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.repositorios.Usuario2Repository;
import com.salesianostriana.dam.proyectofinal.servicios.base.ServicioBaseImpl;

@Service
public class Usuario2Servicio extends ServicioBaseImpl<Usuario2, Long, Usuario2Repository>{
	
	@Autowired
    private JavaMailSender emailSender;
	
	public Usuario2Servicio(Usuario2Repository repo) {
		super(repo);
	}
	
	public Optional<Usuario2> buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}

	@Override
	public Usuario2 save(Usuario2 a) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
		
		a.setContrasenha(encriptador.encode(a.getContrasenha()));
		
		return super.save(a);
	}
	
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("afitgo29@gmail.com");
	    mailSender.setPassword("adminfit&go");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.debug", true);
	    
	    return mailSender;
	}

    public void sendSimpleMessage(String to) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@baeldung.com");
        message.setTo(to); 
        message.setSubject("PRUEBA"); 
        message.setText("PRUEBA");
        emailSender.send(message);
    }

}