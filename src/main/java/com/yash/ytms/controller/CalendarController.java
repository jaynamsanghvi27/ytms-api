package com.yash.ytms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.dto.CalendarDto;
import com.yash.ytms.dto.ScheduleDeleteDto;
import com.yash.ytms.services.IServices.ICalendarService;
import com.yash.ytms.services.IServices.IScheduleDeleteService;


@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	ICalendarService iCalendarService;
    @Autowired
    IScheduleDeleteService scheduleDeleteService;
    
	@GetMapping("/get/all")
	public ResponseEntity<List<CalendarDto>> getAllEvents() {
		return new ResponseEntity<>(iCalendarService.getAllCalendarEvents(), HttpStatus.OK);
	}

	@GetMapping("/get/{eventId}")
	public ResponseEntity<CalendarDto> getScheduleEventById(@PathVariable Long eventId) {
		return new ResponseEntity<>(iCalendarService.getCalendarEventById(eventId), HttpStatus.OK);
	}

	@GetMapping("/{trainerEmail}")
	public ResponseEntity<List<CalendarDto>> searchByTrainerEmail(@PathVariable String trainerEmail) {
		return new ResponseEntity<>(iCalendarService.searchByTrainer(trainerEmail), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<CalendarDto> saveEvent(@RequestBody CalendarDto calendarDto, Principal principal) {
		return new ResponseEntity<>(iCalendarService.updateCalendarEvent(calendarDto, principal), HttpStatus.OK);
	}
//	public ResponseEntity<List<CalendarDto>> saveEvents(@RequestBody List<CalendarDto> calendarDtos,
//			Principal principal) {
//		return new ResponseEntity<>(iCalendarService.createCalendarEvents(calendarDtos, principal), HttpStatus.OK);
//	}

	@PostMapping("/update")
	public ResponseEntity<CalendarDto> updateEvent(@RequestBody CalendarDto calendarDto, Principal principal) {
		return new ResponseEntity<>(iCalendarService.updateCalendarEvent(calendarDto, principal), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<ScheduleDeleteDto> deleteEvent(@RequestBody CalendarDto calendarDto) {
 		
		return new ResponseEntity<>(scheduleDeleteService.save(calendarDto), HttpStatus.OK);
	}
		
}
