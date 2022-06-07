package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.servicios.ClaseGymServicio;

@Controller
public class LoginController {
	
	@Autowired
	private ClaseGymServicio claseServicio;
	
    @GetMapping("/inicio")
    public String login(Model model, @AuthenticationPrincipal Usuario2 usuario) {
    	model.addAttribute("nombreUsuario", usuario.getNombre());
    	model.addAttribute("ap1", " "+usuario.getApellido1()+" ");
    	model.addAttribute("ap2", usuario.getApellido2());
		model.addAttribute("lista", claseServicio.findAll());
    	return "inicio";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index";
    }

}