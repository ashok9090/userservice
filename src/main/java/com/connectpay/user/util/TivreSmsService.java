package com.connectpay.user.util;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.connectpay.user.constant.TivreConstant;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class TivreSmsService {	
	private static final Logger _TIVRELog = LoggerFactory.getLogger(TivreConstant.CP_TIVRE_LOGS);
	private static final String _logClassName = TivreSmsService.class.getSimpleName();

	public String sendSms(Sms sms) {
		String responseObj = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
		_TIVRELog.info(_logClassName+ "<--------send sms started----> ");
			_TIVRELog.info(_logClassName+ "<--------message----> " +sms.getMsg());

			String msg =  URLEncoder.encode(sms.getMsg(),"UTF-8").replace("+", "%20");
			_TIVRELog.info(_logClassName+ "<--------message after encode----> " +msg);
			if (msg.length()<140) {
				OkHttpClient client = new OkHttpClient().newBuilder()
						  .build();
						Request request = new Request.Builder()
						  .url(TivreConstant.SMSURL + "Userid=" +TivreConstant.USERID + "&password=" + TivreConstant.PASSWORD + 
								  "&Msg=" + msg + "&mobnum="+sms.getMobnum() + "&senderid=" + TivreConstant.SENDERID +
								  "&msgid=" +sms.getMsgid() + "&qrytype=Simple&TivreID=1")
						  .method("GET", null)
						  .build();
						_TIVRELog.info(_logClassName+ "<--------request URL----> " +request.url());
	
						Response response = client.newCall(request).execute();
						_TIVRELog.info(_logClassName+ "<--------response----> " +response.code());
						response.close();
			}
			else {
				OkHttpClient client = new OkHttpClient().newBuilder()
						  .build();
						Request request = new Request.Builder()
						  .url(TivreConstant.SMSURL + "Userid=" +TivreConstant.USERID + "&password=" + TivreConstant.PASSWORD + 
								  "&Msg=" + msg + "&mobnum="+sms.getMobnum() + "&senderid=" + TivreConstant.SENDERID +
								  "&msgid=" +sms.getMsgid() + "&qrytype=Simple&TivreID=1&param3=1")
						  .method("GET", null)
						  .build();
						_TIVRELog.info(_logClassName+ "<--------request URL----> " +request.url());
	
						Response response = client.newCall(request).execute();
						_TIVRELog.info(_logClassName+ "<--------response----> " +response.code());
						response.close();
			}
					
		} catch (Exception e) {
			_TIVRELog.info(_logClassName+ "<--------Error----> " +e.getMessage());
			e.printStackTrace();
		}
		finally {
			
		}
		return "";
	}
	
	public String GetDeliveryReport (Sms sms, String dnsid) {
		String deliveryURL = "https://sms.tivre.com/httppush/reporttxt.asp";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();	
		String result = null;
		try {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(deliveryURL)
									  .queryParam("Userid", sms.getUserid()) 
									  .queryParam("password",sms.getPassword())
									  .queryParam("dnsid",dnsid)
									  .queryParam("sourcetable","SMPPSMS3"); 
		HttpEntity<?> entity = new HttpEntity<Object>(headers);  
		HttpEntity<String> response = restTemplate.exchange( builder.toUriString(),
	  								  HttpMethod.GET, entity, String.class);
		result =  response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
