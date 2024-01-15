package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class EditUsersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/edit_users")
    public String showEditUsersPage(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "admin/edit_users";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestParam String email, @RequestParam String role) {
        log.info("Updating role for customer with email: {}", email);
        customerService.customerUpdateByRole(email, role);
        return "redirect:/edit_users";
    }

    @PostMapping("/updateName")
    public String updateName(@RequestParam String email, @RequestParam String attributeName, @RequestParam String newName) {
        log.info("Updating customer information: Email={}, Attribute={}, NewValue={}", email, attributeName, newName);

        // Call the service method to perform the update
        customerService.customerUpdateByName(email,newName);

        return "redirect:/edit_users";
    }

    @PostMapping("/updateSurname")
    public String updateSurname(@RequestParam String email, @RequestParam String attributeSurname, @RequestParam String newSurname) {
        log.info("Updating customer information: Email={}, Attribute={}, NewValue={}", email, attributeSurname, newSurname);

        // Call the service method to perform the update
        customerService.customerUpdateBySurname(email,newSurname);

        return "redirect:/edit_users";
    }


    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam String email) {
        customerService.deleteCustomerByEmail(email);
        return "redirect:/edit_users";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("newUser") Customer newUser, Model model, RedirectAttributes redirectAttributes) {
        if (customerService.isEmailAlreadyInUse(newUser)) {
            redirectAttributes.addFlashAttribute("emailError", "Email is already in use");
            redirectAttributes.addFlashAttribute("newUser", newUser);
        } else {
            customerService.saveCustomer(newUser);
        }
        // Redirect back to the edit_users page after handling the form submission
        return "redirect:/edit_users";
    }
}
