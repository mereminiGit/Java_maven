package co.yedam.CRUDproject.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.CRUDproject.product.service.ProductService;
import co.yedam.CRUDproject.product.service.ProductVO;
import co.yedam.CRUDproject.product.serviceImpl.ProductServiceImpl;

public class ProductAdminController {
	private Scanner sc = new Scanner(System.in);
	private ProductService dao = new ProductServiceImpl();
	ProductVO vo = new ProductVO();

	private void mainTitle() {
		System.out.println("=========== 관 리 자 ===========");
		System.out.println("========== 제 품 관 리 =========");
		System.out.println("======= 1. 제품 전체 조회 =======");
		System.out.println("======= 2. 제품 상세 조회 =======");
		System.out.println("=======  3. 제품 등록  =========");
		System.out.println("=======  4. 제품 수정  =========");
		System.out.println("=======  5. 제품 삭제  =========");
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
			case 1: // 제품 전체 조회
				System.out.println("==============================");
				System.out.println("--------- 제 품 목 록 ---------");
				System.out.println("------------------------------");
				productList();
				System.out.println("==================================================");
				break;

			case 2: // 제품 상세 조회
				System.out.println("==============================");
				System.out.println("--------- 제 품 조 회 ---------");
				System.out.println("------------------------------");

				String id = printString("제품코드");

				if (productSelect(id)) {
					System.out.println(vo.showProductDetail());
				} else
					System.out.println("존재하지 않는 제품입니다.");
				System.out.println("==================================================");
				break;

			case 3: // 제품 등록
				System.out.println("==============================");
				System.out.println("--------- 제 품 등 록 ---------");
				System.out.println("------------------------------");
				productInsert();
				System.out.println("==================================================");
				break;

			case 4: // 제품 수정
				System.out.println("==============================");
				System.out.println("--------- 제 품 수 정 ---------");
				System.out.println("------------------------------");
				productUpdate();
				System.out.println("==================================================");
				break;

			case 5: // 제품 삭제
				System.out.println("==============================");
				System.out.println("--------- 제 품 삭 제 ---------");
				System.out.println("------------------------------");
				productDelete();
				System.out.println("==================================================");
				break;
			case 6: // 관리자 메인화면 이동
				System.out.println("관리자 메인화면으로 이동합니다.");
				run = false;
				break;
			}

		} while (run);
	}

	// 제품 삭제
	private void productDelete() {
		vo = new ProductVO();
		String id = printString("제품코드");
		vo.setProductId(id);
		
		if (dao.productDelete(vo) != 0)
			System.out.println("삭제가 완료되었습니다.");
		else
			System.out.println("존재하지 않는 제품코드 입니다.");
	}

	// 제품 수정
	private void productUpdate() {
		vo = new ProductVO();
		String id = printString("제품코드");
		
		if(productSelect(id)) {
			UpdateSubmenu();
			
			System.out.println("수정항목 선택(중복 가능 띄어쓰기로 구분)>> ");
			String[] menu = sc.nextLine().split(" ");
			
			for(int i = 0; i < menu.length; i++) {
				switch(Integer.parseInt(menu[i])) {
				case 1:
					String name = printString("제품명(수정)");
					vo.setProductName(name);
					
					List<ProductVO> products = new ArrayList<ProductVO>();
					products = dao.productSelectList();
					
					for (ProductVO pv : products) {
						if(vo.getProductName().equals(pv.getProductName())) {
							vo.setProductName(null);
							System.out.println("이미 존재하는 제품명입니다.");
							break;
						}
					}
					
					break;
					
				case 2:
					int price = Integer.parseInt(printString("제품가격(수정)"));
					vo.setProductPrice(price);
					break;
					
				case 3:
					String brand = printString("제품 브랜드(수정)");
					vo.setProductBrand(brand);
					break;
					
				case 4:
					String content = printString("제품 설명(수정)");
					vo.setProductContent(content);
					break;
					
				case 5:
					int count = Integer.parseInt(printString("제품수량(수정)"));
					vo.setProductCount(count);
					break;
				case 6:
					System.out.println("수정을 취소합니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
			
			
			int n = dao.productUpdate(vo);
			if(n != 0)
				System.out.println("수정이 완료되었습니다.");
			else
				System.out.println("수정이 실패했습니다.");
		}else 
			System.out.println("존재하지 않는 제품코드 입니다.");
	}

	// 제품 수정 서브항목
	private void UpdateSubmenu() {
		System.out.println("================================");
		System.out.println("========== 수정항목 선택 ===========");
		System.out.println("================================");
		System.out.println("========== 1. 제 품 명 ===========");
		System.out.println("========== 2. 제품가격 ===========");
		System.out.println("========= 3. 제품 브랜드 ==========");
		System.out.println("========= 4. 제품 설명 ===========");
		System.out.println("========= 5. 제품 수량 ===========");
		System.out.println("========= 6. 취   소 ============");
	}

	// 제품 등록
	private void productInsert() {
		String id = printString("제품코드");

		if (!productSelect(id)) {
			vo = new ProductVO();
			String name = printString("제품명");
			int price = Integer.parseInt(printString("제품가격"));
			String brand = printString("제품 브랜드");
			String content = printString("제품 설명(생략 ENTER)");
			int count = Integer.parseInt(printString("제품수량"));

			vo.setProductId(id);
			vo.setProductName(name);
			vo.setProductPrice(price);
			vo.setProductBrand(brand);
			vo.setProductContent(content);
			vo.setProductCount(count);

			if (dao.productInsert(vo) == 1)
				System.out.println("제품 등록이 완료되었습니다.");
			else
				System.out.println("이미 존재하는 제품입니다.");
		} else
			System.out.println("존재하는 상품코드 입니다.");
	}

	// 제품 상세 조회
	private boolean productSelect(String id) {
		vo = new ProductVO();

		vo.setProductId(id);
		vo = dao.productSelect(vo);

		if (vo != null)
//			System.out.println(vo.showProductDetail());
			return true;
		else
			return false;

	}

	// 제품 목록
	private void productList() {
		List<ProductVO> products = new ArrayList<ProductVO>();
		products = dao.productSelectList();

		for (ProductVO pv : products)
			System.out.println(pv.showProductList());
	}

	// 입력받는 메소드
	private String printString(String msg) {
		System.out.print("[" + msg + "]" + " 입력>> ");
		return sc.nextLine();
	}

}
