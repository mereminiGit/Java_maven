package co.yedam.CRUDproject.product.service;

import java.util.List;

public interface ProductService {
	List<ProductVO> productSelectList();	// 제품 전체 조회
	ProductVO productSelect(ProductVO vo);	// 제품 상세 조회
	int productInsert(ProductVO vo);		// 제품 등록
	int productUpdate(ProductVO vo);		// 제품 수정
	int productDelete(ProductVO vo);		// 제품 삭제
}
