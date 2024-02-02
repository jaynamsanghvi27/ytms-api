package com.yash.ytms.domain;

import com.yash.ytms.dto.YtmsUserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String title;
    private LocalDateTime start;
    private  LocalDateTime end;
    private  String color;
    @ManyToOne
    @JoinColumn(name = "emailAdd")
    private YtmsUser ytmsUser;

}