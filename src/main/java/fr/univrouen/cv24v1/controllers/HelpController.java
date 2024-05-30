package fr.univrouen.cv24v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HelpController {

	 @GetMapping("/help")
    public String showHelp(Model model) {
        // Vous pouvez également ajouter des modèles ou des données à passer à la vue si nécessaire
        return "help";  // Nom du fichier HTML ou XHTML dans 'src/main/resources/templates'
    }
}
