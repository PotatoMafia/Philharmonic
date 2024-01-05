package bdbt_bada_projekt.SpringApplication;

import bdbt_bada_projekt.SpringApplication.entity.Event;
import bdbt_bada_projekt.SpringApplication.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        List<Event> events = eventRepository.findAll();

        // Передаем список событий в модель
        model.addAttribute("events", events);

        // Возвращаем имя представления, которое отобразит данные на странице main
        return "main";
    }
}
