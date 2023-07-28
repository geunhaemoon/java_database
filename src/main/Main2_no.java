package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConnectionMgr;

public class Main2_no {
	public static void main(String[] args) {

		DBConnectionMgr pool = DBConnectionMgr.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DBConnectionMgr.getInstance().getConnection();

			String sql = "select *from study3.prouct_tb";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			System.out.println("product_code\t|\tproduct_name");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionMgr.getInstance().freeConnection(con, pstmt, rs);
		}

		try {
			con = pool.getConnection();
			String sql = "insert into user_tb values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "2023006");
			pstmt.setString(2, "상품6");

			int successCount = pstmt.executeUpdate();
			System.out.println("insert 성공 횟수: " + successCount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

		try {

			con = DBConnectionMgr.getInstance().getConnection();

			String sql = "select *from study3.prouct_tb";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			System.out.println("product_code\t|\tproduct_name");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionMgr.getInstance().freeConnection(con, pstmst, rs);
		}

	}
}