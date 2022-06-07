/**
 * 
 */
package com.salesianostriana.dam.proyectofinal.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;

/**
 * @author lmlopez
 *
 */
public interface ReservaRepository extends JpaRepository<ReservaClase, Long> {

	List<ReservaClase> findByNombreUsuarioContainsIgnoreCase(String nombre);
	
}
