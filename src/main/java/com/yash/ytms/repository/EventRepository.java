package com.yash.ytms.repository;

import com.yash.ytms.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select e from Event e where e.ytmsUser.emailAdd=:trainer")
    List<Event> findByTrainer(@Param("trainer") String trainer);
}
