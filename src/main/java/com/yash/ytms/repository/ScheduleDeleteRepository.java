package com.yash.ytms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.ScheduleDelete;
@Repository
public interface ScheduleDeleteRepository extends JpaRepository<ScheduleDelete, Long> {
List<ScheduleDelete> findByStatus(Integer Status);
}
