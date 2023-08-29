package co.yedam.test.service;

import java.time.LocalDate;

import lombok.Data;

// vo 객체 만들는 클래스 (테이블 칼럼명을 보면서 만들어야함)

@Data	// lombok
public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberTel;
	// 자바 8부터는 localdate가 나와서 이걸 사용함 / 시간까지 관리해야해(localdatetime) / 날짜만 관리localdate
	private LocalDate memberEnterDate;
}
