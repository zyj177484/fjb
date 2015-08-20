package com.jeff.fjb.dal.entity;

public class BankEntity {
	String id;
	String boss;
	String name;
	String address;
	String contactPeople;
	String phone;
	String email;
	public BankEntity(){}
	
	public BankEntity(String id, String boss, String name, String address, String contactPeople, String phone,
			String email) {
		super();
		this.id = id;
		this.boss = boss;
		this.name = name;
		this.address = address;
		this.contactPeople = contactPeople;
		this.phone = phone;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoss() {
		return boss;
	}
	public void setBoss(String boss) {
		this.boss = boss;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPeople() {
		return contactPeople;
	}
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
