/**
 * 
 */
package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.TrainingTypesMaster;

/**
 * 
 */
@Repository
public interface TrainingTypesMasterRepository extends JpaRepository<TrainingTypesMaster, Long>{

}
