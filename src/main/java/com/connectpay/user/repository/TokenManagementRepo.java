package com.connectpay.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectpay.user.entity.TokenManagement;

@Repository
public interface TokenManagementRepo extends JpaRepository<TokenManagement, Long>{

	TokenManagement findByDeviceIdAndUserId(String deviceId,String userName);
	
	//TokenManagement findByTokenAndUserId(String token,String userName);
	
	//TokenManagement findByTokenAndDeviceId(String token,String deviceId);

	///TokenManagement findByToken(String token);
	
}
