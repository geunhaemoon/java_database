package entitiy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategory {
	private int productCategoryId;
	private String productCategoryName;
	
}
