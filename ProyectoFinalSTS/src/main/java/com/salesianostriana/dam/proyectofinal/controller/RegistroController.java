package com.salesianostriana.dam.proyectofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.servicios.Usuario2Servicio;

@Controller
public class RegistroController {

	@Autowired
	private Usuario2Servicio u2S;
	
	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("usuario", new Usuario2());
		return "registro";
	}
	
	@PostMapping("/registro/submit")
	public String nuevoRegistro (@ModelAttribute("usuario") Usuario2 nuevo){
		
		List <Usuario2> aux = u2S.findAll();
		
		for (int i = 0; i < aux.size(); i++) {
			if(aux.get(i).getEmail().equalsIgnoreCase(nuevo.getEmail())) {
				return "redirect:/errorUsuarioExiste";
			}
		}
		
		u2S.save(nuevo);
		return "redirect:/exito";
	}
	
}