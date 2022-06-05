package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinal.servicios.Usuario2Servicio;

@Controller
public class LoginController {

	@Autowired
	private Usuario2Servicio u2S;

    @GetMapping("/inicio")
    public String login(Model model, @RequestParam String email) {	
        model.addAttribute("usuarios", u2S.buscarPorEmail(email));
    	return "inicio";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index";
    }

}