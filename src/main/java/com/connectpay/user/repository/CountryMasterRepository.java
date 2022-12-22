package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.CountryMaster;

public interface CountryMasterRepository extends JpaRepository<CountryMaster, String>{

	CountryMaster findById(int id);
	
	@Query("SELECT lkd FROM CountryMaster lkd WHERE (name = :name)")
	List<CountryMaster> findbyName(@Param("name") String name);
	
}
