package fr.univrouen.cv24v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("projectName", "CV24v1");
        model.addAttribute("versionNumber", "1.0");
        model.addAttribute("teamMembers", "Benmammar, Mohamed Houssam");
        model.addAttribute("universityLogo", "/images/Université_de_Rouen.png");  
        return "home";
    }
}

