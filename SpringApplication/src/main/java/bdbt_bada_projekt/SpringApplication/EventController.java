package bdbt_bada_projekt.SpringApplication;

import bdbt_bada_projekt.SpringApplication.dao.EventDAO;
import bdbt_bada_projekt.SpringApplication.entity.Event;
import bdbt_bada_projekt.SpringApplication.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@Controller
public class EventController {
    @Autowired
    private EventDAO dao;
    @Autowired
    private EventRepository eventRepository;
    @RequestMapping("/events")
    public String showEvents(Model model) {
        log.info("Searching all events");
        List<Event> events = dao.list();
        model.addAttribute("events", events);
        return "events"; // Thymeleaf template name
    }
    @RequestMapping("/index")
    public String viewHomePage(Model model) {
        /* Import java.util.List */
        List<Event> eventList = dao.list();
        model.addAttribute("eventList", eventList);
        return "/index";
    }
}
