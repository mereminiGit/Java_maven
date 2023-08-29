package co.yedam.test.map;

import java.util.List;

import co.yedam.test.service.MemberVO;

public interface MemberMapper {
	// 서비스 인스페이스와 기본적으로 내용이 동일하다
	// 그래야 서비스와 mapper를 맵핑시킬 수 있으니
	List<MemberVO> memberSelectList();  // R
	MemberVO memberSelect(MemberVO vo);	// R
	int memberInsert(MemberVO vo);	// C
	int memberUpdate(MemberVO vo);	// U
	int memberDelete(MemberVO vo);	// D
}
