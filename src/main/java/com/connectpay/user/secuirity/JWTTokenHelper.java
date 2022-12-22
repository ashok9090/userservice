package com.connectpay.user.secuirity;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.connectpay.user.Interface.UserInterface;
import com.connectpay.user.request.AuthenticationRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Component
public class JWTTokenHelper {
	
	
	@Value("${jwt.auth.app}")
	private String appName;
	
	@Value("${jwt.auth.secret_key}")
	private String secretKey;
	
	@Value("${jwt.auth.expires_in}")
    private int expiresIn;
	
	@Autowired
	private UserInterface userInterface;
	
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;


	
	public Claims getAllClaimsFromToken(String token) throws Exception{
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
            throw e;
        }
        return claims;
    }

	
	 public String getUsernameFromToken(String token) throws Exception{
	        String username;
	        try {
	            final Claims claims = this.getAllClaimsFromToken(token);
	            username = claims.getSubject();
	        } catch (Exception e) {
	            username = null;
	            throw e;
	        }
	        return username;
	 }
	 
	 
	 
	 public String generateToken(String username,AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
		 Map<String, Object> claims = new HashMap<>();
		 claims.put("DI",authenticationRequest.getDeviceId());
		 claims.put("DT",authenticationRequest.getDevicetype());
	        return Jwts.builder()
	        		.addClaims(claims)
	                .setIssuer( appName )
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(generateExpirationDate())
	                .signWith( SIGNATURE_ALGORITHM, secretKey )
	                .compact();
	  }
	 
	 private Date generateExpirationDate() {
		 return new Date(new Date().getTime() + expiresIn * 1000);
	 }
	 
	 public Boolean validateToken(String token, UserDetails userDetails) throws Exception{
		 
		 final Claims claims = this.getAllClaimsFromToken(token);
		 Map<String, Object> expectedMap=getMapFromIoJsonwebtokenClaims(claims);
	   	String deviceid=(String)expectedMap.get("DI");
		String username=(String)expectedMap.get("sub");
	     CompletableFuture<Boolean> future= userInterface.checkForLogout(deviceid,username);
	        return (
	                username != null &&
	                username.equals(userDetails.getUsername()) &&
	                        !isTokenExpired(token) && !future.get()
	        );
	  }
	 
	 public boolean isTokenExpired(String token) {
		Date expireDate=getExpirationDate(token);
		return expireDate.before(new Date());
	}


	private Date getExpirationDate(String token) {
		 Date expireDate;
	        try {
	            final Claims claims = this.getAllClaimsFromToken(token);
	            expireDate = claims.getExpiration();
	        } catch (Exception e) {
	        	expireDate = null;
	        }
	        return expireDate;
	}


	public Date getIssuedAtDateFromToken(String token) {
	        Date issueAt;
	        try {
	            final Claims claims = this.getAllClaimsFromToken(token);
	            issueAt = claims.getIssuedAt();
	        } catch (Exception e) {
	            issueAt = null;
	        }
	        return issueAt;
	  }
	
	public String getToken( HttpServletRequest request ) {
      
        String authHeader = getAuthHeaderFromHeader( request );
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

	public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader("Authorization");
    }


	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith( SIGNATURE_ALGORITHM, secretKey).compact();
	}
	public Map<String, Object> getMapFromIoJsonwebtokenClaims(Claims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		if(claims!=null)
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
	
}
