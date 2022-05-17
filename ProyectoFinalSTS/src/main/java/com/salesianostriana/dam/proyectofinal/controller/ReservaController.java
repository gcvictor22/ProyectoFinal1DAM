package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.servicios.ReservaServicio;

@Controller
public class ReservaController {

	@Autowired
	private ReservaServicio reservaServicio;
	
	@GetMapping("/reservar-clase")
	public String showForm(Model model) {
		model.addAttribute("reserva", new ReservaClase());
		return "formularioReserva";
	}
	
	@PostMapping("/nueva-clase/submit")
	public String saveClase(@ModelAttribute("reserva") ReservaClase nueva) {
		reservaServicio.save(nueva);
		return "redirect:/";
	}
	
	@GetMapping("/borrar-reserva/{id}")
	public String borrar(@PathVariable("id") long id) {
		reservaServicio.deleteById(id);
		return "redirect:/";
	}

}
