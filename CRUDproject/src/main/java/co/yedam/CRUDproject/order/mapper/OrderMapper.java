package co.yedam.CRUDproject.order.mapper;

import java.util.List;

import co.yedam.CRUDproject.order.service.OrderVO;

public interface OrderMapper {
	List<OrderVO> orderSelectList();	// 주문 목록 조회
	OrderVO orderSelect(OrderVO vo);	// 주문 상세 조회
	int orderInsert(OrderVO vo);		// 주문 등록
	int orderUpdate(OrderVO vo);		// 주문 수정
	int orderDelete(OrderVO vo);		// 주문 삭제
}
