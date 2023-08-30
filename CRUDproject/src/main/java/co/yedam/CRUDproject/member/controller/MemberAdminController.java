package co.yedam.CRUDproject.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.CRUDproject.member.service.MemberService;
import co.yedam.CRUDproject.member.service.MemberVO;
import co.yedam.CRUDproject.member.serviceImpl.MemberServiceImpl;

// 사용자 관리 / 로그인 
public class MemberAdminController {
	private Scanner sc = new Scanner(System.in);
	private MemberService dao = new MemberServiceImpl();
	MemberVO vo = new MemberVO();

	private void mainTitle() {
		System.out.println("=========== 관 리 자 ===========");
		System.out.println("========= 사 용 자 관 리 ========");
		System.out.println("======= 1. 사용자 전체 조회 ======");
		System.out.println("======= 2. 사용자 상세 조회 ======");
		System.out.println("======= 3. 사용자 등록 ==========");
		System.out.println("======= 4. 사용자 수정 ==========");
		System.out.println("======= 5. 사용자 삭제 ==========");
		System.out.println("===== 6. 관리자 메인화면 이동 =====");
		System.out.println("------------------------------");
		System.out.print("작업번호를 선택하세요>> ");
	}

	public void run() {
		boolean run = true;

		do {
			mainTitle();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				// 사용자 전체 조회
				System.out.println("==============================");
				System.out.println("-------- 사 용 자 목 록 ---------");
				System.out.println("------------------------------");
				memberList();
				System.out.println("==================================================");

				break;
			case 2:
				// 사용자 상세 조회
				System.out.println("==============================");
				System.out.println("-------- 사 용 자 조 회 ---------");
				System.out.println("------------------------------");
				System.out.print("조회 할 사용자 아이디 입력하세요>> ");
				String id = sc.nextLine();
				memberSelect(id);
				System.out.println("--------------------------------------------------");
				if (vo != null)
					System.out.println(vo.showMemberDetail());
				else
					System.out.println("존재하지 않는 사용자입니다.");
				System.out.println("==================================================");
				break;
			case 3:
				// 사용자 등록
				System.out.println("==============================");
				System.out.println("-------- 사 용 자 등 록 ---------");
				System.out.println("------------------------------");
				memberInsert();
				System.out.println("==================================================");
				break;
			case 4:
				// 사용자 수정
				System.out.println("==============================");
				System.out.println("-------- 사 용 자 수 정 ---------");
				System.out.println("------------------------------");
				memberUpdate();
				System.out.println("==================================================");
				break;
			case 5:
				// 사용자 삭제
				System.out.println("==============================");
				System.out.println("-------- 사 용 자 삭 제 ---------");
				System.out.println("------------------------------");
				memberDelete();
				System.out.println("==================================================");				
				break;
			case 6:
				// 관리자 페이지로 이동
				System.out.println("관리자 메인화면으로 이동합니다.");
				run = false;
				break;
			}
		} while (run);
	}

	// 사용자 삭제
	private void memberDelete() {
		vo = new MemberVO();
		String id = printString("삭제 할 아이디");
		vo.setMemberId(id);
		
		int n = dao.memberDelete(vo);
		if(n != 0)
			System.out.println("삭제가 완료되었습니다.");
		else
			System.out.println("존재하지 않는 아이디 입니다.");
	}

	// 사용자 수정
	private void memberUpdate() {
		vo = new MemberVO();
		String id = printString("수정 할 아이디");
		if (memberSelect(id)) {
			UpdateSubmenu();
			vo.setMemberId(id);
			
			System.out.print("수정항목 입력(중복 가능 띄어쓰기로 구분)>> ");
			String[] menu = sc.nextLine().split(" ");

			for (int i = 0; i < menu.length; i++) {
				switch (Integer.parseInt(menu[i])) {
				case 1: // 패스워드 수정
					String pw = printString("패스워드(수정)");
					vo.setMemberPassword(pw);
//					System.out.println(vo.getMemberPassword());
					break;
				case 2: // 이름 수정
					String name = printString("이름(수정)");
					vo.setMemberName(name);
					
					break;
				case 3: // 전화번호 수정
					String tel = printString("전화번호(수정)");
					vo.setMemberTel(tel);
					break;
				case 4: // 관리자 여부 수정
					int check = Integer.parseInt(printString("관리자 권한(수정)"));
					vo.setMemberCheck(check);
					break;
				case 5:
					System.out.println("수정을 취소합니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
			
//			System.out.println(vo.showMemberDetail());
			
			int n = dao.memberUpdate(vo);
			if(n != 0)
				System.out.println("수정이 완료되었습니다.");
			else
				System.out.println("수정을 실패했습니다.");

		} else
			System.out.println("존재하지 않는 아이디입니다.");
	}
	
	// 사용자 수정 외부 참조 (고객용)
	public void memberUpdateUser(String id) {
		vo = new MemberVO();
		if (memberSelect(id)) {
			UpdateSubmenuUser();
			vo.setMemberId(id);
			
			System.out.print("수정항목 입력(중복 가능 띄어쓰기로 구분)>> ");
			String[] menu = sc.nextLine().split(" ");
			
			for (int i = 0; i < menu.length; i++) {
				switch (Integer.parseInt(menu[i])) {
				case 1: // 패스워드 수정
					String pw = printString("패스워드(수정)");
					vo.setMemberPassword(pw);
//					System.out.println(vo.getMemberPassword());
					break;
				case 2: // 이름 수정
					String name = printString("이름(수정)");
					vo.setMemberName(name);
					
					break;
				case 3: // 전화번호 수정
					String tel = printString("전화번호(수정)");
					vo.setMemberTel(tel);
					break;
				case 4:
					System.out.println("수정을 취소합니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
			
//			System.out.println(vo.showMemberDetail());
			
			int n = dao.memberUpdate(vo);
			if(n != 0)
				System.out.println("수정이 완료되었습니다.");
			else
				System.out.println("수정을 실패했습니다.");
			
		} else
			System.out.println("존재하지 않는 아이디입니다.");
	}

	// 사용자 수정 서브항목
	private void UpdateSubmenu() {
		System.out.println("================================");
		System.out.println("========== 수정항목 선택 ===========");
		System.out.println("================================");
		System.out.println("========== 1. 패스워드 ===========");
		System.out.println("========== 2. 이   름 ===========");
		System.out.println("========== 3. 전화번호 ===========");
		System.out.println("== 4. 관리자 권한 (0 - X, 1 - O) ==");
		System.out.println("========== 5. 취   소 ===========");
	}
	
	// 사용자 수정 서브항목 (고객용)
	private void UpdateSubmenuUser() {
		System.out.println("================================");
		System.out.println("========== 수정항목 선택 ===========");
		System.out.println("================================");
		System.out.println("========== 1. 패스워드 ===========");
		System.out.println("========== 2. 이   름 ===========");
		System.out.println("========== 3. 전화번호 ===========");
		System.out.println("========== 4. 취   소 ===========");
	}

	// 사용자 등록
	private void memberInsert() {
		String id = printString("아이디");

		// id가 존재하는지 확인
		if (!memberSelect(id)) {
			vo = new MemberVO();
			String pw = printString("비밀번호");
			String name = printString("이름");
			String email = printString("이메일");
			String tel = printString("전화번호");
			int check = Integer.parseInt(printString("관리자 여부(0-관리자 X, 1-관리자 O)"));
//			MemberVO vo = new MemberVO();

			// 입력값 vo객체에 넣기
			vo.setMemberId(id);
			vo.setMemberPassword(pw);
			vo.setMemberName(name);
			vo.setMemberEmail(email);
			vo.setMemberTel(tel);
			vo.setMemberCheck(check);

			if (dao.memberInsert(vo) == 1)
				System.out.println("사용자가 등록되었습니다.");
			else
				System.out.println("이미 존재하는 이메일입니다.");
		} else
			System.out.println("존재하는 아이디입니다.");
	}

	// 사용자 상세 조회
	public boolean memberSelect(String id) {
		vo = new MemberVO();
		vo.setMemberId(id);
		vo = dao.memberSelect(vo);

//		System.out.println("--------------------------------------------------");
		if (vo != null)
//			System.out.println(vo.showMemberDetail());
			return true;
		else
//			System.out.println("존재하지 않는 사용자입니다.");
			return false;
//		System.out.println("--------------------------------------------------");
	}
	
	// 사용자 상제 조회 vo객체 리턴
	public MemberVO memberSelectVO(String id) {
		vo = new MemberVO();
		vo.setMemberId(id);
		vo = dao.memberSelect(vo);

		if (vo != null)
			return vo;
		else
			return null;
	}

	// 사용자 전체 조회
	private void memberList() {
		List<MemberVO> members = new ArrayList<>();

		members = dao.memberSelectList();
		for (MemberVO m : members)
			System.out.println(m.showMemberList());
	}

	// 입력받는 메소드
	private String printString(String msg) {
		System.out.print("[" + msg + "]" + " 입력>> ");
		return sc.nextLine();
	}
}
