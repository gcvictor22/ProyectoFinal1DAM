package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}

	@GetMapping("/exito")
	public String welcomNew(Model model) {
		model.addAttribute("exito", true);
		return "index";
	}
	
	@GetMapping("/errorUsuarioExiste")
	public String welcomRegisterError(Model model) {
		model.addAttribute("usuarioExiste", true);
		return "index";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/info")
	public String info() {
		return "info";
	}
	
	@GetMapping("/politica")
	public String politica() {
		return "politica";
	}

}
