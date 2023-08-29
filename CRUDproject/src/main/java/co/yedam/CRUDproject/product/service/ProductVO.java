package co.yedam.CRUDproject.product.service;

import lombok.Data;

@Data
// product value object
public class ProductVO {
	private String productId;
	private String productName;
	private String productBrand;
	private int productPrice;
	private String productContent;
	private int productCount;
	
	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", productBrand=" + productBrand
				+ ", productPrice=" + productPrice + ", productContent=" + productContent + ", productCount="
				+ productCount + "]";
	}
	
	public String showProductList() {
		return " | 제품코드: " + productId + " | 제품명: " + productName + " | 제품가격: " + productPrice + " | 제품제고: " + productCount;
	}
	
	public String showProductDetail() {
		return " | 제품코드: " + productId + " | 제품명: " + productName + " | 제품가격: " + productPrice + "\n"
				+"--------------------------------------------------" + "\n"
			+ " | 제품 브랜드: " + productBrand + " | 제품수량: " + productCount + "\n"
			+"--------------------------------------------------" + "\n"
			+ " | 제품 설명: " + productContent;
	}
	
}
