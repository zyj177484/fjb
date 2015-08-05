package com.jeff.fjb.dal.entity;

public class PracticeEntity {
	private int id;
	private String question;
	private String answer;
	private int type;
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private byte[] photo;
	
	public PracticeEntity(){}
	
	public PracticeEntity(int id, String question, String answer, int type, String a, String b, String c, String d, String e) {
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.type = type;
		A = a;
		B = b;
		C = c;
		D = d;
		E = e;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}

	public String getE() {
		return E;
	}

	public void setE(String e) {
		E = e;
	}

}
