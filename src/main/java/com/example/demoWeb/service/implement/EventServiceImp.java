package com.example.demoWeb.service.implement;

import com.example.demoWeb.dto.EventDto;
import com.example.demoWeb.mapper.EventMapper;
import com.example.demoWeb.model.Club;
import com.example.demoWeb.model.Event;
import com.example.demoWeb.repository.ClubRepository;
import com.example.demoWeb.repository.EventRepository;
import com.example.demoWeb.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demoWeb.mapper.EventMapper.mapToEvent;
import static com.example.demoWeb.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;
    @Autowired
    public EventServiceImp(EventRepository eventRepository, ClubRepository clubRepository){
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }
    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return  events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
