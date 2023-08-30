package co.yedam.CRUDproject.member.mapper;

import java.util.List;

import co.yedam.CRUDproject.member.service.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();	// 사용자 전체(목록) 조회
	MemberVO memberSelect(MemberVO vo);	// 사용자 상세 조회
	MemberVO memberSelectEmail(MemberVO vo);
	int memberInsert(MemberVO vo);	// 사용자 등록
	int memberUpdate(MemberVO vo);	// 사용자 수정
	int memberDelete(MemberVO vo); // 사용자 삭제
}
