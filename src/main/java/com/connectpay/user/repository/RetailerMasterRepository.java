package com.connectpay.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.RetailerMaster;

public interface RetailerMasterRepository extends JpaRepository<RetailerMaster, Integer>{

	RetailerMaster findById(int id);
	
	RetailerMaster findByMobile(String mobile);

	RetailerMaster findByEpagentid(String epagentid);

	RetailerMaster findByYblagentid(String yblagentid);

	RetailerMaster findByTerminalid(String tid);

	RetailerMaster findByIrctcid(String irctcid);
	
	@Query("SELECT lkd.wBalance FROM RetailerMaster lkd WHERE id = :id")
    double findWBalanceById(@Param("id") int id);
	
	@Query("SELECT lkd.aBalance FROM RetailerMaster lkd WHERE id = :id")
    double findaBalanceById(@Param("id") int id);

	
	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (sid = :sid)")
	List<RetailerMaster> findbyStockiest(@Param("sid") int sid);
	
	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (name = :name and sid= :sid)")
	List<RetailerMaster> findbyName(@Param("name") String name,@Param("sid") int sid);
	
	@Query("SELECT lkd FROM RetailerMaster lkd WHERE sid in (select abc FROM RetailerMaster abc WHERE ssid=:ssid)")
	List<RetailerMaster> findbySuperStockiest(@Param("ssid") int ssid);

	@Query("SELECT COALESCE(MAX(CAST(SUBSTRING(terminalid, 12,4) AS int)),0) as tid  FROM RetailerMaster lkd WHERE (SUBSTRING(terminalid, 1,11) =:prefix)")
	String getLastTerminalID(@Param("prefix") String prefix);

	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (sid = :sid and COALesce(approved,'N')=:status)")
	List<RetailerMaster> findbyStockiestStatus(@Param("sid") int sid,@Param("status")  String status);

	RetailerMaster findByUniqId(String uniqid);
	@Query("SELECT COALESCE(count(terminalid) ,0) as tcount  FROM RetailerMaster lkd WHERE (ssid=:ssid)")
	String getTotalCount(@Param("ssid") int ssid);

	@Query("SELECT COALESCE(count(terminalid) ,0) as tcount  FROM RetailerMaster lkd WHERE (sid=:sid)")
	String getTotalCountStockiest(@Param("sid") int sid);

	List<RetailerMaster> findByArzooTaIdIsNull();
	
	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (salespersonid = :spid)")
	List<RetailerMaster> findbySP(@Param("spid") int spid);

	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (officestateid = :stateid)")
	List<RetailerMaster> findbyState(@Param("stateid") int stateid);

	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (mobile = :mobile and salespersonid=:spid)")
	RetailerMaster findByMobileSpid(@Param("mobile") String mobile,@Param("spid") int spid);
	
	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (terminalid = :tid and salespersonid =:spid)")
	RetailerMaster findByTerminalIDSpid(@Param("tid") String tid,@Param("spid") int spid);

	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (mobile = :mobile and officestateid=:stateid)")
	RetailerMaster findByMobileStateid(@Param("mobile") String mobile,@Param("stateid") int stateid);
	
	@Query("SELECT lkd FROM RetailerMaster lkd WHERE (terminalid = :tid and officestateid =:stateid)")
	RetailerMaster findByTerminalIDStateid(@Param("tid") String tid,@Param("stateid") int stateid);

	
	@Query("SELECT lkd.id FROM RetailerMaster lkd WHERE lkd.StockiestMaster.id = :sid")
	List<Integer> findIdbySuperStockiestId(@Param("sid") int sid);

	
	@Query("SELECT lkd.outletName FROM RetailerMaster lkd WHERE id= :id")
	String findNameById(@Param("id") int id);
	
	@Query("SELECT lkd.aBalance FROM RetailerMaster lkd WHERE id= :id")
	double findABalanceById(@Param("id") int id);
	
	@Query("SELECT lkd.outletName,lkd.emailID,lkd.mobile,lkd.dob FROM RetailerMaster lkd where lkd.id = :code")
	Object findByCode(@Param("code") int code);
	
	@Query("SELECT lkd.mobile FROM RetailerMaster lkd where lkd.id = :id")
	String findMobileById(@Param("id") int id);
	
	@Query("SELECT lkd.id,lkd.outletName FROM RetailerMaster lkd")
	List<Object> findIdandOutlet();
	
	@Query("SELECT lkd.id FROM RetailerMaster lkd WHERE lkd.terminalid = :terminalid")
	Integer findIdByTerminalid(@Param("terminalid") String terminalid);
	
	@Query("SELECT lkd.id FROM RetailerMaster lkd WHERE lkd.mobile = :mobile")
	List<Integer> findIdBymobile(@Param("mobile") String mobile);

	@Query("SELECT lkd.id,lkd.outletName FROM RetailerMaster lkd WHERE lkd.StockiestMaster.id = :sid")
	List<Object> findIdandOutletBySId(@Param("sid") int sid);
	
	@Query("SELECT lkd.StockiestMaster.id FROM RetailerMaster lkd WHERE id= :id")
	int findStockiestMasterIdById(@Param("id") int id);
	
	@Query("SELECT lkd.superStockiestMaster.id FROM RetailerMaster lkd WHERE id= :id")
	int findSuperStockiestMasterIdById(@Param("id") int id);
}
