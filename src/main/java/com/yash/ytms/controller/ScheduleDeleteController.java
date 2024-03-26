package com.yash.ytms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.dto.ScheduleDeleteDto;
import com.yash.ytms.services.IServices.IScheduleDeleteService;


@RestController
@RequestMapping("/delete")
public class ScheduleDeleteController {
@Autowired
IScheduleDeleteService scheduleDeleteService;

@GetMapping("/get")
List<ScheduleDeleteDto> getDelete()
{
return scheduleDeleteService.getToApprove();	
}

@PostMapping("/approve")
ScheduleDeleteDto approve(@RequestBody Long id ,Principal principal)
{
	return scheduleDeleteService.approve(id, principal);
}

@PostMapping("/deny")
ScheduleDeleteDto deny(@RequestBody Long id ,Principal principal)
{
	return scheduleDeleteService.deny(id, principal);
}
	
}
