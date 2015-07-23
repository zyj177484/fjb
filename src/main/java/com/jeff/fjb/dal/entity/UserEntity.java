package com.jeff.fjb.dal.entity;

public class UserEntity {
	private String id;
    private String username;  
    private String password;
    private String sessionId;
    private String role;
    private byte[] photo = null;
    private String zonghang;
    private String fenhang;
    private String zhihang;
    private String fenlichu;
    private String sex;
    private String mobile;
    public UserEntity(){}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getZonghang() {
		return zonghang;
	}
	public void setZonghang(String zonghang) {
		this.zonghang = zonghang;
	}
	public String getFenhang() {
		return fenhang;
	}
	public void setFenhang(String fenhang) {
		this.fenhang = fenhang;
	}
	public String getZhihang() {
		return zhihang;
	}
	public void setZhihang(String zhihang) {
		this.zhihang = zhihang;
	}
	public String getFenlichu() {
		return fenlichu;
	}
	public void setFenlichu(String fenlichu) {
		this.fenlichu = fenlichu;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
