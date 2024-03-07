package Users;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import Board.Board;

public class UserEx {

	private Scanner scanner = new Scanner(System.in);
	
//	객체 생성
	UserDAO userDAO;
//	생성자 
	public UserEx(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	
	//목록
	public void list() {
		
		System.out.println();
		System.out.println("[회원 목록]");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-12s%-12s%-10s\n", "userid", "userName", "userPassword", "userAge", "userEmail");
		System.out.println("---------------------------------------------------------------------------------------------------");

		List<User> list = userDAO.list();
		for (User user : list) {
			user.print();
		}

		if (list.size() == 0) {
			System.out.println("게시물의 자료가 존재하지 않습니다");
		}

		mainMenu();
	}
	
	
	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch (menuNo) {
		case "1" -> create();
		case "2" -> read();
		case "3" -> clear();
		case "4" -> exit();
		}
	}
	
	public void create() {
		System.out.println("[새로운 회원 입력]");
		System.out.print("아이디  : ");
		String id = scanner.nextLine();
		
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		
		System.out.print("비밀번호 : ");
		String pwd = scanner.nextLine();
		
		System.out.print("나이 : ");
		String age = scanner.nextLine();
		int integerAge = Integer.parseInt(age);
		
		
		System.out.print("이메일 : ");
		String email = scanner.nextLine();
		
		System.out.println(" ");
		
		// 할일 : 보조 메뉴 출력
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		if (menuNo.equals("1")) {
			int updated = userDAO.insert(new User(id, name, pwd, integerAge, email));
			// 변경된 건 수
			System.out.println("변경 건수  : " + updated);
		}

		// 게시물 목록 출력
		list();
	}
	
	public void read() {
		System.out.println("[아이디 입력]");
		System.out.print("아이디 : ");

		String id = scanner.nextLine();

		User user = userDAO.read(id);

		if (user != null) {
			user.printDetail();

			// 보조 메뉴 출력
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴 선택: ");
			String menuNo = scanner.nextLine();
			System.out.println();

			switch (menuNo) {
			case "1" -> update(id);
			case "2" -> delete(id);
			case "3" -> list();
			}
		} else {
			// 찾고자 하는 자료가 없음
			System.out.println("[" + id + "] 에 대한 자료가 존재하지않습니다 ");
			list();
		}
	}
	
	public void update(String id) {
		// 수정 내용 입력 받기
		System.out.println("[정보 수정]");
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		
		
		System.out.print("비밀번호 : ");
		String pwd = scanner.nextLine();
		
		System.out.print("나이 : ");
		String age = scanner.nextLine();
		int integerAge = Integer.parseInt(age);

		
		System.out.print("이메일  : ");
		String email = scanner.nextLine();
		
		// 보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		// 아래 구문이 동작할 수 있게 기능 추가
		// update 구문 완성해서 구현 해주세요
		if (menuNo.equals("1")) {
			userDAO.update(new User(id, name, pwd, integerAge, email));
		}
		// 게시물 목록 출력
		list();
	}
	
	
	public void delete(String id) {
		int updated = userDAO.delete(id);

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		list();
	}

	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		int updated = userDAO.clear();

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		list();
	}

	public void exit() {
		System.out.println("exit");
		System.exit(0);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserEx userEx = new UserEx(new UserDAO());
		userEx.list();
	}

}
