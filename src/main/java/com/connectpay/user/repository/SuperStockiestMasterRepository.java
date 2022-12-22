


package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.SuperStockiestMaster;

public interface SuperStockiestMasterRepository extends JpaRepository<SuperStockiestMaster, String>{

	SuperStockiestMaster findById(int id);
	
	SuperStockiestMaster findByTerminalid(String tid);

	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (mobile = :mobile and id <>:ssid)")
	SuperStockiestMaster findByMobile(@Param("mobile") String mobile,@Param("ssid") int ssid);
	
	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (name = :name)")
	List<SuperStockiestMaster> findbyName(@Param("name") String name);
	
	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (regionid = :regid and approved='Y')")
	List<SuperStockiestMaster> findbyregion(@Param("regid") int regid);
	
	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (stateid = :stateid and approved='Y')")
	List<SuperStockiestMaster> findbystate(@Param("stateid") int stateid);

	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (salespersonid = :spid)")
	List<SuperStockiestMaster> findbySP(@Param("spid") int spid);
	
	@Query("SELECT COALESCE(MAX(CAST(SUBSTRING(terminalid, 6,3) AS int)),0) as tid  FROM SuperStockiestMaster lkd WHERE (SUBSTRING(terminalid, 1,5) =:prefix)")
	String getLastTerminalID(@Param("prefix") String prefix);

	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (mobile = :mobile and salespersonid=:spid)")
	SuperStockiestMaster findByMobileSpid(@Param("mobile") String mobile,@Param("spid") int spid);
	
	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (terminalid = :tid and salespersonid =:spid)")
	SuperStockiestMaster findByTerminalIDSpid(@Param("tid") String tid,@Param("spid") int spid);

	/*
	 * @Query("SELECT lkd.wBalance FROM SuperStockiestMaster lkd WHERE id = :id")
	 * double findWBalanceById(@Param("id") int id);
	 * 
	 * @Query("SELECT lkd.aBalance FROM SuperStockiestMaster lkd WHERE id = :id")
	 * double findaBalanceById(@Param("id") int id);
	 */
	
	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (mobile = :mobile and stateid=:stateid)")
	SuperStockiestMaster findByMobileStateid(@Param("mobile") String mobile,@Param("stateid") int stateid);
	
	@Query("SELECT lkd FROM SuperStockiestMaster lkd WHERE (terminalid = :tid and stateid =:stateid)")
	SuperStockiestMaster findByTerminalIDStateid(@Param("tid") String tid,@Param("stateid") int stateid);
	
	/*
	 * @Query("SELECT lkd.wBalance FROM SuperStockiestMaster lkd WHERE id = :id ")
	 * double findWbalance(@Param("id") int id);
	 * 
	 * @Query("SELECT lkd.aBalance FROM SuperStockiestMaster lkd WHERE id = :id ")
	 * double findAbalance(@Param("id") int id);
	 */

	@Query("SELECT lkd.name,lkd.emailID,lkd.mobile FROM SuperStockiestMaster lkd where lkd.id = :code")
	Object findByCode(@Param("code") int code);
	
	@Query("SELECT lkd.mobile FROM SuperStockiestMaster lkd where lkd.id = :id")
	String findMobileById(@Param("id") int id);
	
	@Query("SELECT lkd.terminalid FROM SuperStockiestMaster lkd WHERE id = :id ")
	String findTerminalId(@Param("id") int id);

	@Query("SELECT lkd.id,lkd.name FROM SuperStockiestMaster lkd")
	List<Object> findIdandOutlet();

	
}