package model;

import java.io.Serializable;

public class Comment implements Serializable { 
	
	private int postNum;
	private char agree; //����
	private String content; // ��� ����
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Comment(char agree, String content) {
		super();
		this.agree = agree;
		this.content = content;
	
	}
	
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	
	public char getAgree() {
		return agree;
	}
	public void setAgree(char agree) {
		this.agree = agree;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
