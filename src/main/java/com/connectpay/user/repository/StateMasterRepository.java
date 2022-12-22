package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.StateMaster;

public interface StateMasterRepository extends JpaRepository<StateMaster, String>{

	StateMaster findById(int id);
	
	@Query("SELECT lkd FROM StateMaster lkd WHERE (countryid = :countryid)")
	List<StateMaster> findbyCountry(@Param("countryid") int countryid);
	
	@Query("SELECT lkd FROM StateMaster lkd WHERE (name = :name and countryid = :countryid)")
	List<StateMaster> findbyName(@Param("name") String name,@Param("countryid") int countryid);
	
	@Query("SELECT lkd FROM StateMaster lkd WHERE countryid = :countryID and regionid = :regionID")
	List<StateMaster> findByRegionID(@Param("countryID") int countryid,@Param("regionID") int regionID);

}
