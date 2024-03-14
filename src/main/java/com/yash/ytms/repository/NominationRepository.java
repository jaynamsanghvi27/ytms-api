package com.yash.ytms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ytms.domain.Nomination;

public interface NominationRepository extends JpaRepository<Nomination, Long>{
	List<Nomination> findAllByTrainingId(Long training_id);
}
