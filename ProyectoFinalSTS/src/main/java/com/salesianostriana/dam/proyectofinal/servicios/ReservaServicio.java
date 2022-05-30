package com.salesianostriana.dam.proyectofinal.servicios;

import java.time.LocalDate;
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
	
	public String mostrarMensajeTresReservas (String nombre) {
		List<ReservaClase> aux = buscarPorNombre(nombre);
		int contador = 0;
		double total = 0.0;
		
		for (int i = 0; i < aux.size(); i++) {
			total += aux.get(i).getPrecioTotal();
		}
		
		if(aux.size()>=6) {
			contador += 10;
			total = total - contador;
			
		}else if(aux.size()>=3) {
			contador += 5;
			total = total - contador;
			
		}else {
			total = total - contador;
		
		}
		
		return "A pagar: "+total+"€; Se han devuelto a "+nombre+" un total de "+contador+"€";
	}
	
	public boolean permitirReserva (String nombre, LocalDate fecha) {
		List<ReservaClase> aux = buscarPorNombre(nombre);
		
		
		for (int i = 0; i < aux.size(); i++) {
			if(aux.get(i).getNombreUsuario().equalsIgnoreCase(nombre) && aux.get(i).getFechaReserva().isEqual(fecha)) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}
}
