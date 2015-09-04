package com.jeff.fjb.dal.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ExamineSubjectEntity {
	private String subject, note;
	private long charge, regTime, resultTime, subjectId;
	private String resultTimeString;
	
	public ExamineSubjectEntity(){}
	
	public ExamineSubjectEntity(String subject, String note, long charge, long regTime, long resultTime) {
		this.subject = subject;
		this.note = note;
		this.charge = charge;
		this.regTime = regTime;
		this.resultTime = resultTime;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getResultTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date date = new Date(resultTime * 1000);
		return sdf.format(date);
	}
	public void setResultTimeString(String resultTimeString) {
		this.resultTimeString = resultTimeString;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public long getCharge() {
		return charge;
	}
	public void setCharge(long charge) {
		this.charge = charge;
	}
	public long getRegTime() {
		return regTime;
	}
	public void setRegTime(long regTime) {
		this.regTime = regTime;
	}
	public long getResultTime() {
		return resultTime;
	}
	public void setResultTime(long resultTime) {
		this.resultTime = resultTime;
	}

	
}
