package com.connectpay.user.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManualPasswordEncoder implements PasswordEncoder{
	
	private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";
    
    private static final String URLALGORITHM = "AES";
    private static final String URLKEY = "URfh667adfDEJ78L";
    
    
    
    public String encrypt(String value) throws Exception
    {
    	
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ManualPasswordEncoder.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        //String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
       // String encryptedValue64 = new Base64().encode(encryptedByteValue);
        String encryptedValue64 = Base64.getEncoder().encodeToString(encryptedByteValue);
        return encryptedValue64;
               
    }
    
    public String decrypt(String value) throws Exception
    {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ManualPasswordEncoder.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        //byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
        byte [] decryptedValue64 = Base64.getDecoder().decode(value);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;
                
    }
    
    private static Key generateKey() throws Exception 
    {
        Key key = new SecretKeySpec(ManualPasswordEncoder.KEY.getBytes(),ManualPasswordEncoder.ALGORITHM);
        return key;
    }
    
    public String generateRandomPass() throws Exception
    {
    	String randomPass="";
    	
    	String uniqueID = UUID.randomUUID().toString();
		
    	System.out.println(uniqueID);
    	UUID uniqueKey = UUID.randomUUID();
        System.out.println (uniqueKey);
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890/!@$&".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
    	randomPass = output;
    	
    	return randomPass;
    }

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
        String value=String.valueOf(rawPassword);
        String encryptedValue64="";
        try {
        	encryptedValue64=this.encrypt(value);
        }catch (Exception e) {
			// TODO: handle exception
		}
        return encryptedValue64;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
        boolean returnvalue=false;
        try {
        	String encryptedValue64=this.decrypt(encodedPassword);
        	if(encryptedValue64.equalsIgnoreCase(rawPassword.toString())) {
        		returnvalue=true;
        	}
        }catch (Exception e) {
			// TODO: handle exception
		}
        return returnvalue;
	}
	
	public String urlencrypt(String value) throws Exception
    {
    	
        Key key = urlgenerateKey();
        Cipher cipher = Cipher.getInstance(ManualPasswordEncoder.URLALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        //String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
       // String encryptedValue64 = new Base64().encode(encryptedByteValue);
        String encryptedValue64 = Base64.getEncoder().encodeToString(encryptedByteValue);
        return encryptedValue64;
               
    }
    
    public String urldecrypt(String value) throws Exception
    {
        Key key = urlgenerateKey();
        Cipher cipher = Cipher.getInstance(ManualPasswordEncoder.URLALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        //byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
        byte [] decryptedValue64 = Base64.getDecoder().decode(value);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;
                
    }
    
    private static Key urlgenerateKey() throws Exception 
    {
        Key key = new SecretKeySpec(ManualPasswordEncoder.URLKEY.getBytes(),ManualPasswordEncoder.ALGORITHM);
        return key;
    }
	
	
	
}
