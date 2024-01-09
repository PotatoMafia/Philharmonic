package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CustomerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

//    public void save(Customer customer) {
//        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
//
//        // Проверка, был ли передан customerId
//        if (Objects.equals(customer.getCustomerId(), null)) {
//            // Если customerId не был передан, генерируем новый
//            customer.setCustomerId(findMaxCustomerId() + 1);
//            System.out.println("Generated CustomerId: " + customer.getCustomerId());
//        } else {
//            // Если customerId уже был установлен, используем переданное значение
//            System.out.println("Customer already has an Id: " + customer.getCustomerId());
//        }
//
//
//        insertActor.withTableName("C##AKUGACZ.\"Customer\"")
//                .usingColumns("\"Customer_id\"", "\"Name\"", "\"Surname\"", "\"Phone_number\"", "\"Address\"", "\"Email\"");
//
//        System.out.println("New CustomerId: " + customer.getCustomerId());
//        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(customer);
//        System.out.println(beanPropertySqlParameterSource);
//        insertActor.execute(beanPropertySqlParameterSource);
//    }

    public void save(Customer customer) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("C##AKUGACZ.\"Customer\"");
        insertActor.usingColumns("\"Customer_id\"", "\"Name\"", "\"Surname\"", "\"Phone_number\"", "\"Address\"", "\"Email\"");
        Map<String, Object> parameters = new HashMap<>(3);
        if (Objects.equals(customer.getCustomerId(), null)) {
            customer.setCustomerId(findMaxCustomerId() + 1);
            System.out.println("Generated CustomerId: " + customer.getCustomerId());
        } else {
            System.out.println("Customer already has an Id: " + customer.getCustomerId());
        }
        parameters.put("\"Customer_id\"", customer.getCustomerId());
        parameters.put("\"Name\"", customer.getName());
        parameters.put( "\"Surname\"", customer.getSurname());
        parameters.put("\"Phone_number\"",customer.getPhoneNumber() );
        parameters.put( "\"Address\"",customer.getAddress());
        parameters.put("\"Email\"",customer.getEmail());
        insertActor.execute(parameters);
    }


    public int findMaxCustomerId() {
        String sql = "SELECT MAX(\"Customer_id\") FROM C##AKUGACZ.\"Customer\"";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    public List<Customer> list() {

        String sql = "SELECT * FROM C##AKUGACZ.\"Customer\"";
        List<Customer> customerList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));

        return customerList;
    }

    public Customer get(int id) {
        return null;
    }

    public void delete(int id) {

    }

    public void update(Customer customer) {

    }
}
