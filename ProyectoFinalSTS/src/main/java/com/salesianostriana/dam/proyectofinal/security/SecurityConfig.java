package com.salesianostriana.dam.proyectofinal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.servicios.Usuario2Servicio;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private final UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/inicio/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/gestionar/**", "/gestion/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedPage("/error")
                .and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/inicio").failureUrl("/login-error").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
                .and().headers().frameOptions().disable();
    
    }
    
    @Bean
	public CommandLineRunner init(Usuario2Servicio servicio) {
    	return args -> {
    		Usuario2 u = new Usuario2();
			u.setAdmin(true);
			u.setNombre("admin");
			u.setApellido1("");
			u.setApellido2("");
			u.setTelefono("000000000");
			u.setEmail("admin");
			u.setContrasenha("admin");
			u.setTarjeta("0000000000000000");
			u.setFechaCaducidad("00/0000");
			u.setCvv(000);
			
			Usuario2 u2 = new Usuario2();
			u2.setAdmin(false);
			u2.setNombre("user");
			u2.setApellido1("");
			u2.setApellido2("");
			u2.setTelefono("111111111");
			u2.setEmail("user");
			u2.setContrasenha("1234");
			u2.setTarjeta("1111111111111111");
			u2.setFechaCaducidad("11/1111");
			u2.setCvv(111);
			
			servicio.save(u);
			servicio.save(u2);
    	};
    }
}
