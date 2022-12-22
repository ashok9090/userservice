package com.connectpay.user.secuirity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.connectpay.user.entity.Login;
import com.connectpay.user.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public LoginDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String[] terminalANDUSERID =username.split(":");
		Login login =loginRepository.findbyterminaliduserid(terminalANDUSERID[1], terminalANDUSERID[0]);
		log.info("After getting data by userid and terminal id "+login);
		if(login==null) {
			throw new UsernameNotFoundException("Could not find User");
		}
		return new LoginDetails(login);
	}

}
