package org.mhboard.web.board.vo;

public class BoardVO {


	private int bid;
	private String cateCd;
	private String title;
	private String content;
	private String tag;
	private int viewCnt;
	private String regId;
	private String regDt;
	private String editDt;


	

	
	
	
	
	public BoardVO() {
		
	}

	
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getCateCd() {
		return cateCd;
	}


	public void setCateCd(String cateCd) {
		this.cateCd = cateCd;
	}


	public int getViewCnt() {
		return viewCnt;
	}


	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	public String getEditDt() {
		return editDt;
	}


	public void setEditDt(String editDt) {
		this.editDt = editDt;
	}


	@Override
	public String toString() {
		return "BoardVO [bid=" + bid + ", cateCd=" + cateCd + ", title=" + title + ", content=" + content + ", tag="
				+ tag + ", viewCnt=" + viewCnt + ", regId=" + regId + ", regDt=" + regDt + ", editDt=" + editDt + "]";
	}

	

	
	
	
	
	
	
}
