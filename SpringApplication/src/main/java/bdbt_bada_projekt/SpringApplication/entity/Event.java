package bdbt_bada_projekt.SpringApplication.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "\"Events\"")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Event_id\"")
    private int eventId;
    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"Date\"")
    private String date;
    @Column(name = "\"Time\"")
    private String time;

    @Column(name = "\"Description\"")
    private String description;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Column(name = "\"Artist_id\"")
    private String artistId;

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public int getPhilharmonicId() {
        return philharmonicId;
    }

    public void setPhilharmonicId(int philharmonicId) {
        this.philharmonicId = philharmonicId;
    }

    @Column(name = "\"Venue_id\"")
    private int venueId;


    @Column(name = "\"Philharmonic_id\"")
    private int philharmonicId = 1;

    public Event() {

    }

    public Event(int eventId, String name, String date, String time, String description) {
        super();
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}
