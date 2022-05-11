package com.salesianostriana.dam.proyectofinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaseGym {

	@Id @GeneratedValue
	private long idReserva;
	
	private long idUsuario;
	
	private String tipoClase;
	
}