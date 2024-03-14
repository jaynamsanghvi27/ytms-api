/**
 * 
 */
package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.GradeMaster;

/**
 * 
 */
@Repository
public interface GradeMasterRepository extends JpaRepository<GradeMaster, Long>{

}
