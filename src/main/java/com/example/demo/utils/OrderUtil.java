package com.example.demo.utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderUtil {

	private final static String AUTHORIZATION = "408a6c32e61d3ad5cb5c4e0cb3d2b089";
	private final static String APP_KEY = "8bd10fea96a9343253009a00ea312207";
	private final static String APP_SECRET = "45a9fdfbea2b22d8cbdf559f419d69ba";
	private final static String URL = "http://60.205.8.187:18001/api/gateway";

	public static String getOrder(String mailNo) {
//		String authorization = "08a01270e1fabcc7b783d50a1b637567";
//		String appkey = "45de26fefc75962c7fcfb7eb1db47012";
//		String appsecret = "44953b37aea240cd6775fc75a36a3228";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cmd = format.format(new Date());

        Map<String,String> map=new HashMap<String,String>();
        map.put("authorization",AUTHORIZATION);
		map.put("app_key",APP_KEY);
		map.put("method","ems.inland.trace.query");

		map.put("timestamp",cmd);
		map.put("version","V3.01");
		map.put("format","json");
		map.put("charset","UTF-8");
		
		map.put("mailNo",mailNo);
		//排序
		String content = SignUtil.getSortParams(map) + APP_SECRET;
		//加密
		String sign=SignUtil.sign(content,"UTF-8");
		System.out.println("调用总部物流查询接口的签名："+sign);
		map.put("sign", sign);
		String body=SignUtil.toKey(map);
		System.out.println("调用总部物流查询接口拼接的字符串："+body);
		String reslut="";
		try {
			reslut = HttpUtil.sendHttpPost(URL, body,"UTF-8", null,"application/x-www-form-urlencoded;charset=UTF-8");
//			reslut = HttpUtil.sendHttpPost("http://60.205.8.187:18001/api/gateway", body,"UTF-8", null,"application/x-www-form-urlencoded;charset=UTF-8");
			System.out.println("调用总部物流查询接口返回的结果为："+reslut);
		} catch (Exception e) {
			System.out.println("服务器抛出了异常，启动失败");
			e.printStackTrace();
		}
		return reslut;
	}


	public static String putOrder(JSONObject json) {
//		String authorization = "08a01270e1fabcc7b783d50a1b637567";
//		String appkey = "45de26fefc75962c7fcfb7eb1db47012";
//		String appsecret = "44953b37aea240cd6775fc75a36a3228";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cmd = format.format(new Date());

		Map<String,String> map=new HashMap<String,String>();
		map.put("authorization",AUTHORIZATION);
		map.put("app_key",APP_KEY);
		map.put("method","ems.inland.order.create.normal");

		map.put("timestamp",cmd);
		map.put("version","V3.01");
		map.put("format","json");
		map.put("charset","UTF-8");
//		String str = "{\"orderNormals\":[{\"txLogisticID\":\"1015983797592\",\"mailNo\":\"\",\"orderType\":\"1\",\"serviceType\":\"1\",\"deliveryTime\":\"2018-04-17 07:00:00\",\"revertBill\":\"0\",\"revertMailNo\":\"gotMail\",\"sender\":{\"name\":\"浦城县人民法院\",\"custCode\":\"35072200013000\",\"phone\":\"2838548\",\"prov\":\"福建省\",\"city\":\"南平市\",\"county\":\"蒲城县\",\"address\":\"福建省南平市浦城县人民法院\"},\"receiver\":{\"name\":\"周黎敏\",\"phone\":\"8625258\",\"prov\":\"福建省\",\"city\":\"南平市\",\"county\":\"延平区\",\"address\":\"福建省南平市延平区南平市中级人民法院\"}}]}";
//		System.out.println(str);
//		JSONObject jsonObject = JSONObject.parseObject(str);
        map.put("orderNormal",json.toString());
		//排序
		String content = SignUtil.getSortParams(map) + APP_SECRET;
		System.out.println(content);
		//加密
		String sign=SignUtil.sign(content,"UTF-8");
		System.out.println("调用总部物流查询接口的签名："+sign);
		map.put("sign", sign);
		String body=SignUtil.toKey(map);
		System.out.println("调用总部物流查询接口拼接的字符串："+body);
		String reslut="";
		try {
			reslut = HttpUtil.sendHttpPost(URL, body,"UTF-8", null,"application/x-www-form-urlencoded;charset=UTF-8");
//			reslut = HttpUtil.sendHttpPost("http://60.205.8.187:18001/api/gateway", body,"UTF-8", null,"application/x-www-form-urlencoded;charset=UTF-8");
			System.out.println("调用总部物流查询接口返回的结果为："+reslut);
		} catch (Exception e) {
			System.out.println("服务器抛出了异常，启动失败");
			e.printStackTrace();
		}
		return reslut;
	}
	
}
