package com.connectpay.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectpay.user.entity.LoginOtp;

public interface LoginOtpRepo extends JpaRepository<LoginOtp, String>{

	LoginOtp findByLoginIdAndDeviceId(int id,String deviceId);

}
