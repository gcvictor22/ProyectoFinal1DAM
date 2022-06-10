package com.salesianostriana.dam.proyectofinal.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.proyectofinal.model.Usuario2;

public interface Usuario2Repository extends JpaRepository<Usuario2, Long>{

	Optional<Usuario2> findFirstByEmail(String email);
	
}