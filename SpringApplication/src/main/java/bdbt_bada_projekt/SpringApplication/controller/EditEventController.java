package bdbt_bada_projekt.SpringApplication.controller;

import bdbt_bada_projekt.SpringApplication.entity.Artist;
import bdbt_bada_projekt.SpringApplication.entity.Employee;
import bdbt_bada_projekt.SpringApplication.entity.Event;
import bdbt_bada_projekt.SpringApplication.service.ArtistService;
import bdbt_bada_projekt.SpringApplication.service.EmployeeService;
import bdbt_bada_projekt.SpringApplication.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditEventController {

    private final EventService eventService;
    private transient Event newEvent;
    private final EmployeeService empoloyeeService;

    @GetMapping("/edit_event")
    public String showEditEventPage(Model model) {
        List<Event> events = eventService.getAllEvents();
        List<Employee> artists = empoloyeeService.getAllEmployeesWithArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("events", events);
        return "admin/edit_event";
    }

    @PostMapping("/updateEventName")
    public String updateEventName(@RequestParam int eventId, @RequestParam String newName) {
        log.info("Updating event information: EventId={}, Attribute={}, NewValue={}", eventId, "name", newName);
        // Call the service method to perform the update
        Event event = eventService.findById(eventId);
        event.setName(newName);
        eventService.updateEvent(event);
        return "redirect:/edit_event";
    }

    @PostMapping("/createEvent")
    public String createEvent(@RequestParam("name") String name,
                              @RequestParam("date") String date,
                              @RequestParam("time") String time,
                              @RequestParam("description") String description,
                              @RequestParam("venue") String venue,
                              @RequestParam(name = "artistId", required = false) String id,
                              Model model, RedirectAttributes redirectAttributes) {
        try {
            String dateString = date.toString();
            String timeString = time.toString();
            int venueId = Integer.parseInt(venue);

            newEvent = new Event();
            newEvent.setName(name);
            log.info("String DATE: [{}]", dateString);
            log.info("String TIME: [{}]", timeString);
            newEvent.setDate(dateString);
            newEvent.setTime(timeString);
            newEvent.setDescription(description);
            newEvent.setVenueId(venueId);
            if (id != null) {
                newEvent.setArtistId(id);
            }else{
                newEvent.setArtistId("-");
            }
            log.info("Check if EXIST: [{}]", eventService.isEventNameAlreadyInUse(name));
            if (eventService.isEventNameAlreadyInUse(name)) {
                redirectAttributes.addFlashAttribute("eventNameError", "Event with this name already exists");
                redirectAttributes.addFlashAttribute("newEvent", newEvent);
            } else {
                log.info("Go to EVENT SERVICE: [{}]", newEvent);
                eventService.saveEvent(newEvent);
            }
        } catch (Exception e) {
            log.error("Error creating event: {}", e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/edit_event";
    }


    @PostMapping("/updateEventArtist")
    public String updateEventArtist(@RequestParam("eventId") int eventId,
                                    @RequestParam("newArtistId") String newArtistId) {
        eventService.updateEventArtist(eventId, newArtistId);
        return "redirect:/edit_event";
    }


    @PostMapping("/updateEventDescription")
    public String updateEventDescription(@RequestParam int eventId, @RequestParam String newDescription) {
        log.info("Updating event information: EventId={}, Attribute={}, NewValue={}", eventId, "description", newDescription);
        // Call the service method to perform the update
        Event event = eventService.findById(eventId);
        event.setDescription(newDescription);
        eventService.updateEvent(event);
        return "redirect:/edit_event";
    }

    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam int eventId) {
        eventService.deleteEventById(eventId);
        return "redirect:/edit_event";
    }

}
