package com.example.demo.utils;

import cn.hutool.http.HttpException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 获取accessToken
 * @author HUANG
 *
 */
@Slf4j
@Component
public class Token {

	private static String tokenUrl;
	private static String statusUrl;

//	@Value("${copote.statusUrl}")
	private String statusTemporary;

//	@Value("${copote.tokenUrl}")
	private String tokenTemporary;
	@PostConstruct
	public void init() {
		statusUrl = statusTemporary;
		tokenUrl = tokenTemporary;
	}



	public static String getToken() {

		String responseStr= "";
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(tokenUrl);
		
		JSONObject jsonObject = new JSONObject();
		//应用ID
		jsonObject.put("appId", "EMS");
		//应用密码
		jsonObject.put("secret", "8226F0FAC11B2FC6E0538F460C55A4A0");
		String toJson = jsonObject.toString();
		log.info("获取token请求的路径为："+tokenUrl+"请求的参数为："+toJson);
		
		try {
			RequestEntity se = new StringRequestEntity(toJson, "application/json", "UTF-8");
			post.setRequestEntity((RequestEntity) se);
			post.setRequestHeader("Content-Type", "application/json");
			client.executeMethod(post);
			log.info("获取token的的返回状态为："+post.getStatusCode()+"获取token的返回值为："+post.getResponseBodyAsString());
			responseStr = post.getResponseBodyAsString();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.info( "获取token发送 POST 请求出现异常！" + e);
		} catch (HttpException e) {
			 e.printStackTrace();
			log.info( "获取token发送 POST请求出现异常！" + e);
		} catch (IOException e) {
			e.printStackTrace();
			log.info( "获取token发送 POST请求出现异常！" + e);
		}
	
		return responseStr;
	}
	

	/**
	 * 调接口
	 * @param toJson
	 * @return
	 */
	public static String updateStatus(String toJson) {
		log.info("变更状态接收到的参数为："+toJson);
		String access = getToken();
		
		JSONObject jsonStr = JSONObject.parseObject(access);
		//返回状态码
		String code = (String) jsonStr.get("code");
		//返回状态说明
		String msg = (String) jsonStr.get("msg");
		//授权码
		String accessToken = (String) jsonStr.get("accessToken");
		//失效时间
		String expiresTime = (String) jsonStr.get("expiresTime");
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("accessToken为:"+accessToken);
		try {
			Date overTime = dft.parse(expiresTime);
			if(overTime.getTime()>System.currentTimeMillis()) {
				access = getToken();
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			log.info( "调用主动更新状态发送 POST请求出现异常！" + e1);
		}
		String responseStr="";
		String userToken = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(statusUrl);
		
		try {
			RequestEntity se = new StringRequestEntity(toJson, "application/json", "UTF-8");
			post.setRequestEntity(se);
			post.setRequestHeader("Content-Type", "application/json");
			//accessToken
			post.setRequestHeader("accessToken", accessToken);
			post.setRequestHeader("userToken", userToken);

			log.info("主动更新状态的post===="+post);
			client.executeMethod(post);
			responseStr = post.getResponseBodyAsString();
			log.info("调用主动更新状态的的返回状态为："+post.getStatusCode()+"调用主动更新状态的返回值为："+post.getResponseBodyAsString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.info( "调用主动更新状态发送 POST请求出现异常！" + e);
		} catch (HttpException e) {
			e.printStackTrace();
			log.info( "调用主动更新状态发送 POST请求出现异常！" + e);
		} catch (IOException e) {
			e.printStackTrace();
			log.info( "调用主动更新状态发送 POST请求出现异常！" + e);
		}
		return responseStr;
	}

}
