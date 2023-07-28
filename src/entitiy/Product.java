package entitiy;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
// 상품의 객체를 담는 클래스로 사용
public class Product {
	private int productId;
	private String productName;
	private int productPrice;
	private int productColorId;
	private int productCategoryId;
	
	private ProductColor productColor;
	private ProductCategory productCategory;
	
}
