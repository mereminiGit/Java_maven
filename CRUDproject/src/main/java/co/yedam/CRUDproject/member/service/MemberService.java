package co.yedam.CRUDproject.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> memberSelectList();	// 사용자 전체(목록) 조회
	MemberVO memberSelect(MemberVO vo);	// 사용자 상세 조회
	MemberVO memberSelectEmail(MemberVO vo); // 이메일로 상세 조회
	int memberInsert(MemberVO vo);	// 사용자 등록
	int memberUpdate(MemberVO vo);	// 사용자 수정
	int memberDelete(MemberVO vo); // 사용자 삭제
}
