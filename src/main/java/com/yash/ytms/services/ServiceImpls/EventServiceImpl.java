package com.yash.ytms.services.ServiceImpls;
import com.yash.ytms.constants.RequestStatusTypes;
import com.yash.ytms.constants.UserRoleTypes;
import com.yash.ytms.domain.Event;
import com.yash.ytms.domain.UserRole;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.repository.EventRepository;
import com.yash.ytms.repository.YtmsUserRepository;
import com.yash.ytms.services.IServices.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private YtmsUserRepository userRepo;

    @Override
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Event> getEventById(Integer eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Event> createEvent(Event event) {
        Event createdEvent = eventRepository.save(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteEvent(Integer eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Event> updateEvent(Integer eventId, Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setStart(updatedEvent.getStart());
            existingEvent.setEnd(updatedEvent.getEnd());
            existingEvent.setColor(updatedEvent.getColor()); // assuming color is a field in the Event entity
            Event savedEvent = eventRepository.save(existingEvent);
            return new ResponseEntity<>(savedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseWrapperDto searchByTrainer(String trainer) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        List<Event> events = eventRepository.findByTrainer(trainer);
        if (events.isEmpty()) {
            responseWrapperDto.setStatus(RequestStatusTypes.FAILED.toString());
            responseWrapperDto.setMessage("No Events Found for this trainer");
            responseWrapperDto.setData("Nil");
        } else {
            responseWrapperDto.setStatus(RequestStatusTypes.SUCCESS.toString());
            responseWrapperDto.setMessage("Events found for trainer");
            responseWrapperDto.setData(events);
        }
        return responseWrapperDto;
    }

    @Override
    public List<YtmsUser> getAllTrainers() {
        return userRepo.findAllTrainers("ROLE_TRAINER");
    }
}