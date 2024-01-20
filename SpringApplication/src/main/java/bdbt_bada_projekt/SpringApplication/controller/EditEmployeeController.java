package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.entity.Artist;
import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.entity.Employee;
import bdbt_bada_projekt.SpringApplication.service.ArtistService;
import bdbt_bada_projekt.SpringApplication.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditEmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private final ArtistService artistService;

    @GetMapping("/edit_employee")
    public String showEditEmployeePage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees(); // Assuming you have a service for employees
        List<Artist> artists = artistService.getAllArtists();

        model.addAttribute("employees", employees);
        model.addAttribute("artists", artists);
        return "admin/edit_employee";
    }



    @PostMapping("/updateEmployeeName")
    public String updateEmployeeName(@RequestParam String email, @RequestParam String newName) {
        log.info("Updating employee information: Email={}, Attribute={}, NewValue={}", email, "name", newName);

        // Call the service method to perform the update
        employeeService.employeeUpdateByName(email, newName);

        return "redirect:/edit_employee";
    }

    @PostMapping("/updateEmployeeSurname")
    public String updateEmployeeSurname(@RequestParam String email, @RequestParam String newSurname) {
        log.info("Updating employee information: Email={}, Attribute={}, NewValue={}", email, "surname", newSurname);

        // Call the service method to perform the update
        employeeService.employeeUpdateBySurname(email, newSurname);

        return "redirect:/edit_employee";
    }

    @PostMapping("/updateStreet")
    public String updateEmployeeStreet(@RequestParam String email, @RequestParam String newStreet) {
        log.info("Updating employee information: Email={}, Attribute={}, NewValue={}", email, "street", newStreet);

        // Call the service method to perform the update
        employeeService.employeeUpdateByStreet(email, newStreet);

        return "redirect:/edit_employee";
    }

    @PostMapping("/updateJobTitle")
    public String updateEmployeeJobTitle(@RequestParam String email, @RequestParam String newJobTitle) {
        log.info("Updating employee information: Email={}, Attribute={}, NewValue={}", email, "jobTitle", newJobTitle);

        // Call the service method to perform the update
        employeeService.employeeUpdateByJobTitle(email, newJobTitle);

        return "redirect:/edit_employee";
    }

    @PostMapping("/updateLocalNumber")
    public String updateEmployeeLocalNumber(@RequestParam String email, @RequestParam String newLocalNumber) {
        log.info("Updating employee information: Email={}, Attribute={}, NewValue={}", email, "surname", newLocalNumber);

        // Call the service method to perform the update
        employeeService.employeeUpdateByLocalNumber(email, newLocalNumber);

        return "redirect:/edit_employee";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam String email) {
        employeeService.deleteEmployeeByEmail(email);
        return "redirect:/edit_employee";
    }

    @PostMapping("/createEmployee")
    public String createUser(@ModelAttribute("newEmployee") Employee employee,
                             @RequestParam(name = "isArtist", required = false) Boolean isArtist,
                             @RequestParam(name = "skills", required = false) String skills,
                             @RequestParam(name = "isSinger", required = false) String isSinger,
                             @RequestParam(name = "isMusician", required = false) String isMusician,
                             Model model, RedirectAttributes redirectAttributes) {

        // Check if the employee email is already in use
        if (employeeService.isEmailAlreadyInUse(employee)) {
            redirectAttributes.addFlashAttribute("emailError", "Email is already in use");
            redirectAttributes.addFlashAttribute("newEmployee", employee);
        } else {
            // Save the employee
            employeeService.saveEmployee(employee);

            // Check if the employee is an artist
            if (isArtist != null && isArtist) {
                // Create an Artist object and set the relevant fields
                Artist artist = new Artist();
                artist.setEmployeeId(employee.getEmployeeId());
                int id = employeeService.findByEmail(employee.getEmail()).getEmployeeId();
                log.info("For Artist setted id:[{}]", id);
                artist.setSkills(skills);
                if(isSinger==null) isSinger = "N";
                artist.setIsSinger(isSinger);
                if(isMusician==null) isMusician = "N";
                artist.setIsMusic(isMusician);

                // Save the artist
                artistService.save(artist);
            }
        }

        // Redirect back to the edit_users page after handling the form submission
        return "redirect:/edit_employee";
    }

    @PostMapping("/updateMusician")
    public String updateMusician(@RequestParam int id, @RequestParam String newMusician) {
        artistService.updateArtistIsMusic(id, newMusician);
        return "redirect:/edit_employee";
    }

    @PostMapping("/updateSinger")
    public String updateSinger(@RequestParam int id, @RequestParam String newSinger) {
        artistService.updateArtistIsSinger(id, newSinger);
        return "redirect:/edit_employee";
    }

    @PostMapping("/updateSkills")
    public String updateSkills(@RequestParam int id, @RequestParam String newSkills) {
        artistService.updateArtistSkills(id, newSkills);
        return "redirect:/edit_employee";
    }

    @PostMapping("/deleteArtist")
    public String deleteArtist(@RequestParam int id) {
        Artist artist = artistService.findByEmployeeId(id);
        if (artist != null) {
            artistService.deleteArtistByEmployeeId(id);
        }
        return "redirect:/edit_employee";
    }

}
