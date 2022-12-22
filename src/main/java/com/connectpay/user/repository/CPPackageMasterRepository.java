package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.CPPackageMaster;

public interface CPPackageMasterRepository extends JpaRepository<CPPackageMaster, String>{

	CPPackageMaster findById(int id);
	
	@Query("SELECT lkd FROM CPPackageMaster lkd WHERE (name = :name)")
	List<CPPackageMaster> findbyName(@Param("name") String name);
	
}
