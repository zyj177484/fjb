package com.jeff.fjb.dal.entity;

public class OrderEntity {
	private String orderId, content, payed, status;
	private long charge, time;
	
	public OrderEntity() {}
	
	public OrderEntity(String orderId, String content, String payed, long charge, long time, String status) {
		this.orderId = orderId;
		this.content = content;
		this.payed = payed;
		this.charge = charge;
		this.time = time;
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPayed() {
		return payed;
	}
	public void setPayed(String payed) {
		this.payed = payed;
	}
	public long getCharge() {
		return charge;
	}
	public void setCharge(long charge) {
		this.charge = charge;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
