package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {
    private CustomerDAO dao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("C##AKUGACZ");
        dataSource.setPassword("AKUGACZ");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        /* Import JdbcTemplate */
        dao = new CustomerDAO(new JdbcTemplate(dataSource));

    }
    @Test
    void save() {
        Integer id = dao.findMaxCustomerId() + 1;
        Customer customer = new Customer(id, "O", "A", "B","C","D","E");
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
    }

    @Test
    void update() {
    }



}