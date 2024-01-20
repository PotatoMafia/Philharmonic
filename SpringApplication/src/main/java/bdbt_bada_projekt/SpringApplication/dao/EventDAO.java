package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Event;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EventDAO {
    private JdbcTemplate jdbcTemplate;

    public EventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Event> list() {
        String sql = "SELECT * FROM C##AKUGACZ.\"Events\"";


        List<Event> eventList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Event.class));
        System.out.println(eventList);
        return eventList;
    }

    public void save(Event event) {

    }

    public Event get(int id) {
        return null;
    }

    public void update(Event event) {

    }

    public void delete(int id) {

    }
}
