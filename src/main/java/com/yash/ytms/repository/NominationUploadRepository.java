package com.yash.ytms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ytms.domain.Event;
import com.yash.ytms.domain.Nomination;
import com.yash.ytms.dto.NominationData;

public interface NominationUploadRepository extends JpaRepository<Nomination,Integer>  {

}
