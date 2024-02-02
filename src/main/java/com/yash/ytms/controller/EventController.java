package com.yash.ytms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.ytms.domain.Event;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.dto.EventDto;
import com.yash.ytms.dto.YtmsUserDto;
import com.yash.ytms.services.IServices.EventService;
import com.yash.ytms.services.IServices.IYtmsUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private final EventService eventService;
    @Autowired
    ObjectMapper modelMapper;
    @Autowired
    private IYtmsUserService userService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
String email = eventDto.getTrainerEmail();
Event event = eventDto.getEvent();
        System.out.println("I am in create use backend "+eventDto.toString());

        YtmsUserDto ytmsUserDto= this.userService.getUserByEmailAdd(email);
        if(ytmsUserDto !=null){
            YtmsUser ytmsUser= modelMapper.convertValue(ytmsUserDto,YtmsUser.class);
            event.setYtmsUser(ytmsUser);
        }
        return eventService.createEvent(event);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer eventId) {
        return eventService.deleteEvent(eventId);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer eventId, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(eventId, updatedEvent);
    }
    @GetMapping("/trainers")
    public List<YtmsUser> getAllTrainers() {
        List<YtmsUser> trainers = eventService.getAllTrainers();
        return  trainers;
    }

}