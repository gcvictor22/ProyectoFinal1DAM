package com.salesianostriana.dam.proyectofinal.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinal.model.ClaseGym;
import com.salesianostriana.dam.proyectofinal.repositorios.ClaseGymRepository;
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
public class ClaseServicio {
	
	// Inyectamos la dependencia al nuevo estilo, sin @Autowired
	private ClaseGymRepository ClaseGymRepository;
	
	public ClaseServicio(ClaseGymRepository repo) {
		this.ClaseGymRepository = repo;
	}
	
	/**
	 * Inserta un nuevo ClaseGym
	 * 
	 * @param a el ClaseGym a insertar
	 * @return El ClaseGym ya insertado (con el Id no vacío).
	 */
	public ClaseGym add(ClaseGym a) { return ClaseGymRepository.save(a); }
	
	
	/**
	 * Edita un ClaseGym, si existe; si no, lo inserta como uno nuevo.
	 * @param a
	 * @return
	 */
	public ClaseGym edit(ClaseGym a) { return ClaseGymRepository.save(a); }

	/**
	 * Elimina el ClaseGym
	 * 
	 * @param a
	 */
	public void delete(ClaseGym a) { ClaseGymRepository.delete(a); }
	
	/**
	 * Elimina a un ClaseGym por su Id
	 * @param id
	 */
	public void delete(long id) { ClaseGymRepository.deleteById(id); }
	
	/**
	 * Devuelve todos los ClaseGyms
	 * @return
	 */
	public List<ClaseGym> findAll() { return ClaseGymRepository.findAll(); }
	
	
	/**
	 * Devuelve un ClaseGym en base a su Id
	 * @param id
	 * @return el ClaseGym encontrado o <code>null</code>
	 */
	public ClaseGym findById(long id) {
		return ClaseGymRepository.findById(id).orElse(null);
	}

}
