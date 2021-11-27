package model;

import java.io.Serializable;

public class Reply implements Serializable { 
	
	private int postNum;
	private char agree; // 찬반
	private String replyContent; // 댓글 내용
	
	public Reply() { }
	
	public Reply(int postNum, char agree, String replyContent) {
		this.postNum = postNum;
		this.agree = agree;
		this.replyContent = replyContent;
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
	
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

}
