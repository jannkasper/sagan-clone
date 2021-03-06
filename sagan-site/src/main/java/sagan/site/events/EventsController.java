package sagan.site.events;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EventsController {

    private static final int NEXT_12_MONTHS = 365;

    private final EventsCalendarService calendarService;

    public EventsController(EventsCalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/community")
    public String community(Model model) {
        List<Event> events = calendarService.findEvents(Period.of(LocalDate.now().toString(), NEXT_12_MONTHS));
        int count = Math.min(events.size(), 6);
        model.addAttribute("events", events.subList(0, count));
        return "events/community";
    }

    @GetMapping("/events")
    public String events(Model model) {
        List<Event> events = calendarService.findEvents(Period.of(LocalDate.now().toString(), NEXT_12_MONTHS));
        model.addAttribute("events", events);
        return "events/list";
    }
}
