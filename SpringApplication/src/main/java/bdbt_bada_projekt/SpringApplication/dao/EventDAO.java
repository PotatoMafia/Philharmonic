package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Event;
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
public class EventDAO {

    @Value("${spring.datasource.username}")
    private String username;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Event event) {
        try {
            SimpleJdbcInsert insertEvent = new SimpleJdbcInsert(jdbcTemplate);
            insertEvent.withTableName("C##AKUGACH.\"Events\"");
            insertEvent.usingColumns(
                    "\"Event_id\"",
                    "\"Name\"",
                    "\"Date\"",
                    "\"Time\"",
                    "\"Description\"",
                    "\"Venue_id\"",
                    "\"Philharmonic_id\"",
                    "\"Artist_id\""
            );
            Map<String, Object> parameters = new HashMap<>(3);
            if (Objects.equals(findMaxEventId(), null)) {
                event.setEventId(0);
            } else {
                event.setEventId(findMaxEventId() + 1);
            }

            log.info("DAO put id [{}] STATUS:SUCCESS", event.getEventId());
            parameters.put("\"Event_id\"", event.getEventId());
            parameters.put("\"Name\"", event.getName());
            log.info("DAO put name [{}] STATUS:SUCCESS", event.getName());
            parameters.put("\"Date\"", event.getDate());
            log.info("DAO put date [{}] STATUS:SUCCESS", event.getDate());
            parameters.put("\"Time\"", event.getTime());
            log.info("DAO put time [{}] STATUS:SUCCESS", event.getTime());
            parameters.put("\"Description\"", event.getDescription());
            log.info("DAO put description [{}] STATUS:SUCCESS", event.getDescription());
            parameters.put("\"Venue_id\"", event.getVenueId());
            log.info("DAO put venue id [{}] STATUS:SUCCESS", event.getVenueId());
            parameters.put("\"Philharmonic_id\"", event.getPhilharmonicId());
            log.info("DAO take event [{}] STATUS:SUCCESS", event.getEventId());
            parameters.put("\"Artist_id\"", event.getArtistId());
            insertEvent.execute(parameters);
            log.info("Event with id [{}] saved", event.getEventId());
        } catch (Exception e) {
            log.error("Error saving event: {}", e.getMessage());
            throw new RuntimeException("Error saving event", e);
        }
    }

    public Integer findMaxEventId() {
        String sql = "SELECT MAX(\"Event_id\") FROM C##AKUGACH.\"Events\"";
        Integer maxEventId = jdbcTemplate.queryForObject(sql, Integer.class);

        // Check for null and return 0 if null
        return maxEventId != null ? maxEventId : 0;
    }

    public void update(Event event) {
        try {
            String sql = "UPDATE C##AKUGACH.\"Events\" SET " +
                    "\"Name\" = ?, " +
                    "\"Date\" = ?, " +
                    "\"Time\" = ?, " +
                    "\"Description\" = ?, " +
                    "\"Venue_id\" = ?, " +
                    "\"Philharmonic_id\" = ?, " +
                    "\"Artist_id\" = ? " +
                    "WHERE \"Event_id\" = ?";

            jdbcTemplate.update(
                    sql,
                    event.getName(),
                    event.getDate(),
                    event.getTime(),
                    event.getDescription(),
                    event.getVenueId(),
                    event.getPhilharmonicId(),
                    event.getArtistId(),
                    event.getEventId()
            );


            log.info("Event with id [{}] updated", event.getEventId());
        } catch (Exception e) {
            log.error("Error updating event: {}", e.getMessage());
            throw new RuntimeException("Error updating event", e);
        }
    }

    public void delete(int eventId) {
        String sql = "DELETE FROM C##AKUGACH.\"Events\" WHERE \"Event_id\" = ?";
        try {
            jdbcTemplate.update(sql, eventId);
            log.info("Event with id [{}] deleted", eventId);
        } catch (Exception e) {
            log.error("Error deleting event with id [{}]: {}", eventId, e.getMessage());
            throw new RuntimeException("Error deleting event", e);
        }
    }

    public List<Event> list() {
        String sql = "SELECT * FROM C##AKUGACH.\"Events\"";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Event.class));
    }

    public Event findById(int eventId) {
        String sql = "SELECT * FROM C##AKUGACH.\"Events\" WHERE \"Event_id\" = ?";
        try {
            List<Event> events = jdbcTemplate.query(sql, new Object[]{eventId}, BeanPropertyRowMapper.newInstance(Event.class));
            return events.isEmpty() ? null : events.get(0);
        } catch (Exception e) {
            log.error("Error finding event by id: {}", e.getMessage());
            throw new RuntimeException("Error finding event by id", e);
        }
    }

    public Event findByName(String eventName) {
        eventName = eventName.trim();
        String sql = "SELECT * FROM C##AKUGACH.\"Events\" WHERE \"Name\" = ?";
        try {
            List<Event> events = jdbcTemplate.query(sql, new Object[]{eventName}, BeanPropertyRowMapper.newInstance(Event.class));
            return events.isEmpty() ? null : events.get(0);
        } catch (Exception e) {
            log.error("Error finding event by name: {}", e.getMessage());
            throw new RuntimeException("Error finding event by name", e);
        }
    }

    public  boolean isExistName(String name){
        name = name.trim();
        String sql = "SELECT * FROM C##AKUGACH.\"Events\" WHERE \"Name\" = ?";
        try {
            List<Event> events = jdbcTemplate.query(sql, new Object[]{name}, BeanPropertyRowMapper.newInstance(Event.class));
            return events.isEmpty() ?  false :true;
        } catch (Exception e) {
            log.error("Error finding event by name: {}", e.getMessage());
            throw new RuntimeException("Error finding event by name", e);
        }
    }





}
