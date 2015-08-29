package com.jeff.fjb.dal.entity;

public class ExamineEntity {
	private String subject, examineDistinct, room;
	private long startTime, endTime, signUp, maxNum;

	public ExamineEntity(){}

	public ExamineEntity(String subject, String examineDistinct, String room, long startTime, long endTime, long signUp,
			long maxNum) {
		this.subject = subject;
		this.examineDistinct = examineDistinct;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.signUp = signUp;
		this.maxNum = maxNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getExamineDistinct() {
		return examineDistinct;
	}

	public void setExamineDistinct(String examineDistinct) {
		this.examineDistinct = examineDistinct;
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
