package com.jeff.fjb.dal.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ExamineEntity {
	private String startTimeString, endTimeString, subject, room, examineDistinct;
	private long startTime, endTime, signUp, maxNum, examineId, subjectId, roomId;

	public ExamineEntity(){}

	public ExamineEntity(long subjectId, long roomId, long startTime, long endTime, long signUp,
			long maxNum) {
		this.subjectId = subjectId;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.signUp = signUp;
		this.maxNum = maxNum;
	}

	public String getExamineDistinct() {
		return examineDistinct;
	}

	public void setExamineDistinct(String examineDistinct) {
		this.examineDistinct = examineDistinct;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getStartTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date date = new Date(startTime * 1000);
		return sdf.format(date);
	}

	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}

	public String getEndTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date date = new Date(endTime * 1000);
		return sdf.format(date);
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	public long getExamineId() {
		return examineId;
	}

	public void setExamineId(long examineId) {
		this.examineId = examineId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
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
