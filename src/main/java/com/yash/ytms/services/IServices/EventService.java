package com.yash.ytms.services.IServices;

import com.yash.ytms.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {
    ResponseEntity<List<Event>> getAllEvents();

    ResponseEntity<Event> getEventById(Integer eventId);

    ResponseEntity<Event> createEvent(Event event);

    ResponseEntity<Void> deleteEvent(Integer eventId);

    ResponseEntity<Event> updateEvent(Integer eventId, Event updatedEvent);
}
