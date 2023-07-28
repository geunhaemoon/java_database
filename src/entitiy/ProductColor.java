package entitiy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductColor {
	private int productColorId;
	private String productColorName;
}
