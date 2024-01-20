package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Repository
@Component
@Slf4j
public class CustomerDAO {

    @Value("${spring.datasource.username}")
    private String username;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Customer customer) {
        try {
            SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
            insertActor.withTableName("\"Customers\"");
            insertActor.usingColumns("\"Customer_id\"", "\"Name\"", "\"Surname\"", "\"Phone_number\"", "\"Personal_number\"", "\"Email\"", "\"Street\"", "\"Local_number\"", "\"Postcode\"", "\"Philharmonic_id\"","\"Password\"", "\"Role\"");

            Map<String, Object> parameters = new HashMap<>(3);
            if (Objects.equals(findMaxCustomerId(), null)) {
                customer.setCustomerId(0);
            } else {
                customer.setCustomerId(findMaxCustomerId() + 1);
            }


            parameters.put("\"Customer_id\"", customer.getCustomerId());
            parameters.put("\"Name\"", customer.getName());
            parameters.put("\"Surname\"", customer.getSurname());
            parameters.put("\"Phone_number\"", customer.getPhoneNumber());
            parameters.put("\"Personal_number\"", customer.getPersonalNumber());
            parameters.put("\"Email\"", customer.getEmail());
            parameters.put("\"Street\"", customer.getStreet());
            parameters.put("\"Local_number\"", customer.getLocalNumber());
            parameters.put("\"Postcode\"", customer.getPostcode());
            parameters.put("\"Philharmonic_id\"", customer.getPhilharmonic_id());
            parameters.put("\"Password\"", customer.getPassword());
            parameters.put("\"Role\"", customer.getRole());

            insertActor.execute(parameters);
            log.info("Customer with email [{}] saved", customer.getEmail());
//            System.out.println("Customer saved successfully.");
        } catch (Exception e) {
//            System.out.println("Error saving customer: " + e.getMessage());
            // Optionally, log the exception using a logging framework
            //logger.error("Error saving customer", e);
            throw new RuntimeException("Error saving customer", e);
        }
    }



    public Integer findMaxCustomerId() {
        String sql = "SELECT MAX(\"Customer_id\") FROM \"Customers\"";
        Integer maxCustomerId = jdbcTemplate.queryForObject(sql, Integer.class);

        // Check for null and return 0 if null
        return maxCustomerId != null ? maxCustomerId : 0;
    }

    public void update(Customer customer) {
        try {
            String sql = "UPDATE \"Customers\" SET " +
                    "\"Name\" = ?, " +
                    "\"Surname\" = ?, " +
                    "\"Phone_number\" = ?, " +
                    "\"Personal_number\" = ?, " +
                    "\"Street\" = ?, " +
                    "\"Local_number\" = ?, " +
                    "\"Postcode\" = ?, " +
                    "\"Philharmonic_id\" = ?, " +
                    "\"Password\" = ?, " +
                    "\"Role\" = ? " +
                    "WHERE \"Email\" = ?";

            jdbcTemplate.update(
                    sql,
                    customer.getName(),
                    customer.getSurname(),
                    customer.getPhoneNumber(),
                    customer.getPersonalNumber(),
                    customer.getStreet(),
                    customer.getLocalNumber(),
                    customer.getPostcode(),
                    customer.getPhilharmonic_id(),
                    customer.getPassword(),
                    customer.getRole(),
                    customer.getEmail()
            );

            log.info("Customer with email [{}] updated", customer.getEmail());
        } catch (Exception e) {
            log.error("Error updating customer: {}", e.getMessage());
            throw new RuntimeException("Error updating customer", e);
        }
    }



    public List<Customer> list() {

        String sql = "SELECT * FROM \"Customers\"";
        List<Customer> customerList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));

        return customerList;
    }


    public void deleteByEmail(String email) {
        String sql = "DELETE FROM \"Customers\" WHERE \"Email\" = ?";
        try {
            jdbcTemplate.update(sql, email);
            log.info("Customer with email [{}] deleted", email);
        } catch (Exception e) {
            log.error("Error deleting customer with email [{}]: {}", email, e.getMessage());
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    public Customer get(int id) {
        return null;
    }

    public void delete(int id) {
        String sql = "DELETE FROM \"Customers\" WHERE \"Customer_id\" = ?";
        try {
            jdbcTemplate.update(sql, id);
//            System.out.println("Customer deleted successfully.");
        } catch (Exception e) {
//            System.out.println("Error deleting customer: " + e.getMessage());
            throw new RuntimeException("Error deleting customer", e);
        }
    }



    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM \"Customers\" WHERE \"Email\" = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
            return count != null && count > 0;
        } catch (Exception e) {
            log.error("Error checking email existence: {}", e.getMessage());
            return false;
        }
    }

    public Customer findByEmail(String email) {
        email = email.trim();
        String sql = "SELECT * FROM \"Customers\" WHERE \"Email\" = ?";
        try {
            List<Customer> customers = jdbcTemplate.query(sql, new Object[]{email}, BeanPropertyRowMapper.newInstance(Customer.class));
            if (!customers.isEmpty()) {
                return customers.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error finding customer by email: " + e.getMessage());
            return null;
        }
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM \"Customers\"";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
    }





}
