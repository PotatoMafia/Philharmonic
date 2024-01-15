package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
