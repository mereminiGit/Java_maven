package co.yedam.CRUDproject.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.CRUDproject.order.service.OrderService;
import co.yedam.CRUDproject.order.service.OrderVO;
import co.yedam.CRUDproject.order.serviceImpl.OrderServiceImpl;
import co.yedam.CRUDproject.product.service.ProductService;
import co.yedam.CRUDproject.product.service.ProductVO;
import co.yedam.CRUDproject.product.serviceImpl.ProductServiceImpl;

public class OrderAdminController {
	private Scanner sc = new Scanner(System.in);
	private OrderService dao = new OrderServiceImpl();
	OrderVO vo = new OrderVO();
	
	private void mainTitle() {
		System.out.println("=========== 관 리 자 ===========");
		System.out.println("========== 주 문 관 리 =========");
		System.out.println("======= 1. 주문 목록 조회 ========");
		System.out.println("======= 2. 주문 상세 조회 ========");
		System.out.println("======= 3. 제품 주문 수정 ========");
		System.out.println("======= 4. 제품 주문 삭제 ========");
		System.out.println("====== 5. 관리자 메인화면 이동 =====");
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
			case 1:		// 주문 목록 조회
				System.out.println("==============================");
				System.out.println("--------- 주 문 목 록 ---------");
				System.out.println("------------------------------");
				orderList();
				System.out.println("==================================================");
				break;
				
			case 2:		// 주문 상세 조회
				System.out.println("==============================");
				System.out.println("--------- 제 품 조 회 ---------");
				System.out.println("------------------------------");

				String no = printString("주문번호");

				if (orderSelect(no)) {
					System.out.println(vo.showOrderDetail());
				} else
					System.out.println("존재하지 않는 제품입니다.");
				System.out.println("==================================================");
				break;
				
			case 3:		// 제품 주문 수정
				System.out.println("==============================");
				System.out.println("--------- 제 품 수 정 ---------");
				System.out.println("------------------------------");
				orderUpdate();
				System.out.println("==================================================");
				break;
				
			case 4:		// 제품 주문 삭제
				System.out.println("==============================");
				System.out.println("--------- 제 품 삭 제 ---------");
				System.out.println("------------------------------");
				orderDelete();
				System.out.println("==================================================");
				break;
				
			case 5:		// 관리자 메인화면 이동
				System.out.println("관리자 메인화면으로 이동합니다.");
				run = false;
				break;
			}
		} while(run);
	} // end of run
	
	// 주문 삭제
	private void orderDelete() {
		vo = new OrderVO();
		String no = printString("주문번호");
		vo.setOrderNo(no);
		
		if(dao.orderDelete(vo) != 0)
			System.out.println("삭제가 완료되었습니다.");
		else
			System.out.println("존재하지 않는 주문번호 입니다.");			
	}

	// 주문 수정
	private void orderUpdate() {
		vo = new OrderVO();
		ProductVO Pvo = new ProductVO();
		ProductService Pdao = new ProductServiceImpl();
		
		String no = printString("주문번호");
		
		if(orderSelect(no)) {
			updateSubmenu();
			
			System.out.println("수정항목 선택(중복 가능 띄어쓰기로 구분)>> ");
			String[] menu = sc.nextLine().split(" ");
			
			for(int i = 0; i < menu.length; i++) {
				switch(Integer.parseInt(menu[i])) {
				case 1:
					String name = printString("받는사람(수정)");
					vo.setOrderName(name);
					break;
				case 2:
					String tel = printString("전화번호(수정)");
					vo.setOrderTel(tel);
					break;
				case 3:
					String add = printString("배송주소");
					vo.setOrderAddress(add);
					break;
				case 4:
					int count = Integer.parseInt(printString("주문수량"));
					vo.setOrderCount(count);
					break;
				case 5:
					System.out.println("수정을 취소합니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
			
			int n = dao.orderUpdate(vo);
			if(n != 0) {
				System.out.println("수정이 완료되었습니다.");
				
				
			}
			else
				System.out.println("수정이 실패했습니다.");
		} else 
			System.out.println("존재하지 않는 주문번호 입니다.");
	}
	
	private void updateSubmenu() {
		System.out.println("================================");
		System.out.println("========== 수정항목 선택 ===========");
		System.out.println("================================");
		System.out.println("========== 1. 받는사람 ===========");
		System.out.println("========== 2. 전화번호 ===========");
		System.out.println("========== 3. 배송주소 ===========");
		System.out.println("========== 4. 주문수량 ===========");
		System.out.println("========== 5. 취  소 ============");
	}

	// 주문 상세 조회
	private boolean orderSelect(String no) {
		vo = new OrderVO();
		vo.setOrderNo(no);
		
		vo = dao.orderSelect(vo);
		
		if(vo != null)
			return true;
		else
			return false;
	}

	// 주문 목록
	private void orderList() {
		List<OrderVO> orders = new ArrayList<OrderVO>();
		orders = dao.orderSelectList();
		
		for(OrderVO vo : orders)
			System.out.println(vo.showOrderList());
	}

	// 입력받는 메소드
	private String printString(String msg) {
		System.out.print("[" + msg + "]" + " 입력>> ");
		return sc.nextLine();
	}
}
























