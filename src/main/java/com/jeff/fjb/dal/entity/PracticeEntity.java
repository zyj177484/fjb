package com.jeff.fjb.dal.entity;

public class PracticeEntity {
	private int id;
	private String question =null;
	private String answer = null;
	private int type;
	private String A = null;
	private String B = null;
	private String C = null;
	private String D = null;
	private String E = null;
	private String hasPhoto = null; 
	private String photo_url = null;
	private byte[] photo = null;
	private int no;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

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

	public String getHasPhoto() {
		return hasPhoto;
	}

	public void setHasPhoto(String hasPhoto) {
		this.hasPhoto = hasPhoto;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
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
