package com.salesianostriana.dam.proyectofinal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class holamundo {
	@GetMapping ({"/", "welcome"})
	public String welcome (@RequestParam(name="nombre", required=false, defaultValue= "Mundo") String
	nombre, Model model) {
		
	}
	model.addAttribute("nombre", nombre);
	return "index";
}
