package com.yash.ytms.services.IServices;

import com.yash.ytms.domain.Event;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.dto.ResponseWrapperDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EventService {
    ResponseEntity<List<Event>> getAllEvents();

    ResponseEntity<Event> getEventById(Integer eventId);

    ResponseEntity<Event> createEvent(Event event);

    ResponseEntity<Void> deleteEvent(Integer eventId);

    ResponseEntity<Event> updateEvent(Integer eventId, Event updatedEvent);
    ResponseWrapperDto searchByTrainer(String trainer);
    List<YtmsUser> getAllTrainers();
}
