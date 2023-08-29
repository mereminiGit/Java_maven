package co.yedam.test.service;

import java.util.List;

public interface MemberService {
	// CRUD (조회, 추가, 수정, 삭제) 기능들을 쭉 적으면 된다
	List<MemberVO> memberSelectList();  // R
	MemberVO memberSelect(MemberVO vo);	// R
	int memberInsert(MemberVO vo);	// C
	int memberUpdate(MemberVO vo);	// U
	int memberDelete(MemberVO vo);	// D
	// 메소드명을 앞에 테이블명 뒤에 기능을 적는다 (의미가 정확하게 전달되게 만들게되면 set get을 안 써도 된다)
}
