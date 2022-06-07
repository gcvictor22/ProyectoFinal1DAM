package com.salesianostriana.dam.proyectofinal.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ReservaClase {
	
	@Id @GeneratedValue
	private long id;
	
	private String nombreUsuario;
	private String email;
	private String tel;
	private double precioTotal;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaReserva;

	@ManyToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private ClaseGym clase;
	
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "reservasClases", fetch = FetchType.EAGER)
	private List <Usuario2> usuarios = new ArrayList();
}