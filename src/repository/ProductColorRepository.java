package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConnectionMgr;
import entitiy.ProductColor;

public class ProductColorRepository {

	private DBConnectionMgr pool;
	private static ProductColorRepository instance;
	
	private ProductColorRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ProductColorRepository getInstance() {
		if (instance == null) {
			instance = new ProductColorRepository();
		}
		return instance;
	}
	
	
	//메소드 호출 시 									찾고자하는 색상 언급
	public ProductColor findProuctByProductColorName(String productColorName) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductColor productColor = null;
				
		
		try {
			con = pool.getConnection();
			String sql = "select product_color_id, "
					+ "product_color_name "
					+ "from "
					+ "product_color_tb "
					+ "where "
					+ "product_color_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productColorName);
			rs=pstmt.executeQuery();
		
			
			if(rs.next()) {
				productColor = ProductColor.builder()
						.productColorId(rs.getInt(1))
						.productColorName(rs.getString(2))
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return productColor;
		// 색상값이 없으면 null로 리턴, 값이 있으면 rs.next에서 나온 값으로 리턴됨
	}
	
	public List<ProductColor> getProductColorListAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductColor> productColorList =null;
		
		try {
			con = pool.getConnection();
			
			String sql = "select product_color_id,"
					+ " product_color_name"
					+ " from "
					+ "product_color_tb "
					+ "order by "
					+ "product_color_name";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			productColorList = new ArrayList<>();
			
			while (rs.next()) {
				ProductColor productColor = ProductColor.builder()
						.productColorId(rs.getInt(1))
						.productColorName(rs.getString(2))
						.build();
				productColorList.add(productColor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return productColorList;
	}
	
		// 객체형태로 값 가져오기
	public int saveProductColor(ProductColor productColor) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			//연결
			con = pool.getConnection();
			//쿼리작성
			String sql = "insert into product_color_tb values(0,?)"; 
			pstmt = con.prepareStatement(sql);
			// 물음표 안에 있는 컬러네임 꺼내오기
			pstmt.setString(1, productColor.getProductColorName());
			successCount = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con,pstmt);
		}
		
		return successCount;
	}
	
}




