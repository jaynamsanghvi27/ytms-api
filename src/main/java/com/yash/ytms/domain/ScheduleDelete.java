package com.yash.ytms.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "scheduledelete")

public class ScheduleDelete {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "schedule")
    private Calendar calendar;
	@ManyToOne
	@JoinColumn(name = "schedule_user")
	private YtmsUser scheduleUser;
	@Column
	private Integer status;

}
