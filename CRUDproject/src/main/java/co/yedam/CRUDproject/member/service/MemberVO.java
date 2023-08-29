package co.yedam.CRUDproject.member.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private String memberTel;
	private LocalDate memberEnterDate;
	private int memberCheck;
	
	
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPassword=" + memberPassword + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberTel=" + memberTel + ", memberEnterDate=" + memberEnterDate
				+ ", memberCheck=" + memberCheck + "]";
	}
	
	public String showMemberList() {
		return "| 아이디 : " + memberId + " | 이름 : " + memberName + " | 이메일 : " + memberEmail;
	}
	
	public String showMemberDetail() {
		return " | 아이디 : " + memberId + " | 비밀번호 : " + memberPassword + " | 이름 : " + memberName + "\n"
			+"--------------------------------------------------" + "\n"
			+ " | 이메일 : " + memberEmail + " | 전화번호 : " + memberTel + "\n"
			+"--------------------------------------------------" + "\n"
			+ " | 가입일자 : " + memberEnterDate + " | 관리자 여부 : " + memberCheck;
	}
}
