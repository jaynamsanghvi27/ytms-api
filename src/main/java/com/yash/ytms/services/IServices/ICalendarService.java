package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.util.List;

import com.yash.ytms.dto.CalendarDto;
import com.yash.ytms.dto.ResponseWrapperDto;

public interface ICalendarService {

List<CalendarDto> createCalendarEvents(List<CalendarDto> calendarDto,Principal principal);
CalendarDto updateCalendarEvent(CalendarDto calendarDto,Principal principal);
List<CalendarDto> getAllCalendarEvents();
List<CalendarDto> searchByTrainer(String trainerEmail);
CalendarDto getCalendarEventById(Long event_id);
void deleteById(Long event_id);

}
