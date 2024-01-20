package bdbt_bada_projekt.SpringApplication.service;

import bdbt_bada_projekt.SpringApplication.dao.EmployeeDAO;
import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeDAO employeeDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeDAO.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new User(employee.getEmail(), "defaultPassword", new ArrayList<>());
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        if (!employeeDAO.emailExists(employee.getEmail())) {
            employeeDAO.save(employee);
        } else {
            throw new RuntimeException("Email is already in use");
        }
    }

    public Employee findByEmail(String email) {
        Employee employee = employeeDAO.findByEmail(email);

        if (employee == null) {
            throw new EntityNotFoundException("Employee not found with email: " + email);
        }

        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    public void deleteEmployeeByEmail(String email) {
        log.info("Successfully deleted:[{}]",email);
        employeeDAO.deleteByEmail(email);
    }

    public void employeeUpdateByName(String email, String name){
        Employee employee = findByEmail(email);
        if (employee != null) {
            employee.setName(name);
            employeeDAO.update(employee);
        }
    }

    public void employeeUpdateBySurname(String email, String newSurname) {
        Employee employee = findByEmail(email);
        if (employee != null) {
            employee.setSurname(newSurname);
            employeeDAO.update(employee);
        }
    }

    public void deleteEmployeeById(int employeeId) {
        employeeDAO.delete(employeeId);
    }

    public void employeeUpdateByJobTitle(String email, String newTitle) {
        Employee employee = findByEmail(email);
        if (employee != null) {
            employee.setJobTitle(newTitle);
            employeeDAO.update(employee);
        }
    }

    public void employeeUpdateByStreet(String email, String street) {
        Employee employee = findByEmail(email);
        if (employee != null) {
            employee.setStreet(street);
            employeeDAO.update(employee);
        }
    }

    public void employeeUpdateByLocalNumber(String email, String localNumber) {
        Employee employee = findByEmail(email);
        if (employee != null) {
            employee.setLocalNumber(localNumber);
            employeeDAO.update(employee);
        }
    }

    public boolean isEmailAlreadyInUse(Employee newEmployee) {
        return employeeDAO.emailExists(newEmployee.getEmail());
    }
}
