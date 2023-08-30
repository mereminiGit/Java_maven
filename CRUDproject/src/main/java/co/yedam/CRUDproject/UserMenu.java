package co.yedam.CRUDproject;

import java.util.Scanner;

import co.yedam.CRUDproject.order.controller.MypageUserController;
import co.yedam.CRUDproject.order.controller.OrderUserController;

public class UserMenu {
	private Scanner sc = new Scanner(System.in);
	OrderUserController ouc = new OrderUserController();
	MypageUserController muc = new MypageUserController();
	
	private void title() {
		System.out.println("==================================");
		System.out.println("======== Welcome customer ========");
		System.out.println("==================================");
		System.out.println("          1. 상 품  주 문           ");
		System.out.println("          2. 마 이 페 이 지          ");
		System.out.println("          3. 회 원  탈 퇴           ");
		System.out.println("          4. 로 그 아 웃            ");
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
			case 1:		// 상품주문
				ouc.run();
				break;
			case 2:		// 마이페이지
				muc.run();
				break;
			case 3:		// 회원탈퇴
				memberDelete();
				run = false;
				break;
			case 4:		// 로그아웃
				System.out.println("===== 고객 로그아웃 =====");
				run = false;
				break;
			}
		} while(run);
	}

	// 회원 탈퇴
	private void memberDelete() {
		
	}
}
