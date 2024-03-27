package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.util.List;

import com.yash.ytms.dto.CalendarDto;
import com.yash.ytms.dto.ScheduleDeleteDto;

public interface IScheduleDeleteService {
ScheduleDeleteDto save(CalendarDto calendarDto);
ScheduleDeleteDto approve(Long id,Principal principal);
ScheduleDeleteDto deny(Long id,Principal principal);
List<ScheduleDeleteDto> getToApprove();
}
