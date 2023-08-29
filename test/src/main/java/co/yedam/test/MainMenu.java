package co.yedam.test;

import java.util.Scanner;

import co.yedam.test.common.SHA256;
import co.yedam.test.menu.MemberMenu;
import co.yedam.test.pucductmenu.ProductManager;
import co.yedam.test.service.MemberService;
import co.yedam.test.service.MemberVO;
import co.yedam.test.serviceImpl.MemberServiceImpl;

public class MainMenu {
	private ProductManager pm = new ProductManager(); // 제품관리 메뉴
	private MemberMenu mm = new MemberMenu(); // 멤버관리 메뉴
	private Scanner sc = new Scanner(System.in);
	private MemberService dao = new MemberServiceImpl();

	// 메인 메뉴가 나오기전에 로그인 기능 넣어서 로그인 되면 title이 나오게 하고 로그인 안 되면 실행 안 되도록
	private void title() {
		System.out.println("=========================");
		System.out.println("=== My Home Shopping ===");
		System.out.println("=========================");
		System.out.println("      1. 멤 버 관 리     ");
		System.out.println("      2. 상 품 관 리     ");
		System.out.println("      3. 종      료     ");
		System.out.println("=== 작업을 선택>> ");
	}

	public void run() {
		boolean b = false;

		if (memberLogin()) {

			do {
				title();
				int key = sc.nextInt();
				sc.nextLine();

				switch (key) {
				case 1:
					mm.run();
					break;

				case 2:
					pm.run();
					break;

				case 3:
					System.out.println("end of prog");
					b = true;
					sc.close();
					break;
				}
			} while (!b);
		}
	}

	private boolean memberLogin() {
		SHA256 sha256 = new SHA256();
		MemberVO vo = new MemberVO();
		System.out.println("=========== 로  그  인 ===========");
		System.out.print("회원 아이디 입력>> ");
		vo.setMemberId(sc.nextLine());
		System.out.print("회원 패스워드 입력>> ");
		vo.setMemberPassword(sc.nextLine());

		vo.setMemberPassword(sha256.encrypt(vo.getMemberPassword()));

		vo = dao.memberSelect(vo);
		if (vo != null) {
			System.out.println(vo.getMemberName() + "님 환영합니다.");
			return true;
		} else {
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다.");
			System.out.println("시스템 재시작 하세요");
			return false;
		}
	}
}
