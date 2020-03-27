package com.example.demo.model;

public class Trace {

	private String acceptTime;
	
	private String acceptAddress;
	
	private String remark;
	
	private String code;

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAcceptAddress() {
		return acceptAddress;
	}

	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "trace [acceptTime=" + acceptTime + ", acceptAddress=" + acceptAddress + ", remark=" + remark + ", code="
				+ code + "]";
	}
	
	
}
