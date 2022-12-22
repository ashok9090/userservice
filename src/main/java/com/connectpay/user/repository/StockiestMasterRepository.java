package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.StockiestMaster;

public interface StockiestMasterRepository extends JpaRepository<StockiestMaster, String>{

	StockiestMaster findById(int id);
	
	StockiestMaster findByTerminalid(String tid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (mobile = :mobile and id<>:sid)")
	StockiestMaster findByMobile(@Param("mobile") String mobile,@Param("sid") int sid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (ssid = :ssid)")
	List<StockiestMaster> findbySuperStockiest(@Param("ssid") int ssid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (name = :name and ssid= :ssid)")
	List<StockiestMaster> findbyName(@Param("name") String name,@Param("ssid") int ssid);
	
	@Query("SELECT COALESCE(MAX(CAST(SUBSTRING(terminalid, 9,3) AS int)),0) as tid  FROM StockiestMaster lkd WHERE (SUBSTRING(terminalid, 1,8) =:prefix)")
	String getLastTerminalID(@Param("prefix") String prefix);

	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (regionid = :regid and approved='Y')")
	List<StockiestMaster> findbyregion(@Param("regid") int regid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (officestateid = :stateid and approved='Y')")
	List<StockiestMaster> findbystate(@Param("stateid") int stateid);

	@Query("SELECT COALESCE(count(terminalid) ,0) as tcount  FROM StockiestMaster lkd WHERE (ssid=:ssid)")
	String getTotalCount(@Param("ssid") int ssid);

	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (salespersonid = :spid)")
	List<StockiestMaster> findbySP(@Param("spid") int spid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (mobile = :mobile and salespersonid=:spid)")
	StockiestMaster findByMobileSpid(@Param("mobile") String mobile,@Param("spid") int spid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (terminalid = :tid and salespersonid =:spid)")
	StockiestMaster findByTerminalIDSpid(@Param("tid") String tid,@Param("spid") int spid);

	/*
	 * @Query("SELECT lkd.wBalance FROM StockiestMaster lkd WHERE id = :id") double
	 * findWBalanceById(@Param("id") int id);
	 * 
	 * @Query("SELECT lkd.aBalance FROM StockiestMaster lkd WHERE id = :id") double
	 * findaBalanceById(@Param("id") int id);
	 */
	
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (mobile = :mobile and officestateid=:stateid)")
	StockiestMaster findByMobileStateid(@Param("mobile") String mobile,@Param("stateid") int stateid);
	
	@Query("SELECT lkd FROM StockiestMaster lkd WHERE (terminalid = :tid and officestateid =:stateid)")
	StockiestMaster findByTerminalIDStateid(@Param("tid") String tid,@Param("stateid") int stateid);
	

	@Query("SELECT lkd.id FROM StockiestMaster lkd WHERE lkd.superStockiestMaster.id = :ssid")
	List<Integer> findIdbySuperStockiestId(@Param("ssid") int ssid);
	
	@Query("SELECT lkd.outletName FROM StockiestMaster lkd WHERE id = :id")
	String findNameById(@Param("id") int id);

	
	/*
	 * @Query("SELECT lkd.wBalance FROM StockiestMaster lkd WHERE id = :id ") double
	 * findWbalance(@Param("id") int id);
	 * 
	 * @Query("SELECT lkd.aBalance FROM StockiestMaster lkd WHERE id = :id ") double
	 * findAbalance(@Param("id") int id);
	 */

	@Query("SELECT lkd.outletName,lkd.emailID,lkd.mobile FROM StockiestMaster lkd where lkd.id = :code")
	Object findByCode(@Param("code") int code);
	
	@Query("SELECT lkd.mobile FROM StockiestMaster lkd where lkd.id = :id")
	String findMobileById(@Param("id") int id);
	
	@Query("SELECT lkd.superStockiestMaster.id FROM StockiestMaster lkd WHERE id = :id ")
	int findSSID(@Param("id") int id);
	
	@Query("SELECT lkd.terminalid FROM StockiestMaster lkd WHERE id = :id ")
	String findTerminalId(@Param("id") int id);
	
	@Query("SELECT lkd.id,lkd.outletName FROM StockiestMaster lkd")
	List<Object> findIdandOutlet();

	
}
