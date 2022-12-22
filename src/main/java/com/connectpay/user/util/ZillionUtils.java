package com.connectpay.user.util;

import java.io.File;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
@Service
public class ZillionUtils {
		
	  /*||===============================23/12/16=======================================||
		||===============================Author Nithin==================================||
		||==Get file count from a folder in AWS S3 using awsS3DirectoryFileCount()======||
		||==============================================================================||*/
		  public static int countFilesInDirectory(HttpServletRequest request, int packageid) {
				   String tomcatPath = System.getProperty("catalina.base");
				   String projectPathTemp = request.getContextPath();
				   String projectPath = projectPathTemp.substring(1);
				   System.err.println("projectPath"+projectPath);
				   projectPath = File.separator + projectPath;
				   System.err.println("projectPath with file separator"+projectPath);
				   int count = 0;
				   int imgdefaultCount=1;
				   File directory = null;
				   System.err.println(tomcatPath+File.separator+"webapps"+projectPath+File.separator+"resources"+File.separator+"packages"+File.separator+packageid+File.separator);
				     directory = new File(tomcatPath+File.separator+"webapps"+projectPath+File.separator+"resources"+File.separator+"packages"+File.separator+packageid+File.separator);
				  
				  try{
					  if(null!=directory.listFiles())
					  for (File file : directory.listFiles()) {
				          if (file.isFile()) {
				              count++;
				          }
				          if (file.isDirectory()) {
				              count += countFilesInDirectory(request, packageid);
				          }
					 }
				      System.err.println("imagecount");
				      
					  System.err.println(count);
					  return count;
			     
			      }catch(Exception exception){
			    	exception.printStackTrace();
			    	return imgdefaultCount;
			      }
			      
			  }
		
			  /*
			   * countFilesInDirectory() ends here
			   */
		  
	
		  public String generateRandomNumber (int upperbound) {
				Random rand = new Random(); //instance of random class
				int number= rand.nextInt(upperbound);
				return String.format("%06d", number);
				
			}
		  public final String smsMsgPart1 = "Tivre OTP code is ";
			public final String smsMsgPart2 = "-Tivre";
}
