package com.connectpay.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connectpay.user.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {	
	
	@Query("SELECT lkd FROM Login lkd WHERE (userid = :userid and  password = :password and terminalid = :terminalid)")
	Login findLoginUserDetails(@Param("userid") String userid, @Param("password") String password , @Param("terminalid") String terminalid);
	
	@Query("SELECT lkd FROM Login lkd WHERE (userid = :userid)")
	Login findbyUserid(@Param("userid") String userid);
	
	@Query("SELECT lkd FROM Login lkd WHERE (role = :role and cpid=:cpid)")
	Login findbyRoleCPid(@Param("role") String role,@Param("cpid") int cpid);

	Login findById(int id);
	
	@Query("SELECT lkd FROM Login lkd WHERE (userid = :userid and  terminalid = :terminalid)")
	Login findbyterminaliduserid(@Param("userid") String userid,  @Param("terminalid") String terminalid);
	
	@Query("SELECT lkd FROM Login lkd WHERE (userid = :userid and  password = :password )")
	Login findByUserIdAndPassword(@Param("userid") String userid, @Param("password") String password );
	
	
	Login findByCpIDAndPassword(int id,String pw);
	
	@Query("SELECT lkd.cpID FROM Login lkd WHERE (terminalid = :terminalid and userid = :userid)")
	int findCpIDByTerminalidAndUserID(@Param("terminalid") String terminalid,@Param("userid") String userid);
	
	Login findByTerminalid(String terminal);
	
}
