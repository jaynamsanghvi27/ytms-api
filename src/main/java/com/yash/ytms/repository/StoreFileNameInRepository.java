package com.yash.ytms.repository;

import com.yash.ytms.domain.FileName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreFileNameInRepository  extends JpaRepository<FileName, Long> {
    @Query(nativeQuery = true, value = "select file_name from file_name")
    List<String> findAllFileName();
}
