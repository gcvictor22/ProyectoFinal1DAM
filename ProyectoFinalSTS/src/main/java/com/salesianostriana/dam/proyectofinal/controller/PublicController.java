package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
	
	
	@GetMapping("/")
	public String welcome() {
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
	
	@GetMapping("/registro")
	public String registro() {
		return "registro";
	}
	
	@GetMapping("/politica")
	public String politica() {
		return "politica";
	}

}