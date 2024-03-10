package Management;

import java.util.List;
import java.util.Scanner;

public class DepartmentExec {

	private Scanner scanner = new Scanner(System.in);

	DepartmentDAO departmentDAO;

	public DepartmentExec(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	// 목록
	public void showlist() {
		list();
		System.out.println("------------------------------------------------");
		System.out.println("  1.등록   |   2.상세보기  |  3.전체삭제  |  4.종료");
		System.out.println("------------------------------------------------");

		System.out.print("목록 번호 입력 : ");

		String listNum = scanner.nextLine();

		switch (listNum) {

		case ("1") -> registration();
		case ("2") -> detailRead();
		case ("3") -> deleteAll();
		case ("4") -> exit();
		default -> System.out.println("잘못된 입력입니다.");
		}
	}

	public void list() {

		System.out.println();
		System.out.println("[부서 목록]");
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("DEPTNO");
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		List<Integer> list = departmentDAO.list();

		for (Integer deptno : list) {
			System.out.println(deptno);
		}

		if (list.size() == 0) {
			System.out.println("게시물의 자료가 존재하지 않습니다");
		}

//		mainMenu();
	}

	// 등록
	public void registration() {

		System.out.print("부서 코드: ");
		String sDeptno = scanner.nextLine();

		System.out.print("부서명: ");
		String dname = scanner.nextLine();

		System.out.print("장소: ");
		String loc = scanner.nextLine();

		System.out.println(" ");

		System.out.println("-------------------------------------------");
		System.out.println("등록하시겠습니까?");
		System.out.println("1. OK | 2. Cancel");
		String menuNo = scanner.nextLine();
		if (menuNo.equals("1")) {
			int deptno = Integer.parseInt(sDeptno);
			int updated = departmentDAO.insert(new Department(deptno, dname, loc));
			System.out.println("생성 건수 :" + updated);
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		showlist();
	}

	public void detailRead() {
		System.out.println("[부서 번호 입력]");
		System.out.print("DEPTNO : ");

		String str = scanner.nextLine();
		int deptno = Integer.parseInt(str);

		Department department = departmentDAO.read(deptno);

		if (department != null) {
			department.printDetail();

			// 보조 메뉴 출력
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴 선택: ");
			String menuNo = scanner.nextLine();
			System.out.println();

			switch (menuNo) {
			case "1" -> update(deptno);
			case "2" -> delete(deptno);
			case "3" -> showlist();
			}
		} else {
			// 찾고자 하는 자료가 없음
			System.out.println("[" + deptno + "] 에 대한 자료가 존재하지않습니다 ");
			showlist();
		}
	}

	public void update(int deptno) {
		// 수정 내용 입력 받기
		System.out.println("[정보 수정]");

		System.out.print("부서 이름 : ");
		String dname = scanner.nextLine();

		System.out.print("지역명 : ");
		String loc = scanner.nextLine();

		// 보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();

		if (menuNo.equals("1")) {
			departmentDAO.update(new Department(deptno, dname, loc));
		} else {
			System.out.println("잘못된 번호입니다.");
		}
		showlist();
	}

	public void delete(int deptno) {
		int updated = departmentDAO.delete(deptno);

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		showlist();
	}

	public void deleteAll() {
		System.out.println("[게시물 전체 삭제]");
		int updated = departmentDAO.clear();

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		showlist();
	}

	public void exit() {
		System.out.println("exit");
		System.exit(0);
	}

	public static void main(String[] args) {

		DepartmentExec departmentExec = new DepartmentExec(new DepartmentDAO());

		departmentExec.showlist();

	}

}
