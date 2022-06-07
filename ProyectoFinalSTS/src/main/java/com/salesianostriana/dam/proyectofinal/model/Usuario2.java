package com.salesianostriana.dam.proyectofinal.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Usuario2 implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;
	
	@Column(unique = true)
	private String email;
	
	private String contrasenha;
	private String tarjeta;
	private String fechaCaducidad;
	private int cvv;
	private boolean admin;
	
	public Usuario2(Long id, String nombre, String apellido1, String apellido2, String telefono, String email,
			String contrasenha, String tarjeta, String fechaCaducidad, int cvv, boolean admin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.email = email;
		this.contrasenha = contrasenha;
		this.tarjeta = tarjeta;
		this.fechaCaducidad = fechaCaducidad;
		this.cvv = cvv;
		this.admin = admin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String role = "ROLE_";
		if (admin) {
			role += "ADMIN";
		} else {
			role += "USER";
		}
		return Arrays.asList(new SimpleGrantedAuthority(role));
	
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String getPassword() {
		
		return contrasenha;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@ManyToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private ReservaClase reservasClases;
}