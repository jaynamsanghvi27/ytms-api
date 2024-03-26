package com.yash.ytms.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "calendar")
public class Calendar {
	private static final ZoneId zoneId = ZoneId.of("Asia/Kolkata");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String title;
    @Column
	private LocalDateTime start;
    @Column
	private LocalDateTime end;

	@ManyToOne
	@JoinColumn(name = "schedule_user")
	private YtmsUser scheduleUser;

	@Column
	private Long number_of_week_days;
	
	@Transient
	private LocalDate start_date;

	@Transient
	private LocalDate end_date;

	@Transient
	private LocalTime start_time;

	@Transient
	private LocalTime end_time;

	
	public void setStart() {
		this.start = start_date.atTime(start_time);
	}

	public void setEnd() {
		this.end =end_date.atTime(end_time);
	}

}
