package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    private EmployeeDAO dao;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        /* Import JdbcTemplate */
        dao = new EmployeeDAO(new JdbcTemplate(dataSource));

    }

    @Test
    void save() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setGender("M");
        employee.setPersonalNumber("123456789");
        employee.setEmail("john.doe@example.com");
        ///employee.setEmploymentDate(new Date());
        employee.setJobTitle("Software Engineer");
        employee.setStreet("Main Street");
        employee.setLocalNumber("123");
        employee.setPostCode("12345");
        employee.setPayPerHour(20.0);
        employee.setPhoneNumber("123-456-7890");

        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Surname: " + employee.getSurname());
        System.out.println("Gender: " + employee.getGender());
        System.out.println("Personal Number: " + employee.getPersonalNumber());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Employment Date: " + employee.getEmploymentDate());
        System.out.println("Job Title: " + employee.getJobTitle());
        System.out.println("Address: " + employee.getStreet());
        System.out.println("Pay Per Hour: " + employee.getPayPerHour());
        System.out.println("Phone Number: " + employee.getPhoneNumber());

        dao.save(employee);


    }

    @Test
    void testSave() {
    }

    @Test
    void findMaxEmployeeId() {
    }

    @Test
    void update() {
    }

    @Test
    void list() {
    }

    @Test
    void deleteByEmail() {
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    @Test
    void emailExists() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findAll() {
    }
}