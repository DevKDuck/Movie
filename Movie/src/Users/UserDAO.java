package Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Board.Board;

public class UserDAO {

	private static Connection conn = null;
	private static PreparedStatement usersListPstmt = null;
	private static PreparedStatement usersInsertPstmt = null;
	private static PreparedStatement usersDeletePstmt = null;
	private static PreparedStatement usersUpdatePstmt = null;
	private static PreparedStatement usersDetailPstmt = null;
	private static PreparedStatement usersDeleteAllPstmt = null;

	static{
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"bituser", //계정이름 
					"1004" //계정비밀번호
					);
			
			System.out.println("연결 성공");
			
			conn.setAutoCommit(false);
			
			
			usersListPstmt = conn.prepareStatement("SELECT * FROM USERS"); //전체 유저 찾기
			usersInsertPstmt = conn.prepareStatement("insert into USERS (USERID , USERNAME , USERPASSWORD , USERAGE , USEREMAIL) values (?, ?, ?, ?, ?)");
			usersDeletePstmt = conn.prepareStatement("delete from USERS where userid =?");
			usersUpdatePstmt = conn.prepareStatement("update users set USERNAME=?, USERPASSWORD=?, USERAGE=?, USEREMAIL=? where userid=?");
			usersDetailPstmt = conn.prepareStatement("select * from USERS where USERID =?");
			usersDeleteAllPstmt = conn.prepareStatement("delete from users");
			
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//전체 목록
	public List<User> list(){
		List<User> list = new ArrayList<>();
		
		try {
			ResultSet rs = usersListPstmt.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getString("USERID"), rs.getString("USERNAME"), rs.getString("USERPASSWORD"), rs.getInt("USERAGE"), rs.getString("USEREMAIL"));
								list.add(user);	
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//삽입
	public int insert(User user) {
		int updated = 0; 
		try {
			// 입력 값을 설정 한다
			usersInsertPstmt.setString(1, user.getUserID());
			usersInsertPstmt.setString(2, user.getUserName());
			usersInsertPstmt.setString(3, user.getUserPassword());
			usersInsertPstmt.setInt(4, user.getUserAge());
			usersInsertPstmt.setString(5, user.getUserEmail());

			updated = usersInsertPstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	//읽기
	public User read(String id) {

		User user = null;
		try {
			// 입력 값을 설정 한다
			usersDetailPstmt.setString(1, id);

			ResultSet rs = usersDetailPstmt.executeQuery();
			if (rs.next()) {
				// 찾고자 하는 자료가 있음
				user = new User(rs.getString("USERID"), rs.getString("USERNAME"), rs.getString("USERPASSWORD"), rs.getInt("USERAGE"), rs.getString("USEREMAIL"));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	//업데이트
	public int update(User user) {
		try {
			// 입력 값을 설정 한다
			usersUpdatePstmt.setString(1, user.getUserName());
			usersUpdatePstmt.setString(2, user.getUserPassword());
			usersUpdatePstmt.setInt(3, user.getUserAge());
			usersUpdatePstmt.setString(4, user.getUserEmail());
			usersUpdatePstmt.setString(5, user.getUserID());

			conn.commit();
			return usersUpdatePstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	//삭제
	public int delete(String id) {
		int updated = 0;
		try {
			// 입력 값을 설정 한다
			usersDeletePstmt.setString(1, id);

			updated = usersDeletePstmt.executeUpdate();
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
			updated = usersDeleteAllPstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	


}
