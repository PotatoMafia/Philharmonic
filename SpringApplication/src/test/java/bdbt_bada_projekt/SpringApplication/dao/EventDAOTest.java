package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventDAOTest {
    private EventDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("C##AKUGACZ");
        datasource.setPassword("AKUGACZ");
        datasource.setDriverClassName("oracle.jdbc.OracleDriver");
        /* Import JdbcTemplate */
        dao = new EventDAO(new JdbcTemplate(datasource));

    }
    @Test
    void list() {
        List<Event> eventList = dao.list();
        assertFalse(eventList.isEmpty());
    }
}