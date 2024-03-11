/**
 * 
 */
package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.UnitMaster;

/**
 * 
 */
@Repository
public interface UnitMasterRepository extends JpaRepository<UnitMaster, Long>{

}
