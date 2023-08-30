package co.yedam.CRUDproject.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.CRUDproject.order.service.OrderService;
import co.yedam.CRUDproject.order.service.OrderVO;
import co.yedam.CRUDproject.order.serviceImpl.OrderServiceImpl;
import co.yedam.CRUDproject.product.controller.ProductAdminController;
import co.yedam.CRUDproject.product.service.ProductService;
import co.yedam.CRUDproject.product.service.ProductVO;
import co.yedam.CRUDproject.product.serviceImpl.ProductServiceImpl;

public class OrderUserController {
	private Scanner sc = new Scanner(System.in);
	private OrderService orderDao = new OrderServiceImpl();
	OrderVO orderVO = new OrderVO();
	
	private ProductService productDao = new ProductServiceImpl();
	ProductVO productVO = new ProductVO();
	
	private void mainTitle() {
		System.out.println("=========== 고    객 ===========");
		System.out.println("========== 상 품  주 문 =========");
		System.out.println("======= 1. 제품 목록 조회 ========");
		System.out.println("======= 2. 제품 상세 조회 ========");
		System.out.println("======= 3. 제품 주문 신청 ========");
		System.out.println("====== 4. 고객 메인화면 이동 ======");
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
			case 1:		// 제품 목록 조회
				System.out.println("==============================");
				System.out.println("--------- 제 품 목 록 ---------");
				System.out.println("------------------------------");
				productList();
				System.out.println("==================================================");
				break;
				
			case 2:		// 제품 상세 조회
				System.out.println("==============================");
				System.out.println("--------- 제 품 조 회 ---------");
				System.out.println("------------------------------");
				
				String id = printString("제품코드");

				if (productSelete(id)) {
					System.out.println(productVO.showProductDetail());
				} else
					System.out.println("존재하지 않는 제품입니다.");
				System.out.println("==================================================");
				break;
				
			case 3:		// 제품 주문 신청
				System.out.println("==============================");
				System.out.println("--------- 제 품 주 문 ---------");
				System.out.println("------------------------------");
				productOrderInsert();
				System.out.println("==================================================");
				break;
				
			case 4:		// 고객 메인화면 이동
				System.out.println("고객 메인화면으로 이동합니다.");
				run = false;
				break;
			}
		} while(run);
	}

	// 제품 주문 신청
	private void productOrderInsert() {
		String name = printString("주문자");
		String tel = printString("전화번호");
		String address = printString("배송주소");
		String productId = printString("제품코드");
		int count = Integer.parseInt(printString("주문수량"));
		
		orderVO.setOrderName(name);
		orderVO.setOrderTel(tel);
		orderVO.setOrderAddress(address);
		orderVO.setOrderCount(count);
		orderVO.setProductId(productId);
		orderVO.setMemberId("test03");
		
		if (orderDao.orderInsert(orderVO) == 1) {
			ProductAdminController Pcontrol = new ProductAdminController();
			productVO = new ProductVO();
			String id = orderVO.getProductId();
			
			productVO = Pcontrol.productSelectVO(id);
			int productNo = productVO.getProductCount() - count;
			
			Pcontrol.productUpdateCount(productNo, id);
			
			System.out.println("제품 주문이 완료되었습니다.");
		}
		else
			System.out.println("제품 주문이 취소되었습니다.");
	}

	// 제품 상세 조회
	private boolean productSelete(String id) {
		productVO = new ProductVO();

		productVO.setProductId(id);
		productVO = productDao.productSelect(productVO);

		if (productVO != null)
			return true;
		else
			return false;
	}

	// 제품 목록 조회
	private void productList() {
		List<ProductVO> products = new ArrayList<ProductVO>();
		products = productDao.productSelectList();

		for (ProductVO pv : products)
			System.out.println(pv.showProductList());
	}
	
	// 입력받는 메소드
		private String printString(String msg) {
			System.out.print("[" + msg + "]" + " 입력>> ");
			return sc.nextLine();
		}
}
