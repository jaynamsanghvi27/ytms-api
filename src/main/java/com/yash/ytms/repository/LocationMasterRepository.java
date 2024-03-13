/**
 * 
 */
package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ytms.domain.LocationMaster;

/**
 * 
 */
@Repository
public interface LocationMasterRepository extends JpaRepository<LocationMaster, Long>{

}
