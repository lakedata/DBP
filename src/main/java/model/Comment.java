package model;

import java.io.Serializable;

public class Comment implements Serializable { 
	
	private int postNum;
	private char agree; // 찬반
	private String content; // 댓글 내용
	
	public Comment() { }
	
	public Comment(int postNum, char agree, String content) {
		this.postNum = postNum;
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

}
