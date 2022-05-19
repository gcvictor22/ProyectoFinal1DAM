package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/inicio")
    public String login() {	
        return "inicio";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index";
    }

}