/**
 * 
 */
package com.yash.ytms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.TrainingRequestForm;

/**
 * 
 */
@Repository
public interface TrainingRequestRepository extends JpaRepository<TrainingRequestForm, Long> {
	
	public List<TrainingRequestForm> findByUserName(String userName);

}
