/**
 * 
 */
package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.TechnologyMaster;

/**
 * 
 */
@Repository
public interface TechnologyMasterRepository extends JpaRepository<TechnologyMaster, Long> {

}
