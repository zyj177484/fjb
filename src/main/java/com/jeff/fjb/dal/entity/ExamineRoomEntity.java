package com.jeff.fjb.dal.entity;

public class ExamineRoomEntity {
	private String examineDistinct;
	private String name;
	private int num;
	private int roomId;
	public ExamineRoomEntity(){}
	public ExamineRoomEntity(String examineDistinct, String name, int num) {
		this.examineDistinct = examineDistinct;
		this.name = name;
		this.num = num;
	}

	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getExamineDistinct() {
		return examineDistinct;
	}
	public void setExamineDistinct(String examineDistinct) {
		this.examineDistinct = examineDistinct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
