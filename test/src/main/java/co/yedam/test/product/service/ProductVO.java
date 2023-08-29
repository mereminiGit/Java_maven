package co.yedam.test.product.service;

import lombok.Data;

@Data
public class ProductVO {
	// table칼럼과 vo(value object)객체를 일치 시켜야함
	// table칼럼명은 스네이크 케이스, 자바는 카멜케이스
	private String productId;
	private String productName;
	private int productPrice;
	private String productModel;

	public String toString() {
		System.out.print(productId + " : ");
		System.out.print(productName + " : ");
		System.out.print(productPrice + " : ");
		System.out.println(productModel + " : ");
		return null;
	}
}
