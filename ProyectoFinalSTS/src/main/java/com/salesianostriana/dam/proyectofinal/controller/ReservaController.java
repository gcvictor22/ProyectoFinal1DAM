package com.salesianostriana.dam.proyectofinal.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.servicios.ClaseGymServicio;
import com.salesianostriana.dam.proyectofinal.servicios.ReservaServicio;

@Controller
public class ReservaController {

	@Autowired
	private ReservaServicio reservaServicio;
	
	@Autowired
	private ClaseGymServicio claseServicio;
	
	@GetMapping("/reservar-clase")
	public String showForm(Model model) {
		model.addAttribute("reserva", new ReservaClase());
		model.addAttribute("lista", claseServicio.findAll());
		return "formularioReserva";
	}
	
	@PostMapping("/nueva-reserva/submit")
	public String saveClase(@ModelAttribute("reserva") ReservaClase nueva) {
		nueva.setFechaReserva(LocalDate.now());
		nueva.setPrecioTotal(nueva.getClase().getPrecio());
		reservaServicio.save(nueva);
		return "redirect:/inicio";
	}
	
	@GetMapping("/borrar-reserva/{id}")
	public String borrar(@PathVariable("id") long id) {
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
