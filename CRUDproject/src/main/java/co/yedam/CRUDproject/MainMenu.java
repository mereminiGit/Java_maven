package co.yedam.CRUDproject;

import java.util.Scanner;

import co.yedam.CRUDproject.common.SendMail;
import co.yedam.CRUDproject.member.controller.MemberAdminController;
import co.yedam.CRUDproject.member.service.MemberService;
import co.yedam.CRUDproject.member.service.MemberVO;
import co.yedam.CRUDproject.member.serviceImpl.MemberServiceImpl;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memberDao = new MemberServiceImpl();
//	MemberVO memberVO = new MemberVO();

	AdminMenu am = new AdminMenu();
	UserMenu um = new UserMenu();
	
	public String mainId;
	public int adminCheck;

	private void title() {
		System.out.println("==================================");
		System.out.println("========= Shoes Shopping =========");
		System.out.println("==================================");
		System.out.println("          1. 로   그   인           ");
		System.out.println("          2. 아 이 디 찾 기          ");
		System.out.println("          3. 비 밀 번 호 찾 기        ");
		System.out.println("          4. 회  원  가  입          ");
		System.out.println("          5. 종      료             ");
		System.out.println("==================================");
		System.out.print("작업번호를 선택하세요>> ");
	}

	public void run() {
		boolean run = true;

		do {
			title();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1: // 로그인
				if (memberLogin()) {
					if (adminCheck == 1) { // 관리자로 로그인 시
						am.run();
					} else { // 사용자로 로그인 시
						um.run();
					}
				}
				break;
			case 2: // 아이디 찾기
				findId();
				break;
			case 3: // 비밀번호 찾기
				findPassword();
				break;
			case 4: // 회원가입
				createMember();
				break;
			case 5: // 종료
				System.out.println("===== 쇼핑몰 종료 =====");
				run = false;
				sc.close();
				break;
			}

		} while (run);
	}

	// 회원가입
	private void createMember() {
		String id = printString("아이디");
		MemberAdminController mac = new MemberAdminController();
		MemberVO memberVO = new MemberVO();
		
		if (!mac.memberSelect(id)) {
			String pw = printString("비밀번호");
			String name = printString("이름");
			String email = printString("이메일");
			String tel = printString("전화번호");
			int check = 0;
//			MemberVO vo = new MemberVO();

			// 입력값 vo객체에 넣기
			memberVO.setMemberId(id);
			memberVO.setMemberPassword(pw);
			memberVO.setMemberName(name);
			memberVO.setMemberEmail(email);
			memberVO.setMemberTel(tel);
			memberVO.setMemberCheck(check);

			if (memberDao.memberInsert(memberVO) == 1)
				System.out.println("사용자가 등록되었습니다.");
			else
				System.out.println("이미 존재하는 이메일입니다.");
		} else
			System.out.println("존재하는 아이디입니다.");
	}

	// 비민번호 찾기 (아이디, 이메일 입력 시 해당 사용자의 이메일로 초기화 비번 12345 발송)
	private void findPassword() {
		MemberVO memberVO = new MemberVO();
		System.out.println("============ 비밀번호 찾기 ============");
		System.out.print("회원 아이디 입력>> ");
		memberVO.setMemberId(sc.nextLine());
		System.out.print("회원 이메일 입력>> ");
		memberVO.setMemberEmail(sc.nextLine());

		memberVO = memberDao.memberSelectEmail(memberVO);
		if (memberVO != null) {
			MemberVO memberVOtmp = new MemberVO();
			memberVOtmp.setMemberId(memberVO.getMemberId());
			memberVOtmp.setMemberPassword("12345");
			memberDao.memberUpdate(memberVOtmp);

			String to = memberVO.getMemberEmail();

			String title = "비밀번호 초기화";
			StringBuilder sb = new StringBuilder();
			sb.append("<h2> 비밀번호 초기화 메일입니다.</h2>");
			sb.append("<h3>" + "비밀번호 : " + memberVOtmp.getMemberPassword() + "</h3>");
			sb.append("<h3>초기화된 비밀번호로 로그인하세요.</h3>");

			String content = sb.toString();

			SendMail sendMail = new SendMail();
			sendMail.sendMail(to, title, content);
		} else
			System.out.println("가입되지 않은 회원입니다.");
	}

	// 아이디 찾기 (이름, 이메일 입력 시 아이디 메일로 발송)
	private void findId() {
		MemberVO memberVO = new MemberVO();
		System.out.println("============ 아이디 찾기 ============");
		System.out.print("회원 이름 입력>> ");
		memberVO.setMemberName(sc.nextLine());
		System.out.print("회원 이메일 입력>> ");
		memberVO.setMemberEmail(sc.nextLine());

		memberVO = memberDao.memberSelectEmail(memberVO); // 디비에 있는지
		if (memberVO != null) {
			String to = memberVO.getMemberEmail();

			String title = "아이디 찾기";
			StringBuilder sb = new StringBuilder();
			sb.append("<h2> 아이디 찾기 메일입니다.</h2>");
			sb.append("<h3>" + "아이디 : " + memberVO.getMemberId() + "</h3>");
			sb.append("<h3>해당 아이디로 로그인하세요.</h3>");

			String content = sb.toString();

			SendMail sendMail = new SendMail();
			sendMail.sendMail(to, title, content);
		} else
			System.out.println("가입되지 않은 회원입니다.");
	}

	// 로그인 기능
	private boolean memberLogin() {
		MemberVO memberVO = new MemberVO();
		System.out.println("============ 로  그  인 ============");
		System.out.print("회원 아이디 입력>> ");
		memberVO.setMemberId(sc.nextLine());
		System.out.print("회원 패스워드 입력>> ");
		memberVO.setMemberPassword(sc.nextLine());

		memberVO = memberDao.memberSelect(memberVO);
		if (memberVO != null) {
			System.out.println(memberVO.getMemberName() + "님 환영합니다.");
			mainId = memberVO.getMemberId();
			adminCheck = memberVO.getMemberCheck();
			return true;
		} else {
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다.");
//			System.out.println("시스템 재시작 하세요");
			return false;
		}
	}

	// 입력받는 메소드
	private String printString(String msg) {
		System.out.print("[" + msg + "]" + " 입력>> ");
		return sc.nextLine();
	}

}
