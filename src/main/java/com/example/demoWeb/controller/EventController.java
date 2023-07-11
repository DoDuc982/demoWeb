package com.example.demoWeb.controller;

import com.example.demoWeb.dto.EventDto;
import com.example.demoWeb.model.Event;
import com.example.demoWeb.service.EventService;
import com.example.demoWeb.service.implement.EventServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.PublicKey;
import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }
    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "event-create";
    }
    @GetMapping("/events")
    public String eventList(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }
    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-details";
    }
    @GetMapping("events/{eventId}/edit")
    public String ediEventForm(@PathVariable("eventId") Long eventId, Model model){
        EventDto event = eventService.findByEventId(eventId);
        model.addAttribute("event", event);
        return "events-edit";
    }
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
    @PostMapping("/events/{clubId}")
    public String createEvents(
            @PathVariable("clubId") Long clubId,
            @ModelAttribute("event") EventDto eventDto,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "clubs-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }
    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(
            @PathVariable("eventId") Long eventId,
            @Valid @ModelAttribute EventDto event,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){
            model.addAttribute("event", event);
            return "events-edit";
        }
        EventDto eventDto = eventService.findByEventId(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

}
