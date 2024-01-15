package bdbt_bada_projekt.SpringApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String aboutPage(Model model, Principal principal) {
        // Check if the user is logged in
        boolean loggedIn = principal != null;

        // If logged in, you can check their role (if you have a role system)
        String userRole = "USER"; // Set this according to the logic in your application

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("userRole", userRole);

        // Add other attributes that may be needed in your view here

        return "about"; // Return the name of the view (Thymeleaf template)
    }
}
