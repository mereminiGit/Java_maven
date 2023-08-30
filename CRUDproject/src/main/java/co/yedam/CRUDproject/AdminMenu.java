package co.yedam.CRUDproject;

import java.util.Scanner;

import co.yedam.CRUDproject.member.controller.MemberAdminController;
import co.yedam.CRUDproject.order.controller.OrderAdminController;
import co.yedam.CRUDproject.product.controller.ProductAdminController;

public class AdminMenu {
	private Scanner sc = new Scanner(System.in);
	MemberAdminController mac = new MemberAdminController();
	OrderAdminController oac = new OrderAdminController();
	ProductAdminController pac = new ProductAdminController();
	
	private void title() {
		System.out.println("==================================");
		System.out.println("========= Welcome  admin =========");
		System.out.println("==================================");
		System.out.println("         1. 사 용 자  관 리          ");
		System.out.println("         2. 상 품  관 리            ");
		System.out.println("         3. 주 문  관 리            ");
		System.out.println("         4. 로  그  아  웃          ");
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
			case 1:		// 사용자관리
				mac.run();
				break;
			case 2:		// 상품관리
				pac.run();
				break;
			case 3:		// 주문관리
				oac.run();
				break;
			case 4:		// 로그아웃
				System.out.println("===== 관리자 로그아웃 =====");
				run = false;
				break;
			}
		} while(run);
	}
}
