package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.RegionMaster;

public interface RegionMasterRepository extends JpaRepository<RegionMaster, String>{

	RegionMaster findById(int id);
	
	@Query("SELECT lkd FROM RegionMaster lkd WHERE (name = :name)")
	List<RegionMaster> findbyName(@Param("name") String name);
	
}
