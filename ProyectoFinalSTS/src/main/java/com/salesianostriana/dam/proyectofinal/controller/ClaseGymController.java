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
import com.salesianostriana.dam.proyectofinal.servicios.ClaseGymServicio;
import com.salesianostriana.dam.proyectofinal.servicios.ReservaServicio;

@Controller
public class ClaseGymController {

	@Autowired
	private ClaseGymServicio claseServicio;

	@Autowired
	private ReservaServicio reservaServicio;

	@GetMapping("/gestionar")
	public String gestionarClasesReservas(Model model) {
		model.addAttribute("lista", claseServicio.findAll());
		model.addAttribute("reservas", reservaServicio.findAll());

		return "gestionar";
	}

	@GetMapping("/gestionar-error")
	public String gestionarClasesReservasError(Model model) {
		model.addAttribute("lista", claseServicio.findAll());
		model.addAttribute("reservas", reservaServicio.findAll());
		model.addAttribute("errorEliminar", true);

		return "gestionar";
	}

	@GetMapping("/gestionar/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {

		Optional<ClaseGym> claseE = claseServicio.findById(id);

		if (claseE != null) {
			model.addAttribute("claseGym", claseE.get());
			return "formularioCrearEditar";

		} else {
			return "redirect:/gestionar";
		}

	}

	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("claseGym") ClaseGym c) {
		claseServicio.edit(c);
		return "redirect:/gestionar";
	}

	@GetMapping("/gestionar/nueva-clase")
	public String showForm(Model model) {
		model.addAttribute("claseGym", new ClaseGym());
		return "formularioCrearEditar";
	}

	@PostMapping("/gestionar/nueva-clase/submit")
	public String saveClase(@ModelAttribute("claseGym") ClaseGym nueva) {
		claseServicio.save(nueva);
		return "redirect:/gestionar";
	}

	@GetMapping("/borrar-clase/{id}")
	public String borrar(@PathVariable("id") long id) {

		if (claseServicio.findById(id).get().getReservas().size() > 0) {
			return "redirect:/gestionar-error";
		}

		claseServicio.deleteById(id);
		return "redirect:/gestionar";
	}

}