package model;

public class Post {
	private int postNum;  		 
	private int policyId;  //FK 
	private String userId;   //FK
	private String title;  	 
	private String writeDate;  	 
	private String content;  

	
	public Post(int postNum, int policyId, String userId, String title, String writeDate, String content) {
		this.postNum = postNum;
		this.policyId = policyId;
		this.userId = userId;
		this.title = title;
		this.writeDate = writeDate;
		this.content = content;
	}
	
	
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


//	@Override
//	public String toString() {
//		return "PostDTO] " + pid + " : " + subject + " : " + content + " : " + name + " : " + viewcnt + " : " + regdate;
//	}
}
