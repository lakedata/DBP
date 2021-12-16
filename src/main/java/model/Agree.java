package model;

import java.io.Serializable;

public class Agree implements Serializable{
	private int agree;
	private int disagree;
	private int postNum;
	
	public Agree() {}
	
	public Agree(int agree, int disagree, int postNum) {
		this.agree = agree;
		this.disagree = disagree;
		this.postNum = postNum;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public int getDisagree() {
		return disagree;
	}

	public void setDisagree(int disagree) {
		this.disagree = disagree;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	
	

}
   