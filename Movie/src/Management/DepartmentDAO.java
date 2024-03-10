package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDAO {

	private static Connection conn = null;
	private static PreparedStatement departmentListPstmt = null;
	private static PreparedStatement departmentInsertPstmt = null;
	private static PreparedStatement departmentDeletePstmt = null;
	private static PreparedStatement departmentUpdatePstmt = null;
	private static PreparedStatement departmentDetailPstmt = null;
	private static PreparedStatement departmentDeleteAllPstmt = null;

	static {
		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "bituser", // 계정이름
					"1004" // 계정비밀번호
			);

			System.out.println("연결 성공");

			conn.setAutoCommit(false);

			departmentListPstmt = conn.prepareStatement("SELECT DEPTNO FROM DEPT"); // 전체 deptno 보여주
			departmentInsertPstmt = conn.prepareStatement("insert into DEPT (DEPTNO , DNAME , LOC) values (?, ?, ?)");
			departmentDeletePstmt = conn.prepareStatement("delete from DEPT where DEPTNO =?");
			departmentUpdatePstmt = conn.prepareStatement("update DEPT set DNAME=?, LOC=? where DEPTNO=?");
			departmentDetailPstmt = conn.prepareStatement("SELECT * FROM DEPT WHERE DEPTNO = ?");
			departmentDeleteAllPstmt = conn.prepareStatement("delete from DEPT");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	public List<Integer> list() {
		List<Integer> list =new ArrayList<>();
		try {
			ResultSet rs = departmentListPstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("DEPTNO"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insert(Department department) {
		int updated = 0;
		try {
			departmentInsertPstmt.setInt(1, department.getDeptno());
			departmentInsertPstmt.setString(2, department.getDname());
			departmentInsertPstmt.setString(3, department.getLoc());

			updated = departmentInsertPstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public Department read(int deptno) {
		Department department = null;

		try {
			departmentDetailPstmt.setInt(1, deptno);

			ResultSet rs = departmentDetailPstmt.executeQuery();
			if (rs.next()) {
			
				department = new Department(rs.getInt("DEPTNO"), rs.getString("DNAME"), rs.getString("LOC"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	// 업데이트
	public int update(Department department) {
		try {
			// 입력 값을 설정 한다
			departmentUpdatePstmt.setString(1, department.getDname());
			departmentUpdatePstmt.setString(2, department.getLoc());
			departmentUpdatePstmt.setInt(3, department.getDeptno());

			conn.commit();
			return departmentUpdatePstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 삭제
	public int delete(int deptno) {
		int updated = 0;
		try {
			// 입력 값을 설정 한다
			departmentDeletePstmt.setInt(1, deptno);

			updated = departmentDeletePstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updated;
	}
	
	//전체 삭제
		public int clear() {

			int updated = 0;
			try {
				updated = departmentDeleteAllPstmt.executeUpdate();
				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return updated;
		}

}
