package bdbt_bada_projekt.SpringApplication.service;

import bdbt_bada_projekt.SpringApplication.dao.EventDAO;
import bdbt_bada_projekt.SpringApplication.entity.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventDAO eventDAO;

    @Transactional
    public void saveEvent(Event event) {
        log.info("Service save event [{}] STATUS:SUCCESS", event.getEventId());
        eventDAO.save(event);
    }

    public Event findById(int eventId) {
        Event event = eventDAO.findById(eventId);

        if (event == null) {
            throw new EntityNotFoundException("Event not found with id: " + eventId);
        }

        return event;
    }

    public List<Event> getAllEvents() {
        log.info("Getting events");
        return eventDAO.list();
    }

    public void deleteEventById(int eventId) {
        eventDAO.delete(eventId);
    }

    public void updateEvent(Event event) {
        eventDAO.update(event);
    }

    public boolean isEventNameAlreadyInUse(String name) {
        log.info("Name of the EVENT: [{}]", name);
        return  eventDAO.isExistName(name);
    }

    public void updateEventArtist(int eventId, String newArtistId) {
        Event event = eventDAO.findById(eventId);
        if (event != null) {
            event.setArtistId(newArtistId);
            eventDAO.update(event);
        }
    }

}
