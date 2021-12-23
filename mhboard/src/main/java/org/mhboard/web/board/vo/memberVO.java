package org.mhboard.web.board.vo;

public class memberVO {
	
	
	
	private int mid;
	private String memberId;
	private String memberPw;
	private String email;
	private String nickName;
	private String regDt;
	private int admin;
	
	
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
	
	
	@Override
	public String toString() {
		return "memberVO [mid=" + mid + ", memberId=" + memberId + ", memberPw=" + memberPw + ", email=" + email
				+ ", nickName=" + nickName + ", regDt=" + regDt + ", admin=" + admin + "]";
	}
	
	

	
	
	
	

}
