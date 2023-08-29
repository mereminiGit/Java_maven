package co.yedam.test.pucductmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.test.product.service.ProductService;
import co.yedam.test.product.service.ProductVO;
import co.yedam.test.product.serviceImpl.productServiceImpl;

public class ProductManager {	// 제품관리 메뉴
	private Scanner sc = new Scanner(System.in);
	private ProductService dao = new productServiceImpl();
	
	private void mainTitle() {
		System.out.println("===========================");
		System.out.println("===== 제   품   관   리 =====");
		System.out.println("==== 1. 제 품 전 체 조 회 ====");
		System.out.println("==== 2. 제 품 한 건 조 회 ====");
		System.out.println("==== 3. 제  품  등  록 ======");
		System.out.println("==== 4. 제  품  수  정 ======");
		System.out.println("==== 5. 제  품  삭  제 ======");
		System.out.println("==== 6. 홈 메 뉴 이 동 =====");
		System.out.println("===========================");
		System.out.println("= 작업번호를 선택 하세요.. ");
	}
	
	public void run() {
		boolean b = false;
		do {
			mainTitle();
			int jobCode = sc.nextInt(); sc.nextLine();
			
			switch(jobCode) {
			case 1:
				//제품 조회 루틴
				System.out.println("--------------------------");
				System.out.println("----- 제 품 목 록 전 체 -----");
				System.out.println("--------------------------");
				productList(); // 조회 메소드
				System.out.println("--------------------------");
				break;
			case 2:
				//제품 한건 조회 루틴
				System.out.println("--------------------------");
				System.out.println("조회할 제품코드를 입력하세요");
				String id = sc.nextLine(); 
				productSelect(id);
				break;
			case 3:
				//제품 등록 루틴
				System.out.println("------- 제 품 등 록 --------");
				productInsert();
				break;
			case 4:
				//제품 수정 루틴
				System.out.println("------- 제 품 수 정 --------");
				productUpdate();
				break;
			case 5:
				System.out.println("------- 제 품 삭 제 --------");
				productDelete();
				//제품 삭제 루틴
				break;
			case 6:
				//작업 종료 루틴
				b = true;
//				System.out.println("작업이 종료 됩니다. ");
//				sc.close();
				break;
			}
		}while(!b);
	}

	private void productDelete() {
		// 제품 삭제
		ProductVO vo = new ProductVO();
		
		System.out.println("삭제할 졔품의 코드 입력>> ");
		vo.setProductId(sc.nextLine());
		
		int n = dao.productUpdate(vo);
		if(n != 0)
			System.out.println("== 수정완료");
		else
			System.out.println("== 수정실패");
	}

	private void productUpdate() {
		// 제품 수정
		// 먼저 수정항목 선택 
		ProductVO vo = new ProductVO();
		
		System.out.println("수정할 졔품의 코드 입력>> ");
		vo.setProductId(sc.nextLine());
		subtitile();
		int key = sc.nextInt(); sc.nextLine();
		
		switch (key) {
		case 1: // 모든 항복
			System.out.println("== 제품명 입력>> ");
			vo.setProductName(sc.nextLine());
			
			System.out.println("== 제품 가격 입력>> ");
			vo.setProductPrice(Integer.parseInt(sc.nextLine()));
			
			System.out.println("== 제품 모델 입력>> ");
			vo.setProductModel(sc.nextLine());
			break;
		case 2: 
			System.out.println("== 제품명 입력>> ");
			vo.setProductName(sc.nextLine());
			break;
		case 3:
			System.out.println("== 제품 가격 입력>> "); // 디폴트 값이 프라이스에 0이 들어감 
			vo.setProductPrice(Integer.parseInt(sc.nextLine()));
			break;
		case 4:
			System.out.println("== 제품 모델 입력>> "); 
			vo.setProductModel(sc.nextLine());
			break;
		}
		
		int n = dao.productUpdate(vo);
		if(n != 0)
			System.out.println("== 수정완료");
		else
			System.out.println("== 수정실패");
	}

	private void subtitile() {
		// 제품 수정 서브 타이틀
		System.out.println("===========================");
		System.out.println("===  수정할 항목번호를 선택  ===");
		System.out.println("===========================");
		System.out.println("== 1. 모든항목===============");
		System.out.println("== 2. 제 품 명===============");
		System.out.println("== 3. 제품가격===============");
		System.out.println("== 4. 제품모델===============");
		System.out.println("===========================");
		System.out.print("수정 항목 입력>> ");
	}

	private void productInsert() {
		// 제품 등록
		ProductVO vo = new ProductVO();
		System.out.println("== 제품 코드 입력>> ");
		vo.setProductId(sc.nextLine());
		
		System.out.println("== 제품명 입력>> ");
		vo.setProductName(sc.nextLine());
		
		System.out.println("== 제품 가격 입력>> ");
		vo.setProductPrice(Integer.parseInt(sc.nextLine()));
		
		System.out.println("== 제품 모델 입력>> ");
		vo.setProductModel(sc.nextLine());
		
		int n = dao.productInsert(vo);
		if(n != 0)
			System.out.println("== 제품 등록 완료");
		else
			System.out.println("== 제품 등록 실패");
//		vo.toString();
	}

	private void productSelect(String id) {
		// 제품 하나 조회
		ProductVO vo = new ProductVO();					// vo 객체 하나 생성(id, name, price, model)
		vo.setProductId(id); // vo에 아이디 보내고			// vo(id, null,null,null)
		vo = dao.productSelect(vo); // id를 가진 vo가 처리	// 결과가 있으면 나머지 채워서 준다 / 없으면 비어있는 vo가 옴
		if(vo != null)
			vo.toString();
		else
			System.out.println("제품코드가 존재하지 않습니다.");
		
	}

	private void productList() {
		// 제품 전체 목록 조회
//		ProductService dao = new productServiceImpl();
		List<ProductVO> products = new ArrayList<ProductVO>();
		
		products = dao.productSelectList();
		for(ProductVO p : products)
			p.toString();
	}
}




















