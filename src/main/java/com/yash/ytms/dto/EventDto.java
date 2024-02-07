package com.yash.ytms.dto;

import com.yash.ytms.domain.Event;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private String title;
    private String start;
    private  String end;
    private  String color;
    private String trainerEmail;

}
