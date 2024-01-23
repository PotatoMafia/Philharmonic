package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class EventDAOTest {
    private EventDAO dao;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private Event event;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");
        /* Import JdbcTemplate */
        dao = new EventDAO(new JdbcTemplate(datasource));
        event = new Event();
        event.setName("Test Event");
        event.setDate("2022-01-01");
        event.setTime("12:00");
        event.setDescription("Test description");
        event.setVenueId(1);
        event.setPhilharmonicId(1);
        event.setDate("2022-01-01");

    }
    @Test
    void list() {
        List<Event> eventList = dao.list();
        assertFalse(eventList.isEmpty());
    }

    @Test
    void save() {

        dao.save(event);
    }


    @Test
    void findByName() {
        assertFalse(dao.findByName("F") == null);
    }
}