package service;

import java.util.ArrayList;
import java.util.List;

import entitiy.ProductCategory;
import repository.ProductCategoryRepository;

public class ProductCategoryService {

	private ProductCategoryRepository productCategoryRepository;
	private static ProductCategoryService instance;
	
	private ProductCategoryService() {
		productCategoryRepository = productCategoryRepository.getInstance();
	}
	
	public static ProductCategoryService getInstance() {
		if (instance == null) {
			instance = new ProductCategoryService();
		}
		return instance;
	}
	
	public List<String> getProductCategoryNameList(){
		List<String> productCategoryNameList = new ArrayList<>();
		System.out.println("출력");
	
		productCategoryRepository.getProductCategoryListAll().forEach(productCategory ->{
			productCategoryNameList.add(productCategory.getProductCategoryName());
		});
		
		return productCategoryNameList;
		
		
		
	}
	
	public boolean isProductCategoryNameDuplicated(String productCategoryName) {
		boolean result =false;
		
		result = productCategoryRepository.findProductCategoryByProductCategoryName(productCategoryName) != null;
		return result;
	}
	
	public boolean registerProductCategory(ProductCategory productCategory) {
		boolean result = false;
		
		result = productCategoryRepository.saveProductCategory(productCategory)>0;
		
		return result;
	}
}
