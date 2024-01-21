package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.entity.Ticket;
import bdbt_bada_projekt.SpringApplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }


    @PostMapping("/registration")
    public String save(@ModelAttribute("customer") Customer customer, Model model, RedirectAttributes redirectAttributes) {
        if (customerService.isEmailAlreadyInUse(customer)) {
            redirectAttributes.addFlashAttribute("emailError", "Email is already in use");
            redirectAttributes.addFlashAttribute("customer", customer);
            return "redirect:/registration";
        }
        customerService.saveCustomer(customer);
        return "redirect:/login";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer) {
        // Update customer details in the database
        customerService.customerUpdate(customer);

        // Redirect to a confirmation page or any other page
        return "redirect:/confirmationPage";
    }

    @GetMapping("/main_user")
    public String showMainUserPage(Model model, HttpSession session) {
        // Your logic to retrieve data or perform operations before rendering the page

        // Add attributes to the model if needed
        // Get the logged-in customer's email from the session
        String loggedInUserEmail = (String) session.getAttribute("loggedInUser");

        Object loggedInUser =  session.getAttribute("loggedInUser");

        // Retrieve customer details from the database based on email
        List<Ticket> tickets;
        tickets = customerService.getAllTicketsForCustomer(loggedInUserEmail);
        model.addAttribute("tickets", tickets);

        Customer customer = customerService.findByEmail(loggedInUserEmail);
            // Add customer details to the model
        model.addAttribute("customer", customer);

        // Return the name of the Thymeleaf template (HTML page) to render
        return "user/main_user";
    }



//    @RequestMapping(value = {"/registration"})
//    public String save(@ModelAttribute("customer") Customer customer) {
//
//        customerService.saveCustomer(customer);
//
//        return "/login";
//
//
//    }

//
}
