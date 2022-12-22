package com.connectpay.user.secuirity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.encode("12345"));
	}
}

//$2a$10$Zm3GFjIKxrwPwmHpZYvo0.ZVsD6wlml0tTi64yd6E4vTG/iWGHe2W
//$2a$10$EOubvEgdm2hQLklmY1a9G.AVq7d6ngzYfwLt3qZ5.YpST84a/6.Pu