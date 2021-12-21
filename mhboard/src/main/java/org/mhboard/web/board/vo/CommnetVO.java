package org.mhboard.web.board.vo;

public class CommnetVO {

	
	private int cid;
	private int bid;
	private String content;
	private String regId;
	private String regDt;
	private String editDt;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "CommnetVO [cid=" + cid + ", bid=" + bid + ", content=" + content + ", regId=" + regId + ", regDt="
				+ regDt + ", editDt=" + editDt + "]";
	}
	
	
	
	
	
	
}
