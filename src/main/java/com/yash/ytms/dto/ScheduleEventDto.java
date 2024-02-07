package com.yash.ytms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEventDto {

    private Integer eventId;

    private String title;

    private LocalDateTime startTS;

    private LocalDateTime endTS;

    private String color;

    private YtmsUserDto scheduleUser;
}
