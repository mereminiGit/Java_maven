package co.yedam.CRUDproject.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.CRUDproject.MainMenu;
import co.yedam.CRUDproject.member.controller.MemberAdminController;
import co.yedam.CRUDproject.member.service.MemberService;
import co.yedam.CRUDproject.member.service.MemberVO;
import co.yedam.CRUDproject.member.serviceImpl.MemberServiceImpl;
import co.yedam.CRUDproject.order.service.OrderService;
import co.yedam.CRUDproject.order.service.OrderVO;
import co.yedam.CRUDproject.order.serviceImpl.OrderServiceImpl;
import co.yedam.CRUDproject.product.controller.ProductAdminController;
import co.yedam.CRUDproject.product.service.ProductVO;

public class MypageUserController {
	private Scanner sc = new Scanner(System.in);
	private OrderService orderDao = new OrderServiceImpl();
	OrderVO orderVO = new OrderVO();

	private MemberService memberDao = new MemberServiceImpl();
	MemberVO memberVO = new MemberVO();
	
	MainMenu mm = new MainMenu();
	
	private void mainTitle() {
		System.out.println("=========== 고    객 ===========");
		System.out.println("========= 마 이 페 이 지 =========");
		System.out.println("======= 1. 주문 목록 조회 ========");
		System.out.println("======= 2. 주문 상세 조회 ========");
		System.out.println("======= 3. 제품 주문 수정 ========");
		System.out.println("======= 4. 제품 주문 취소 ========");
		System.out.println("======= 5. 사용자 계정 조회 =======");
		System.out.println("======= 6. 사용자 계정 수정 =======");
		System.out.println("====== 7. 고객 메인화면 이동 ======");
		System.out.println("-------------------------------");
		System.out.print("작업번호를 선택하세요>> ");
	}

	public void run() {
		boolean run = true;

		do {
			mainTitle();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1: // 주문 목록 조회
				System.out.println("==============================");
				System.out.println("--------- 주문 목록 조회 ---------");
				System.out.println("------------------------------");
				userorderList();
				System.out.println("==================================================");
				break;

			case 2: // 주문 상세 조회
				System.out.println("==============================");
				System.out.println("--------- 주문 상세 조회 ---------");
				System.out.println("------------------------------");

				String no = printString("주문번호");

				if (userorderSelete(no)) {
					// 제품명, 이메일, 결제금액

					String id = orderVO.getProductId();
					ProductAdminController Pcontrol = new ProductAdminController();
					ProductVO Pvo = new ProductVO();

					Pvo = Pcontrol.productSelectVO(id);

					orderVO.setOrderProductName(Pvo.getProductName());

					// 이메일
					String memberId = orderVO.getMemberId();
					MemberAdminController Mcontrol = new MemberAdminController();
					MemberVO Mvo = new MemberVO();

					Mvo = Mcontrol.memberSelectVO(memberId);

					orderVO.setOrderMemberEmail(Mvo.getMemberName());

					// 결제금액
					int productPrice = Pvo.getProductPrice();
					orderVO.setOrederTotal(productPrice * orderVO.getOrderCount());

					System.out.println(orderVO.showOrderDetail());

				} else
					System.out.println("존재하지 않는 주문번호 입니다.");
				System.out.println("==================================================");
				break;

			case 3: // 제품 주문 수정
				System.out.println("==============================");
				System.out.println("--------- 제품 주문 수정 ---------");
				System.out.println("------------------------------");
				userorderUpdate();
				System.out.println("==================================================");
				break;

			case 4: // 제품 주문 취소
				System.out.println("==============================");
				System.out.println("--------- 제품 주문 취소 ---------");
				System.out.println("------------------------------");
				userorderDelete();
				System.out.println("==================================================");
				break;

			case 5: // 사용자 계정 조회
				System.out.println("==============================");
				System.out.println("-------- 사용자 계정 조회 ---------");
				System.out.println("------------------------------");
				usermemberSelect(mm.mainId);	// 로그인한 사용자 넘겨주기
				System.out.println("==================================================");
				break;

			case 6: // 사용자 계정 수정
				System.out.println("==============================");
				System.out.println("-------- 사용자 계정 수정 ---------");
				System.out.println("------------------------------");
				usermemberUpdate(mm.mainId);	// 로그인한 사용자 넘겨주기
				System.out.println("==================================================");
				break;

			case 7: // 고객 메인화면 이동
				System.out.println("고객 메인화면으로 이동합니다.");
				run = false;
				break;
			}
		} while (run);
	}

	// 사용자 계정 수정
	private void usermemberUpdate(String id) {
		MemberAdminController mac = new MemberAdminController();
		memberVO = new MemberVO();
		mac.memberUpdateUser(id);
	}

	// 사용자 계정 조회
	private void usermemberSelect(String id) {
		memberVO = new MemberVO();
		memberVO.setMemberId(id);
		memberVO = memberDao.memberSelect(memberVO);
		
		if (memberVO != null)
			System.out.println(memberVO.showMemberDetail());
		else
			System.out.println("시스템 오류..");
	}

	// 제품 주문 취소
	private void userorderDelete() {
		orderVO = new OrderVO();
		String no = printString("주문번호");
		
		if (userorderSelete(no)) {
			OrderAdminController oac = new OrderAdminController();
			oac.orderDeleteUser(no);
		} else 
			System.out.println("존재하지 않는 주문번호 입니다.");
	}

	// 제품 주문 수정
	private void userorderUpdate() {
		orderVO = new OrderVO();
		String no = printString("주문번호");
		if (userorderSelete(no)) {
			OrderAdminController oac = new OrderAdminController();
			oac.orderUpdateUser(no);
		} else 
			System.out.println("존재하지 않는 주문번호 입니다.");
		
	}

	// 주문 상세 조회
	private boolean userorderSelete(String no) {
		orderVO = new OrderVO();
		orderVO.setOrderNo(no);

		orderVO = orderDao.orderSelect(orderVO);

		if (orderVO != null && orderVO.getMemberId().equals(mm.mainId))  //  로그인 한 사용자
			return true;
		else
			return false;
	}

	// 주문 목록 조회
	private void userorderList() {
		boolean tmp = false;
		List<OrderVO> orders = new ArrayList<OrderVO>();
		orders = orderDao.orderSelectList();

		for (OrderVO vo : orders) {
			if (vo.getMemberId().equals(mm.mainId)) {  //  로그인 한 사용자
				System.out.println(vo.showOrderList());
				tmp = true;
			}
		}

		if (!tmp) {
			System.out.println("주문 목록이 없습니다.");
		}

	}

	// 입력받는 메소드
	private String printString(String msg) {
		System.out.print("[" + msg + "]" + " 입력>> ");
		return sc.nextLine();
	}
}
