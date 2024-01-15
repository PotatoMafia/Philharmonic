package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerDAOTest {
    private CustomerDAO dao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("C##AKUGACH");
        dataSource.setPassword("AKUGACH");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        /* Import JdbcTemplate */
        dao = new CustomerDAO(new JdbcTemplate(dataSource));

    }
    @Test
    void save() {
        // Arrange
        Integer id = dao.findMaxCustomerId() + 1;
        // Assuming this is inside a method or constructor
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setPhoneNumber("123456789");
        customer.setPersonalNumber("123456789");
        customer.setEmail("john.doe@example.com");
        customer.setStreet("Main Street");
        customer.setLocalNumber("123");
        customer.setPostcode("12345");
         // Assuming you have this field in your Customer class
        customer.setPassword("mySecretPassword");

        dao.save(customer);

    }



    @Test
    void list() {
        List<Customer> customerList = dao.list();
        System.out.println(customerList);
        assertTrue(customerList.size() == 1);
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
        dao.delete(1);
    }

    @Test
    void update() {
    }


    @Test
    public void testFindByEmail() {

        //System.out.println(dao.findByEmail("frog@gmail.com"));
        Customer customer = dao.findByEmail("john.doe@example.com                   ");
        String checkPassword = "mySecretPassword                                  ";
        System.out.println(customer.getPassword());
        System.out.println(customer + ":" + checkPassword);

    }

    @Test
    void testDelete() {
    }
}