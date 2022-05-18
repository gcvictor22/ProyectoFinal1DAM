package com.salesianostriana.dam.proyectofinal.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.repositorios.ReservaRepository;
import com.salesianostriana.dam.proyectofinal.servicios.base.ServicioBaseImpl;
/**
 * Clase que encapsula un servicio CRUD de ClaseGyms. Se trata de un "envoltorio"
 * del repositorio de ClaseGyms, ya que tendrá los mismos métodos, y ninguno más.
 * En ejemplos y tutoriales posteriores ofreceremos la posibilidad de crear un servicio
 * abstracto, del cual podrán extender los demás, para preocuparse solo de la 
 * lógica propia del servicio.
 * 
 * @author luismi
 *
 */
@Service
public class ReservaServicio extends ServicioBaseImpl<ReservaClase, Long, ReservaRepository>{

public List<ReservaClase> buscarPorNombre (String nombre){
		
		return repositorio.findByNombreUsuarioContainsIgnoreCase(nombre);
	}
	
}