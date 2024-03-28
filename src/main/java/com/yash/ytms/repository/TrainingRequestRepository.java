/**
 * 
 */
package com.yash.ytms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.TrainingRequestForm;

/**
 * 
 */
@Repository
public interface TrainingRequestRepository extends JpaRepository<TrainingRequestForm, Long> {
	
	public List<TrainingRequestForm> findByUserName(String userName);

//    @Query(value = "select trf from TrainingRequestForm trf where trf.trainer=:trainerName", nativeQuery = true)
    List<TrainingRequestForm> findByTrainer(String trainerName);

    @Query(value = "select full_name from ytms_user where email_add =:userName", nativeQuery = true)
    String findTrainerName(String userName);
}
