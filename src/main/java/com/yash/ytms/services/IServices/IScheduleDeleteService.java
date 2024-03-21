package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.util.List;

import com.yash.ytms.dto.CalendarDto;
import com.yash.ytms.dto.ScheduleDeleteDto;

public interface IScheduleDeleteService {
ScheduleDeleteDto save(CalendarDto calendarDto);
ScheduleDeleteDto approve(ScheduleDeleteDto scheduleDeleteDto,Principal principal);
ScheduleDeleteDto deny(ScheduleDeleteDto scheduleDeleteDto,Principal principal);
List<ScheduleDeleteDto> getToApprove();
}
