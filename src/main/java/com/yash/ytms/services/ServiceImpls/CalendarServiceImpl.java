package com.yash.ytms.services.ServiceImpls;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.constants.RequestStatusTypes;
import com.yash.ytms.domain.Calendar;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.dto.CalendarDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.YtmsUserDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.CalendarRepository;
import com.yash.ytms.services.IServices.ICalendarService;
import com.yash.ytms.services.IServices.IYtmsUserService;
@Service
public class CalendarServiceImpl implements ICalendarService {

	@Autowired
	CalendarRepository calendarRepository;
	@Autowired
	private IYtmsUserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CalendarDto> createCalendarEvents(List<CalendarDto> calendarDto, Principal principal) {
		// TODO Auto-generated method stub
		final String userName = principal.getName();
		YtmsUserDto userDto = this.userService.getUserByEmailAdd(userName);
		if (ObjectUtils.isNotEmpty(calendarDto) && ObjectUtils.isNotEmpty(userDto)) {
			YtmsUser user = this.modelMapper.map(userDto, YtmsUser.class);
			List<Calendar> calendarEvents = calendarDto.stream().map(cd -> this.modelMapper.map(cd, Calendar.class))
					.toList();
			// this.modelMapper.map(calendarDto, new TypeToken<List<Calendar>>() {}.getType());
			calendarEvents.stream().map(event -> {
				event.setStart();
				event.setEnd();
				event.setScheduleUser(user);
				return event;
			}).collect(Collectors.toList());

			calendarEvents = calendarRepository.saveAllAndFlush(calendarEvents);

			List<CalendarDto> calendar = calendarEvents.stream().map(ce -> this.modelMapper.map(ce, CalendarDto.class))
					.toList();
			// this.modelMapper.map(calendarEvents, new TypeToken<List<CalendarDto>>(){}.getType());
			return calendar;
		} else {
			throw new ApplicationException("Event details is invalid, please try again !");

		}
	}

	@Override
	public CalendarDto updateCalendarEvent(CalendarDto calendarDto, Principal principal) {
		// TODO Auto-generated method stub
		final String userName = principal.getName();
		YtmsUserDto userDto = this.userService.getUserByEmailAdd(userName);
		if (ObjectUtils.isNotEmpty(calendarDto) && ObjectUtils.isNotEmpty(userDto)) {
			YtmsUser user = this.modelMapper.map(userDto, YtmsUser.class);
			Calendar calendarEvent = this.modelMapper.map(calendarDto, Calendar.class);
			calendarEvent.setScheduleUser(user);
			calendarEvent.setStart();
			calendarEvent.setEnd();

			calendarEvent = calendarRepository.saveAndFlush(calendarEvent);
			CalendarDto calendar = this.modelMapper.map(calendarEvent, CalendarDto.class);

			return calendar;
		} else {
			throw new ApplicationException("Event details is invalid, please try again !");
		}
	}

	@Override
	public List<CalendarDto> getAllCalendarEvents() {
		// TODO Auto-generated method stub
		List<Calendar> calendarEvents = calendarRepository.findAll();
		if (!calendarEvents.isEmpty()) {
			return calendarEvents.stream().map(ce -> this.modelMapper.map(ce, CalendarDto.class)).toList();
		} else
			throw new ApplicationException("No Events found !");
	}

	@Override
	public List<CalendarDto> searchByTrainer(String trainerEmail) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(trainerEmail)) {
			ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
			List<Calendar> calendar = this.calendarRepository.findAllEventsByTrainerEmail(trainerEmail);
			List<CalendarDto> calendarDto = calendar.stream().map(e -> this.modelMapper.map(e, CalendarDto.class)).toList();
					
			if (calendar.isEmpty()) {
				responseWrapperDto.setStatus(RequestStatusTypes.FAILED.toString());
				responseWrapperDto.setMessage("No Events Found for this trainer");
				responseWrapperDto.setData("Nil");
			} else {
				List<CalendarDto> calendarDtos = calendar.stream().map(e -> this.modelMapper.map(e, CalendarDto.class))
						.toList();
				responseWrapperDto.setStatus(RequestStatusTypes.SUCCESS.toString());
				responseWrapperDto.setMessage("Events found for trainer");
				responseWrapperDto.setData(calendarDtos);
			}
			return calendarDto;
		} else
			throw new ApplicationException("Trainer email is empty or null, please check & try again.");
	}

	@Override
	public CalendarDto getCalendarEventById(Long eventId) {
		// TODO Auto-generated method stub
		if (ObjectUtils.isNotEmpty(eventId)) {
			Optional<Calendar> optionalEvent = calendarRepository.findById(eventId);
			if (optionalEvent.isPresent()) {
				Calendar calendarEvent = optionalEvent.get();
				return this.modelMapper.map(calendarEvent, CalendarDto.class);
			} else
				throw new ApplicationException("Event not found !");
		} else
			throw new ApplicationException("Event id is null or empty, please check & try again !");
	}

	@Override
	public void deleteById(Long event_id) {
		// TODO Auto-generated method stub
		calendarRepository.deleteById(event_id);
		
	}
}
