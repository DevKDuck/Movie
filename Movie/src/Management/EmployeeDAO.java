package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;




public class EmployeeDAO {

	private static Connection conn = null;
	private static PreparedStatement employeeListPstmt = null;
	private static PreparedStatement employeetInsertPstmt = null;
	private static PreparedStatement employeeDeletePstmt = null;
	private static PreparedStatement employeeUpdatePstmt = null;
	private static PreparedStatement employeeDetailPstmt = null;
	private static PreparedStatement employeeDeleteAllPstmt = null;

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

			employeeListPstmt = conn.prepareStatement("SELECT e.empno, e.ename, e.job, e.mgr, to_char(e.hiredate, 'YYYY-MM-DD') AS hiredate, e.sal, e.comm, e.deptno, d.dname, d.loc FROM emp e JOIN dept d ON e.deptno = d.deptno"); 
			employeetInsertPstmt = conn.prepareStatement("insert into EMP (EMPNO , ENAME , JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) values (?, ?, ?, ?, ?, ?, ?, ?)");
			employeeDeletePstmt = conn.prepareStatement("delete from EMP where EMPNO =?");
			employeeUpdatePstmt = conn.prepareStatement("update EMP set , ENAME=? , JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? where EMPNO=?");
			employeeDetailPstmt = conn.prepareStatement("SELECT * FROM DEPT WHERE EMPNO = ?");
			employeeDeleteAllPstmt = conn.prepareStatement("delete from EMPNO");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	public List<Employee> list(){
		List<Employee> list = new ArrayList<>();
		
		try {
			ResultSet rs = employeeListPstmt.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee(rs.getInt("EMPNO"), rs.getString("ENAME"), rs.getString("JOB"), rs.getInt("MGR"), rs.getDate("HIREDATE"), rs.getInt("SAL"),rs.getInt("COMM"),rs.getInt("DEPTNO"));
								list.add(emp);	
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insert(Employee employee) {
		int updated = 0;
		
		try {
			employeetInsertPstmt.setInt(1, employee.getEmpno());
			employeetInsertPstmt.setString(2, employee.getEname());
			employeetInsertPstmt.setString(3, employee.getJob());
			employeetInsertPstmt.setInt(4, employee.getMgr());
			employeetInsertPstmt.setDate(5, (java.sql.Date) employee.getHiredate());
			employeetInsertPstmt.setInt(6, employee.getSal());
			employeetInsertPstmt.setInt(7, employee.getComm());
			employeetInsertPstmt.setInt(8, employee.getDeptno());

			updated = employeetInsertPstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public Employee read(int empno) {
		Employee emp = null;

		try {
			employeeDetailPstmt.setInt(1, empno);

			ResultSet rs = employeeDetailPstmt.executeQuery();
			if (rs.next()) {
				emp = new Employee(rs.getInt("EMPNO"), rs.getString("ENAME"), rs.getString("JOB"), rs.getInt("MGR"), rs.getDate("HIREDATE"),rs.getInt("SAL"), rs.getInt("COMM"), rs.getInt("DEPTNO"));
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	// 업데이트
	public int update(Employee emp) {
		try {
			
			employeeUpdatePstmt.setString(1, emp.getEname());
			employeeUpdatePstmt.setString(2, emp.getJob());
			employeeUpdatePstmt.setInt(3, emp.getMgr());
			employeeUpdatePstmt.setDate(4, (java.sql.Date) emp.getHiredate());
			employeeUpdatePstmt.setInt(5, emp.getSal());
			employeeUpdatePstmt.setInt(6, emp.getComm());
			employeeUpdatePstmt.setInt(7, emp.getDeptno());
			employeeUpdatePstmt.setInt(8, emp.getEmpno());
			

			conn.commit();
			return employeeUpdatePstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 삭제
	public int delete(int empno) {
		int updated = 0;
		try {
			employeeDeletePstmt.setInt(1, empno);

			updated = employeeDeletePstmt.executeUpdate();
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
				updated = employeeDeleteAllPstmt.executeUpdate();
				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return updated;
		}

}
