package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.dao.CustomerDAO;
import bdbt_bada_projekt.SpringApplication.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class CustomerControllerOld {
    @Autowired
    private final CustomerDAO dao;
    public CustomerControllerOld(CustomerDAO dao){
        this.dao = dao;
    }
    @GetMapping("/registration_main")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration_main";
    }
    @PostMapping("/registration_main")
    public String registerUser(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        dao.save(customer);
        redirectAttributes.addAttribute("name", customer.getName());
        return "redirect:/login?success";
    }
}
