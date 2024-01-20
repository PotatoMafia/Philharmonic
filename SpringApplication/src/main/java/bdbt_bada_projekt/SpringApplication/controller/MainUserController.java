package bdbt_bada_projekt.SpringApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;


@Controller
public class MainUserController {

    @GetMapping("/main_user")
    public String showMainUserPage(Model model, HttpSession session) {
        // Your logic to retrieve data or perform operations before rendering the page

        // Add attributes to the model if needed

        Object loggedInUser =  session.getAttribute("loggedInUser");

        // Return the name of the Thymeleaf template (HTML page) to render
        return "user/main_user";
    }
}
