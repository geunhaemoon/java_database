package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConnectionMgr;

public class Main {
	public static void main(String[] args) {

		DBConnectionMgr pool = DBConnectionMgr.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Java 와 DB를 연결함
			con = DBConnectionMgr.getInstance().getConnection();

			// 실행할 쿼리문 작성
			String sql = "select * from user_tb";

			// 작성한 쿼리문을 가공
			pstmt = con.prepareStatement(sql);

			// 가공된 쿼리문을 실행 > 결과를 ResultSet으로 변환
			rs = pstmt.executeQuery();

			// 결과가 담긴 ResultSet을 반복작업을 통해 데이터 조회
			System.out.println("번호\t|\t아이디\t|\t비밀번호");
//			System.out.println("user_id \t|\t username \t|\t password");
			while (rs.next()) {
				// getInt() -> 정수
				// getString() -> 문자열
				System.out.println(rs.getInt(1) + "\t|\t" + rs.getString(2) + "\t|\t" + rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 생성된 rs, pststm, con 객체 소멸 (데이터 베이스 연결 해체)
			DBConnectionMgr.getInstance().freeConnection(con, pstmt, rs);
		}
/////////////////////////////////////////////////

		try {
			// 데이터베이스 연결
			con = pool.getConnection();
			// 쿼리문 작성
			String sql = "insert into user_tb values(0,?,?)";
			// 쿼리문 가공 준비
			pstmt = con.prepareStatement(sql);
			// 쿼리문 가공
			pstmt.setString(1, "qqq");
			pstmt.setString(2, "1234");

			int successCount = pstmt.executeUpdate();
			System.out.println("insert 성공 횟수: " + successCount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

		try {
			// Java 와 DB를 연결함
			con = DBConnectionMgr.getInstance().getConnection();

			// 실행할 쿼리문 작성
			String sql = "select * from user_tb";

			// 작성한 쿼리문을 가공
			pstmt = con.prepareStatement(sql);

			// 가공된 쿼리문을 실행 > 결과를 ResultSet으로 변환
			rs = pstmt.executeQuery();

			// 결과가 담긴 ResultSet을 반복작업을 통해 데이터 조회
			System.out.println("번호\t|\t아이디\t|\t비밀번호");
//			System.out.println("user_id \t|\t username \t|\t password");
			while (rs.next()) {
				// getInt() -> 정수
				// getString() -> 문자열
				System.out.println(rs.getInt(1) + "\t|\t" + rs.getString(2) + "\t|\t" + rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 생성된 rs, pststm, con 객체 소멸 (데이터 베이스 연결 해체)
			DBConnectionMgr.getInstance().freeConnection(con, pstmt, rs);
		}

	}
}
