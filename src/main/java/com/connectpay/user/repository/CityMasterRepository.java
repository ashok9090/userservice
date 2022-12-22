package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.CityMaster;

public interface CityMasterRepository extends JpaRepository<CityMaster, String>{

	CityMaster findById(int id);
	
	@Query("SELECT lkd FROM CityMaster lkd WHERE (stateid = :stateid)")
	List<CityMaster> findbyState(@Param("stateid") int stateid);
	
	@Query("SELECT lkd FROM CityMaster lkd WHERE (name = :name and stateid= :stateid)")
	List<CityMaster> findbyName(@Param("name") String name,@Param("stateid") int stateid);
}
