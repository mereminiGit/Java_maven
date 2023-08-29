package co.yedam.test.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.test.common.SHA256;
import co.yedam.test.service.MemberService;
import co.yedam.test.service.MemberVO;
import co.yedam.test.serviceImpl.MemberServiceImpl;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService dao = new MemberServiceImpl();
	private SHA256 sha256 = new SHA256();
	
	private void memberTitle() {
		System.out.println("=================================");
		System.out.println("             멤버 관리             ");
//		System.out.println("             0. 로그인         ");
		System.out.println("          1. 멤버 전체 조회         ");
		System.out.println("          2. 멤버 한건 조회         ");
		System.out.println("            3. 멤버 등록           ");
		System.out.println("            4. 멤버 수정           ");
		System.out.println("            5. 멤버 삭제           ");
		System.out.println("            6. 홈 메 뉴           ");
		System.out.println("=================================");
		System.out.print("=== 작업 번호를 선택>> ");
	} // end of title
	
	public void run() {
		boolean b = false;
		do {
			memberTitle();
			int key = sc.nextInt(); sc.nextLine();
			
			switch(key) {
//			case 0:
////				memberLogin();
//				break;
			case 1:
				memberSelectList();
				break;
			case 2:
				break;
			case 3:
				memberInsert();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
//				System.out.println("프로그램 종료합니다.");
				b = true;
//				sc.close();
				break;
			}
		}while(!b);
	}



	private void memberInsert() {
		MemberVO vo = new MemberVO();
		System.out.println("=========== 멤 버 등 록 ===========");
		System.out.print("회원 아이디 입력>> ");
		vo.setMemberId(sc.nextLine());
		System.out.print("회원 패스워드 입력>> ");
		vo.setMemberPassword(sc.nextLine());
		System.out.print("회원 이름 입력>> ");
		vo.setMemberName(sc.nextLine());
		System.out.print("회원 전화번호 입력>> ");
		vo.setMemberTel(sc.nextLine());
		
		// 패스워드를 암호화 해서 담기
		vo.setMemberPassword(sha256.encrypt(vo.getMemberPassword()));
		int n = dao.memberInsert(vo);
		
		if(n != 0)
			System.out.println("=== 등록 성공 ===");
		else
			System.out.println("=== 등록 실패 ===");
	}

	private void memberSelectList() {
		List<MemberVO> members = dao.memberSelectList();
		
		System.out.println("=========== 멤 버 목 록 ===========");
		System.out.println("   아이디   |   이름   |   전화번호   |   가입일자");
		
		for(MemberVO m : members) {
			System.out.print(m.getMemberId() + "  |  ");
			System.out.print(m.getMemberName() + "  |  ");
			System.out.print(m.getMemberTel() + "  |  ");
			System.out.println(m.getMemberEnterDate());
		}
	}
}	









