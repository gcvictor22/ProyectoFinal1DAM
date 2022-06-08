package com.salesianostriana.dam.proyectofinal.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.servicios.ClaseGymServicio;
import com.salesianostriana.dam.proyectofinal.servicios.ReservaServicio;

import java.util.List;

@Controller
public class ReservaController {

	@Autowired
	private ReservaServicio reservaServicio;

	@Autowired
	private ClaseGymServicio claseServicio;

	@GetMapping("/reservar-clase")
	public String showForm(Model model, @AuthenticationPrincipal Usuario2 usuario) {
		model.addAttribute("nombreUsuarioCompleto",
				usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
		model.addAttribute("telefono", usuario.getTelefono());
		model.addAttribute("correo", usuario.getEmail());
		model.addAttribute("reserva", new ReservaClase());
		model.addAttribute("lista", claseServicio.findAll());
		return "formularioReserva";
	}
	
	@GetMapping("/reservar-clase-error")
	public String showFormError(Model model, @AuthenticationPrincipal Usuario2 usuario) {
		model.addAttribute("nombreUsuarioCompleto",
				usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
		model.addAttribute("telefono", usuario.getTelefono());
		model.addAttribute("correo", usuario.getEmail());
		model.addAttribute("reserva", new ReservaClase());
		model.addAttribute("lista", claseServicio.findAll());
		model.addAttribute("sinPlazas", true);
		return "formularioReserva";
	}

	@PostMapping("/nueva-reserva/submit")
	public String saveClase(@ModelAttribute("reserva") ReservaClase nueva, @AuthenticationPrincipal Usuario2 usuario) {
		
		List <ReservaClase> aux = reservaServicio.findAll();
		
		nueva.setNombreUsuario(usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
		nueva.setTel(usuario.getTelefono());
		nueva.setEmail(usuario.getEmail());
		nueva.setFechaReserva(LocalDate.now());
		nueva.setPrecioTotal(nueva.getClase().getPrecio());
		nueva.setUsuarios(usuario);
		
		for (int i = 0; i < aux.size(); i++) {
			if(nueva.getClase().getPlazas()<=0 || nueva.getEmail() == aux.get(i).getEmail() && nueva.getClase().getId() == aux.get(i).getClase().getId()) {
				return"redirect:/reservar-clase-error";
			}
		}
		nueva.getClase().setPlazas(nueva.getClase().getPlazas()-1);
		reservaServicio.save(nueva);
		return "redirect:/inicio";
	}

	@GetMapping("/borrar-reserva/{id}")
	public String borrar(@PathVariable("id") long id) {
		reservaServicio.findById(id).get().getClase().setPlazas(reservaServicio.findById(id).get().getClase().getPlazas()+1);
		reservaServicio.deleteById(id);
		return "redirect:/gestionar";
	}

	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String nombre) {
		model.addAttribute("lista", claseServicio.findAll());
		model.addAttribute("reservas", reservaServicio.buscarPorNombre(nombre));
		model.addAttribute("precios", (reservaServicio.mostrarMensajeTresReservas(nombre)));
		return "gestionar";
	}

}
