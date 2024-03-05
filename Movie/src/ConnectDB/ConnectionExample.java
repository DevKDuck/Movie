package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionExample {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", 
					"bituser", //계정이름
					"1004" //계정 비밀번호
					);
			// Statement 를 이용하여 변경하기 
	         Statement stmt = conn.createStatement();
	         int updated = stmt.executeUpdate("INSERT INTO users (userid, username, userpassword, userage, useremail) VALUES ('hong1', '홍길동', '1004', 20, 'hong@naver.com')");
	         System.out.println("변경 건수  : " + updated);
	        
			
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}
	}
}		