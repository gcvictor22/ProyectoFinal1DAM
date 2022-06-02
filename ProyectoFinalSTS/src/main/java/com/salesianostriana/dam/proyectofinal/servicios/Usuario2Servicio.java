package com.salesianostriana.dam.proyectofinal.servicios;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.repositorios.Usuario2Repository;
import com.salesianostriana.dam.proyectofinal.servicios.base.ServicioBaseImpl;

@Service
public class Usuario2Servicio extends ServicioBaseImpl<Usuario2, Long, Usuario2Repository>{

	public Usuario2Servicio(Usuario2Repository repo) {
		super(repo);
	}
	
	public Optional<Usuario2> buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}
	
}