package bdbt_bada_projekt.SpringApplication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Events\"")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    private String name;
    private Date date;
    private String time;

    public Event(){

    }
    public Event(int eventId, String name, Date date, String time) {
        super();
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    // Геттеры и сеттеры

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
