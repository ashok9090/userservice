package com.connectpay.user.entityservice;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.connectpay.user.entity.Login;
import com.connectpay.user.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserEntityServcie {
	 
	 @Autowired
	    private LoginRepository loginRepository;

	 
	@Async("taskExecutor")
	public CompletableFuture<Login> findByUserNameAndPasswords(String userName, String encodePassword){
		// TODO Auto-generated method stub
		Login login=new Login();
		try {
			//login=loginRepository.findByUsernameAndPassword(userName,encodePassword);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Retrive Data from table Exception "+e.getMessage());
			login=null;
		}
		return CompletableFuture.completedFuture(login);
	}

	/*@Async("taskExecutor")
	public CompletableFuture<User> findById(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return CompletableFuture.completedFuture(userRepository.findById(userId));
	}*/
	 
	 
	 

}
