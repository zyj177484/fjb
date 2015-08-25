package com.jeff.fjb.dal.entity;

public class ExamineSubjectEntity {
	private String subject, note;
	private long charge, regTime, resultTime;
	
	public ExamineSubjectEntity(String subject, String note, long charge, long regTime, long resultTime) {
		this.subject = subject;
		this.note = note;
		this.charge = charge;
		this.regTime = regTime;
		this.resultTime = resultTime;
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
