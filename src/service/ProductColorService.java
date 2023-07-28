package service;

import java.util.ArrayList;
import java.util.List;

import entitiy.ProductColor;
import repository.ProductColorRepository;

public class ProductColorService {

	private ProductColorRepository productColorRepository;
	private static ProductColorService instance;
	
	private ProductColorService() {
		productColorRepository = productColorRepository.getInstance();
	}
	
	public static ProductColorService getInstance() {
		if (instance == null) {
			instance = new ProductColorService();
		} 
		return instance;
	}
	
	
	public List<String> getProductColorNameList(){
		List<String> productColorNameList = new ArrayList<>();
		
		// 재사용때문에 이렇게 쓴다......? .....?  무슨말이징 
		productColorRepository.getProductColorListAll().forEach(productColor ->{
			productColorNameList.add(productColor.getProductColorName());
		});
		
		return productColorNameList;
	}
	
	//중복확인
	public boolean isProductColorNameDuplicated(String productColorName) {
		boolean result = false;
		
		// 값이 들어왔을때 null인지 확인함 , 중복이 되었을때 true
		result = productColorRepository.findProuctByProductColorName(productColorName) != null;
		return result;
	}
	
	//
	public boolean registerProductColor(ProductColor productColor) {
		boolean result = false;
		
		// 정상적으로 들어갔단 것은 0보다 크다는것...? 
		result = productColorRepository.saveProductColor(productColor)>0;
		
		return result;
	}

	
}
