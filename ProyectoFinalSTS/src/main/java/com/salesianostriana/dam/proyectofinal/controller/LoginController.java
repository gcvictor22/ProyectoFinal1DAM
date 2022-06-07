package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinal.servicios.ClaseGymServicio;

@Controller
public class LoginController {

	@Autowired
	private ClaseGymServicio claseServicio;

	@GetMapping("/inicio")
	public String login(Model model) {
		model.addAttribute("lista", claseServicio.findAll());
		return "inicio";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}

}