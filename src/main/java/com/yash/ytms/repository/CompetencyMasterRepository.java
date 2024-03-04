/**
 * 
 */
package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.CompetencyMaster;

/**
 * 
 */
@Repository
public interface CompetencyMasterRepository extends JpaRepository<CompetencyMaster, Long> {

}
