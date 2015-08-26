package com.jeff.fjb.dal.entity;

public class ExamineEntity {
	private String subject, ditinct, room;
	private long startTime, endTime, signUp, maxNum;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDitinct() {
		return ditinct;
	}
	public void setDitinct(String ditinct) {
		this.ditinct = ditinct;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getSignUp() {
		return signUp;
	}
	public void setSignUp(long signUp) {
		this.signUp = signUp;
	}
	public long getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(long maxNum) {
		this.maxNum = maxNum;
	}
	
}
