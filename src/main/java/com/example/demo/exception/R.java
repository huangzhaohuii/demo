

package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("Svc_Rsp_St", "00");
		put("Svc_Rsp_CD", "100001");
		put("Sign_inf", "");
	}
	
	public static R error() {
		return error("系统错误");
	}
	
	public static R error(String msg) {
		R r = new R();
		r.put("Svc_Rsp_CD", "200001");
		r.put("Svc_Rsp_St", "01");
		r.put("Rsp_Inf",msg);
		r.put("Sign_inf", "");
		return r;
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("Svc_Rsp_St", "01");
		r.put("Svc_Rsp_CD", "200001");
		r.put("Sign_inf", "");
		r.put("Rsp_Inf", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("Rsp_Inf", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
