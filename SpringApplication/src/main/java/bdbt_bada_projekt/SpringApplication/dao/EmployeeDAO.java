package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Employee;
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
public class EmployeeDAO {

    @Value("${spring.datasource.username}")
    private String username;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ArtistDAO artistDAO;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Employee employee) {
        try {
            SimpleJdbcInsert insertEmployee = new SimpleJdbcInsert(jdbcTemplate);
            insertEmployee.withTableName("C##AKUGACH.\"Employees\"");
            insertEmployee.usingColumns(
                    "\"Employee_id\"",
                    "\"Name\"",
                    "\"Surname\"",
                    "\"Gender\"",
                    "\"Personal_number\"",
                    "\"Email\"",
                    "\"Employment_date\"",
                    "\"Job_title\"",
                    "\"Street\"",
                    "\"Local_number\"",
                    "\"PostCode\"",
                    "\"Pay_per_hour\"",
                    "\"Phone_number\"",
                    "\"Philharmonic_id\""
            );

            Map<String, Object> parameters = new HashMap<>(3);
            if (Objects.equals(findMaxEmployeeId(), null)) {
                employee.setEmployeeId(0);
            } else {
                employee.setEmployeeId(findMaxEmployeeId() + 1);
            }

            parameters.put("\"Employee_id\"", employee.getEmployeeId());
            parameters.put("\"Name\"", employee.getName());
            parameters.put("\"Surname\"", employee.getSurname());
            parameters.put("\"Gender\"", employee.getGender());
            parameters.put("\"Personal_number\"", employee.getPersonalNumber());
            parameters.put("\"Email\"", employee.getEmail());
            parameters.put("\"Employment_date\"", employee.getEmploymentDate());
            parameters.put("\"Job_title\"", employee.getJobTitle());
            parameters.put("\"Street\"", employee.getStreet());
            parameters.put("\"Local_number\"", employee.getLocalNumber());
            parameters.put("\"PostCode\"", employee.getPostCode());
            parameters.put("\"Pay_per_hour\"", employee.getPayPerHour());
            parameters.put("\"Phone_number\"", employee.getPhoneNumber());
            parameters.put("\"Philharmonic_id\"", employee.getPhilharmonic_id());

            insertEmployee.execute(parameters);
            log.info("Employee with email [{}] saved", employee.getEmail());
            log.info("Employee with id [{}] saved", employee.getEmployeeId());
        } catch (Exception e) {
            log.error("Error saving employee: {}", e.getMessage());
            throw new RuntimeException("Error saving employee", e);
        }
    }

    public Integer findMaxEmployeeId() {
        String sql = "SELECT MAX(\"Employee_id\") FROM C##AKUGACH.\"Employees\"";
        Integer maxEmployeeId = jdbcTemplate.queryForObject(sql, Integer.class);

        // Check for null and return 0 if null
        return maxEmployeeId != null ? maxEmployeeId : 0;
    }

    public void update(Employee employee) {
        try {
            String sql = "UPDATE C##AKUGACH.\"Employees\" SET " +
                    "\"Name\" = ?, " +
                    "\"Surname\" = ?, " +
                    "\"Gender\" = ?, " +
                    "\"Personal_number\" = ?, " +
                    "\"Email\" = ?, " +
                    "\"Employment_date\" = ?, " +
                    "\"Job_title\" = ?, " +
                    "\"Street\" = ?, " +
                    "\"Local_number\" = ?, " +
                    "\"PostCode\"  = ?, " +
                    "\"Pay_per_hour\" = ?, " +
                    "\"Phone_number\" = ? " +
                    "WHERE \"Email\" = ?";

            jdbcTemplate.update(
                    sql,
                    employee.getName(),
                    employee.getSurname(),
                    employee.getGender(),
                    employee.getPersonalNumber(),
                    employee.getEmail(),
                    employee.getEmploymentDate(),
                    employee.getJobTitle(),
                    employee.getStreet(),
                    employee.getLocalNumber(),
                    employee.getPostCode(),
                    employee.getPayPerHour(),
                    employee.getPhoneNumber(),
                    employee.getEmail()
            );

            log.info("Employee with email [{}] updated", employee.getEmail());
        } catch (Exception e) {
            log.error("Error updating employee: {}", e.getMessage());
            throw new RuntimeException("Error updating employee", e);
        }
    }

    public List<Employee> list() {
        String sql = "SELECT * FROM C##AKUGACH.\"Employees\"";
        List<Employee> employeeList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
        return employeeList;
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM C##AKUGACH.\"Employees\" WHERE \"Email\" = ?";
        if(artistDAO.findByEmployeeId(findByEmail(email).getEmployeeId()) != null){
           artistDAO.deleteByEmployeeId(findByEmail(email).getEmployeeId());
        }
        try {
            jdbcTemplate.update(sql, email);
            log.info("Employee with email [{}] deleted", email);
        } catch (Exception e) {
            log.error("Error deleting employee with email [{}]: {}", email, e.getMessage());
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    public Employee get(int id) {
        return null;
    }

    public void delete(int id) {
        String sql = "DELETE FROM C##AKUGACH.\"Employees\" WHERE \"Employee_id\" = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM C##AKUGACH.\"Employees\" WHERE \"Email\" = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
            return count != null && count > 0;
        } catch (Exception e) {
            log.error("Error checking email existence: {}", e.getMessage());
            return false;
        }
    }

    public Employee findByEmail(String email) {
        email = email.trim();
        String sql = "SELECT * FROM C##AKUGACH.\"Employees\" WHERE \"Email\" = ?";
        try {
            List<Employee> employees = jdbcTemplate.query(sql, new Object[]{email}, BeanPropertyRowMapper.newInstance(Employee.class));
            if (!employees.isEmpty()) {
                return employees.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error finding employee by email: {}", e.getMessage());
            return null;
        }
    }

    public Employee findByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM C##AKUGACH.\"Employees\" WHERE \"Employee_id\" = ?";
        try {
            List<Employee> employees = jdbcTemplate.query(sql, new Object[]{employeeId}, BeanPropertyRowMapper.newInstance(Employee.class));
            if (!employees.isEmpty()) {
                return employees.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error finding employee by Employee_id: {}", e.getMessage());
            return null;
        }
    }


    public List<Employee> findAll() {
        String sql = "SELECT * FROM C##AKUGACH.\"Employees\"";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
    }
}
