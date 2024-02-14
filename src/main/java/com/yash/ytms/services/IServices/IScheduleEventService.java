package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.ScheduleEventDto;


public interface IScheduleEventService {

    ScheduleEventDto createScheduleEvent(ScheduleEventDto scheduleEventDto, Principal principal);

    ResponseWrapperDto searchByTrainer(String trainerEmail);

    ScheduleEventDto getScheduleEventById(Integer eventId);

    List<ScheduleEventDto> getAllScheduleEvents();
    List<ScheduleEventDto> getAllEventsForUser(String username, LocalDate datetime);

    ResponseWrapperDto deleteScheduleEventById(Integer eventId, Principal principal);

    ResponseWrapperDto updateScheduleEvent(Integer eventId, ScheduleEventDto scheduleEventDto, Principal principal);
}
