package com.salesianostriana.dam.proyectofinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

	@Id @GeneratedValue
	private long idUsuario;
	
	private String nombre;
	
	private String apellido1;
	
	private String apellido2;
	
	private String telefono;
	
	private String correo;
	
	private String contrasenha;
	
	private String numTarjeta;
	
	private int cvv;
	
	private String fechaCaducidad;
	
	@ManyToOne
	private ClaseGym c;
}
