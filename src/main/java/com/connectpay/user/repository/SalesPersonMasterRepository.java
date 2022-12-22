package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.SalesPersonMaster;

public interface SalesPersonMasterRepository extends JpaRepository<SalesPersonMaster, String>{

	SalesPersonMaster findById(int id);
	
	SalesPersonMaster findByMobile(String mobile);

	@Query("SELECT lkd FROM SalesPersonMaster lkd WHERE (name = :name)")
	List<SalesPersonMaster> findbyName(@Param("name") String name);
	
	@Query("SELECT lkd FROM SalesPersonMaster lkd WHERE (regionid = :regid)")
	List<SalesPersonMaster> findbyregion(@Param("regid") int regid);

	@Query("SELECT lkd FROM SalesPersonMaster lkd WHERE (assignedstateid = :stateid)")
	List<SalesPersonMaster> findbystate(@Param("stateid") int stateid);
	
	@Query("SELECT lkd.name FROM SalesPersonMaster lkd")
	List<Object> findAllByName();

}
