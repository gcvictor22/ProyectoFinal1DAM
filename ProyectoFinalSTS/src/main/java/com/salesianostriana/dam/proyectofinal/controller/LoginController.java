package com.salesianostriana.dam.proyectofinal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.servicios.ClaseGymServicio;
import com.salesianostriana.dam.proyectofinal.servicios.ReservaServicio;

@Controller
public class LoginController {

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate hoy = LocalDate.now();
	
	@Autowired
	private ClaseGymServicio claseServicio;
	
	@Autowired
	private ReservaServicio reservaServicio;

	@GetMapping("/inicio")
	public String login(Model model, @AuthenticationPrincipal Usuario2 usuario) {

		List<ReservaClase> aux = reservaServicio.findAll();

		for (int i = 0; i < aux.size(); i++) {
			if (aux.get(i).getFechaReserva().compareTo(hoy) < 0) {
				reservaServicio.delete(aux.get(i));
				aux.get(i).getClase().setPlazas(aux.get(i).getClase().getPlazas() + 1);
			}
		}

		model.addAttribute("nombreUsuario", usuario.getNombre());
		model.addAttribute("ap1", " " + usuario.getApellido1() + " ");
		model.addAttribute("ap2", usuario.getApellido2());
		model.addAttribute("lista", claseServicio.findAll());
		return "inicio";
	}
	
	@GetMapping("/inicio-sin-clases")
	public String loginSinClases(Model model, @AuthenticationPrincipal Usuario2 usuario) {

		List<ReservaClase> aux = reservaServicio.findAll();

		for (int i = 0; i < aux.size(); i++) {
			if (aux.get(i).getFechaReserva().compareTo(hoy) < 0) {
				reservaServicio.delete(aux.get(i));
				aux.get(i).getClase().setPlazas(aux.get(i).getClase().getPlazas() + 1);
			}
		}

		model.addAttribute("nombreUsuario", usuario.getNombre());
		model.addAttribute("ap1", " " + usuario.getApellido1() + " ");
		model.addAttribute("ap2", usuario.getApellido2());
		model.addAttribute("lista", claseServicio.findAll());
		model.addAttribute("sinClasesReservar", true);
		return "inicio";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}

}