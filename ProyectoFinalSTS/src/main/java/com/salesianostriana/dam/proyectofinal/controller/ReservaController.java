package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.servicios.ClaseServicio;
import com.salesianostriana.dam.proyectofinal.servicios.ReservaServicio;

@Controller
public class ReservaController {

	@Autowired
	private ReservaServicio reservaServicio;
	
	@Autowired
	private ClaseServicio claseServicio;
	
	@GetMapping("/reservar-clase")
	public String showForm(Model model) {
		model.addAttribute("reserva", new ReservaClase());
		model.addAttribute("lista", claseServicio.findAll());
		return "formularioReserva";
	}
	
	@PostMapping("/nueva-reserva/submit")
	public String saveClase(@ModelAttribute("reserva") ReservaClase nueva) {
		reservaServicio.save(nueva);
		return "redirect:/inicio";
	}
	
	@GetMapping("/borrar-reserva/{id}")
	public String borrar(@PathVariable("id") long id) {
		reservaServicio.deleteById(id);
		return "redirect:/gestionar";
	}

}
