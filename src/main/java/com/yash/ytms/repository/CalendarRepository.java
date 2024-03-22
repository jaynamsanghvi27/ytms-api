package com.yash.ytms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yash.ytms.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
	
	@Query("select e from Calendar e where e.scheduleUser.emailAdd=:trainerEmail")
	List<Calendar> findAllEventsByTrainerEmail(@Param("trainerEmail") String trainerEmail);

}
