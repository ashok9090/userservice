package com.connectpay.user.secuirity;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.connectpay.user.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Exception exception = (Exception) request.getAttribute("exception");

		String message;

		if (exception != null) {
			ErrorDetails details=new ErrorDetails(new Date(),  exception.toString(), request.getRequestURI());

			byte[] body = new ObjectMapper().writeValueAsBytes(details);

			response.getOutputStream().write(body);

		} else {

			if (authException.getCause() != null) {
				message = authException.getCause().toString() + " " + authException.getMessage();
			} else {
				message = authException.getMessage();
			}
			ErrorDetails details=new ErrorDetails(new Date(),  message, request.getRequestURI());
			byte[] body = new ObjectMapper().writeValueAsBytes(details);

			response.getOutputStream().write(body);
		}
	}

}
