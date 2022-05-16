package com.salesianostriana.dam.proyectofinal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectofinal.model.ClaseGym;
import com.salesianostriana.dam.proyectofinal.servicios.ClaseServicio;

@Controller
public class ClaseGymController {

	@Autowired
	private ClaseServicio claseServicio;

	@GetMapping("/gestionar")
	public String list(Model model) {
		model.addAttribute("lista", claseServicio.findAll());
		return "gestionar";
	}

	@GetMapping("/detalle/{id}")
	public String detail(Model model, @PathVariable Long id) {

		Optional<ClaseGym> result = claseServicio.findById(id);

		if (result.isPresent()) {
			model.addAttribute("alumno", result.get());
			return "clase/detail";
		} else {
			return "redirect:/inicio";
		}

	}
	
	@GetMapping("/gestionar/nueva-clase")
	public String showForm(Model model) {
		model.addAttribute("claseGym", new ClaseGym());
		return "gestionar";
	}
	
	@PostMapping("/gestionar/nueva-clase/submit")
	public String saveClase(@ModelAttribute("claseGym") ClaseGym nueva) {
		claseServicio.save(nueva);
		return "redirect:/gestionar";
	}

}
