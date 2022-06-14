package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
   
	//필드
	private String id = "webdb";
	private String pw = "webdb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
   
	//생성자
   
   
	//메소드-gs
   
   
	//메소드-일반
	public void getConnection() {
		
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//>자원 정리 메소드
	public void close() {
		
		// 5. 자원정리
		try {
			
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//정보 등록 메소드
	public int guestInsert(GuestbookVo guestbookVo) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestbookVo.getName());
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getContent());
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("["+count + "건이 등록되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//정보 삭제 메소드
	public int guestDelete(int no, String password) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			

			//실행
			count = pstmt.executeUpdate();

			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("["+count + "건이 삭제되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//정보 전체 조회 메소드
	public List<GuestbookVo> getList() {
		
		//리스트 만들기
		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();
		
		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " select no ";
			query += "        , name ";
			query += "        , password ";
			query += "        , content ";
			query += "        , to_char(reg_date, ?) regDate ";
			query += " from guestbook ";
			query += " order by no asc ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "YYYY-MM-DD HH:MM:SS");
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			//반복문으로 Vo 만들기 List에 추가하기
			while(rs.next()) {
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");
				
				
				GuestbookVo guestbookvo = new GuestbookVo(no, name, password, content, regDate);
				
				guestList.add(guestbookvo);
				
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return guestList;
		
	}
	
	//Guestbook 찾기
	public GuestbookVo oneGuest(int delNo) {
		
		GuestbookVo guestVo = null;
		
		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할 것
			
			//SQL문 준비
			String query = "";
			query += " select no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         , to_char(reg_date, ?) regDate ";
			query += " from guestbook ";
			query += " where no = ? ";

			//바인딩
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			pstmt.setString(1, "YYYY-MM-DD HH:MM:SS");
			pstmt.setInt(2, delNo);

			//실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");

				guestVo = new GuestbookVo(no, name, password, content, regDate);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return guestVo;
	}

}
