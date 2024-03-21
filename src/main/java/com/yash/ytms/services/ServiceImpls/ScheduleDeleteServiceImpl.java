package com.yash.ytms.services.ServiceImpls;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.domain.Calendar;
import com.yash.ytms.domain.ScheduleDelete;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.dto.CalendarDto;
import com.yash.ytms.dto.ScheduleDeleteDto;
import com.yash.ytms.dto.YtmsUserDto;
import com.yash.ytms.repository.ScheduleDeleteRepository;
import com.yash.ytms.services.IServices.ICalendarService;
import com.yash.ytms.services.IServices.IScheduleDeleteService;
import com.yash.ytms.services.IServices.IYtmsUserService;

@Service
public class ScheduleDeleteServiceImpl implements IScheduleDeleteService {

	@Autowired
	private ICalendarService calendarService;
	@Autowired
	private ScheduleDeleteRepository deleteRepository;
	@Autowired
	private IYtmsUserService userService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ScheduleDeleteDto save(CalendarDto calendarDto) {
		// TODO Auto-generated method stub
		ScheduleDeleteDto scheduleDeleteDto = new ScheduleDeleteDto();
		Calendar calendar = this.modelMapper.map(calendarDto, Calendar.class);
		scheduleDeleteDto.setCalendar(calendar);
		ScheduleDelete save = this.modelMapper.map(scheduleDeleteDto, ScheduleDelete.class);
		save.setStatus(0);
		return this.modelMapper.map(deleteRepository.saveAndFlush(save), ScheduleDeleteDto.class);

	}

	@Override
	public ScheduleDeleteDto approve(ScheduleDeleteDto scheduleDeleteDto,Principal principal) {
		// TODO Auto-generated method stub
		final String userName = principal.getName();
		YtmsUserDto userDto = this.userService.getUserByEmailAdd(userName);
		YtmsUser user = this.modelMapper.map(userDto, YtmsUser.class);
		ScheduleDelete save = this.modelMapper.map(scheduleDeleteDto, ScheduleDelete.class);
		save.setStatus(1);
		save.setScheduleUser(user);
		calendarService.deleteById(save.getCalendar().getId());
		return this.modelMapper.map(deleteRepository.saveAndFlush(save), ScheduleDeleteDto.class);
	}

	@Override
	public ScheduleDeleteDto deny(ScheduleDeleteDto scheduleDeleteDto,Principal principal) {
		// TODO Auto-generated method stub
		final String userName = principal.getName();
		YtmsUserDto userDto = this.userService.getUserByEmailAdd(userName);
		YtmsUser user = this.modelMapper.map(userDto, YtmsUser.class);
		ScheduleDelete save = this.modelMapper.map(scheduleDeleteDto, ScheduleDelete.class);
		save.setScheduleUser(user);
		save.setStatus(2);
		return this.modelMapper.map(deleteRepository.saveAndFlush(save), ScheduleDeleteDto.class);
	}

	@Override
	public List<ScheduleDeleteDto> getToApprove() {
		// TODO Auto-generated method stub
		
	 List<ScheduleDelete> toDelete = deleteRepository.findByStatus(0);
		return toDelete.stream().map(ce -> this.modelMapper.map(ce, ScheduleDeleteDto.class)).toList();
	 	
	}

}
