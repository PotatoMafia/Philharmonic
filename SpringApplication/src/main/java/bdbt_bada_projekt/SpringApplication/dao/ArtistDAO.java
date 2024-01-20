package bdbt_bada_projekt.SpringApplication.dao;

import bdbt_bada_projekt.SpringApplication.entity.Artist;
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
public class ArtistDAO {

    @Value("${spring.datasource.username}")
    private String username;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArtistDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Artist artist) {
        try {
            SimpleJdbcInsert insertArtist = new SimpleJdbcInsert(jdbcTemplate);
            insertArtist.withTableName("\"Artists\"");
            insertArtist.usingColumns(
                    "\"Employee_id\"",
                    "\"Skills\"",
                    "\"Is_music\"",
                    "\"Is_singer\""
            );

            Map<String, Object> parameters = new HashMap<>(3);


            parameters.put("\"Employee_id\"", artist.getEmployeeId());
            parameters.put("\"Skills\"", artist.getSkills());
            parameters.put("\"Is_music\"", artist.getIsMusic());
            parameters.put("\"Is_singer\"", artist.getIsSinger());

            insertArtist.execute(parameters);
            log.info("Artist with employeeId [{}] saved", artist.getEmployeeId());
        } catch (Exception e) {
            log.error("Error saving artist: {}", e.getMessage());
            throw new RuntimeException("Error saving artist", e);
        }
    }


    public List<Artist> findAll() {
        String sql = "SELECT * FROM \"Artists\"";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Artist.class));
    }

    public void update(Artist artist) {
        try {
            String sql = "UPDATE \"Artists\" SET " +
                    "\"Skills\" = ?, " +
                    "\"Is_music\" = ?, " +
                    "\"Is_singer\" = ? " +
                    "WHERE \"Employee_id\" = ?";

            jdbcTemplate.update(
                    sql,
                    artist.getSkills(),
                    artist.getIsMusic(),
                    artist.getIsSinger(),
                    artist.getEmployeeId()
            );

            log.info("Artist with employeeId [{}] updated", artist.getEmployeeId());
        } catch (Exception e) {
            log.error("Error updating artist: {}", e.getMessage());
            throw new RuntimeException("Error updating artist", e);
        }
    }

    public List<Artist> list() {
        String sql = "SELECT * FROM \"Artists\"";
        List<Artist> artistList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Artist.class));
        return artistList;
    }


    public Artist findByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM \"Artists\" WHERE \"Employee_id\" = ?";
        try {
            List<Artist> artists = jdbcTemplate.query(sql, new Object[]{employeeId}, BeanPropertyRowMapper.newInstance(Artist.class));
            if (!artists.isEmpty()) {
                return artists.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error finding artist by Employee_id: {}", e.getMessage());
            return null;
        }
    }


    public void deleteByEmployeeId(int employeeId) {
        String sql = "DELETE FROM \"Artists\" WHERE \"Employee_id\" = ?";
        try {
            jdbcTemplate.update(sql, employeeId);
            log.info("Artist with employeeId [{}] deleted", employeeId);
        } catch (Exception e) {
            log.error("Error deleting artist with employeeId [{}]: {}", employeeId, e.getMessage());
            throw new RuntimeException("Error deleting artist", e);
        }
    }

    public void delete(int employeeId) {
        String sql = "DELETE FROM \"Artists\" WHERE \"Employee_id\" = ?";
        try {
            jdbcTemplate.update(sql, employeeId);
            log.info("Artist with employeeId [{}] deleted", employeeId);
        } catch (Exception e) {
            log.error("Error deleting artist with employeeId [{}]: {}", employeeId, e.getMessage());
            throw new RuntimeException("Error deleting artist", e);
        }
    }


}
