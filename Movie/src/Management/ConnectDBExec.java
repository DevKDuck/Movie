package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDBExec {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "bituser", // 계정이름
					"1004" // 계정비밀번호
			);
			System.out.println("연결 성공");

			// 리스트 보여주기
			PreparedStatement pstmt = conn.prepareStatement("select * From dept");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int deptno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				String loc = rs.getString("LOC");
				
				System.out.println(deptno);
				System.out.println(dname);
				System.out.println(loc);
			}
			rs.close();
			pstmt.close();
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
