package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private HttpSession session;
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        log.info("Test processLogin [{}]", email);

        String role = customerService.login(email, password);

        if (role != null) {
            session.setAttribute("role", role);
            if (role.equals("ADMIN")) {
                session.setAttribute("loggedInUser", email);
                session.setAttribute("registered", true);
                return "redirect:/main_admin";
            } else if (role.equals("USER")) {
                session.setAttribute("loggedInUser", email);
                session.setAttribute("registered", true);
                return "redirect:/main_user";
            }
        }

        // Login failed, add error attribute to the model
        model.addAttribute("error", "Invalid email or password");

        // Retrieve the list of customers to display on the login page
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);

        // Stay on the login page
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        log.info("Logged out");
        return "login";
    }

}
