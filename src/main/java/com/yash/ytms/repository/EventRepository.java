package com.yash.ytms.repository;

import com.yash.ytms.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query("SELECT e FROM Event e JOIN e.ytmsUser u WHERE u.emailAdd = : trainer")
    List<Event> findByTrainer(String trainer);
}
