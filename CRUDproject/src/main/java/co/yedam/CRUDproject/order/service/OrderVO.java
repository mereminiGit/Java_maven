package co.yedam.CRUDproject.order.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderVO {
	private String orderNo;
	private String orderName;
	private String orderTel;
	private String orderAddress;
	private LocalDate orderDate;
	private int orderCount;
	private String orderProductId;
	private String orderMemberId;
	
	private String orderProductName;
	private String orderProductEmail;
	
	public String showOrderList() {
		return " | 주문번호: " + orderNo + " | 제품코드: " + orderProductId + " | 아이디: " + orderMemberId;
	}
	
	public String showOrderDetail() {
		return " | 주문번호: " + orderNo + " | 받는사람: " + orderName + " | 전화번호: " + orderTel + "\n"
				+"--------------------------------------------------" + "\n"
			+ " | 배송주소: " + orderAddress + " | 주문일자: " + orderDate + "\n"
			+"--------------------------------------------------" + "\n"
			+ " | 제품코드: " + orderProductId + " | 제품명: " + orderProductName + " | 주문수량: " + orderCount + "\n"
			+"--------------------------------------------------" + "\n"
			+ " | 아이디: " + orderMemberId + " | 이메일: " + orderProductEmail + "\n"
			+"--------------------------------------------------" + "\n"
			+" | 결제금액: " + orderProductEmail;
	}
}
