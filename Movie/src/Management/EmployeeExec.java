package Management;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class EmployeeExec {

	private Scanner scanner = new Scanner(System.in);

	EmployeeDAO employeeDAO;
	Department departmentDAO;

	public EmployeeExec(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
		
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

		List<Employee> list = employeeDAO.list();

		for (Employee emp : list) {
			emp.print();
		}

		if (list.size() == 0) {
			System.out.println("게시물의 자료가 존재하지 않습니다");
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

//		mainMenu();
	}

	// 등록
	public void registration() {

		System.out.print("사원 번호 : ");
		String strEmpno = scanner.nextLine();

		System.out.print("사원이름 : ");
		String ename = scanner.nextLine();

		System.out.print("직업 : ");
		String job = scanner.nextLine();

		System.out.print("상관 번호 : ");
		String strMgr = scanner.nextLine();

		System.out.print("입사일자(ex- 1920-12-17) :  ");
		String strHiredate = scanner.nextLine();

		System.out.print("급여  : ");
		String strSal = scanner.nextLine();

		System.out.print("보너스   : ");
		String strComm = scanner.nextLine();

		System.out.print("부서번호   : ");
		String strDeptno = scanner.nextLine();

		System.out.println(" ");

		System.out.println("-------------------------------------------");
		System.out.println("등록하시겠습니까?");
		System.out.println("1. OK | 2. Cancel");
		String menuNo = scanner.nextLine();
		if (menuNo.equals("1")) {
			int empno = Integer.parseInt(strEmpno);
			int mgr = Integer.parseInt(strMgr);

			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				Date hiredate = (Date) formatter.parse(strHiredate);

				int sal = Integer.parseInt(strSal);
				int comm = Integer.parseInt(strComm);
				int deptno = Integer.parseInt(strDeptno);

				DepartmentDAO deptDAO = new DepartmentDAO();
				Department dept = deptDAO.read(deptno);
				if (dept != null) {
					int updated = employeeDAO.insert(new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno));
					System.out.println("생성 건수 :" + updated);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		showlist();
	}

	public void detailRead() {
		System.out.println("[사 번호 입력]");
		System.out.print("EMPNO : ");

		String str = scanner.nextLine();
		int empno = Integer.parseInt(str);

		Employee emp = employeeDAO.read(empno);

		if (emp != null) {
			emp.printDetail();

			// 보조 메뉴 출력
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴 선택: ");
			String menuNo = scanner.nextLine();
			System.out.println();

			switch (menuNo) {
			case "1" -> update(empno);
			case "2" -> delete(empno);
			case "3" -> showlist();
			}
		} else {
			// 찾고자 하는 자료가 없음
			System.out.println("[" + empno + "] 에 대한 자료가 존재하지않습니다 ");
			showlist();
		}
	}

	public void update(int empno) {
		// 수정 내용 입력 받기
		System.out.println("[정보 수정]");

		System.out.print("사원 번호 : ");
		String strEmpno = scanner.nextLine();

		System.out.print("사원이름 : ");
		String ename = scanner.nextLine();

		System.out.print("직업 : ");
		String job = scanner.nextLine();

		System.out.print("상관 번호 : ");
		String strMgr = scanner.nextLine();

		System.out.print("입사일자 : ");
		String strHiredate = scanner.nextLine();

		System.out.print("급여  : ");
		String strSal = scanner.nextLine();

		System.out.print("보너스   : ");
		String strComm = scanner.nextLine();

		System.out.print("부서번호   : ");
		String strDeptno = scanner.nextLine();

		// 보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();

		if (menuNo.equals("1")) {
			int changeEmpno = Integer.parseInt(strEmpno);
			int mgr = Integer.parseInt(strMgr);

			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date hiredate = (Date) formatter.parse(strHiredate);
				int sal = Integer.parseInt(strSal);
				int comm = Integer.parseInt(strComm);
				int deptno = Integer.parseInt(strDeptno);

				employeeDAO.update(new Employee(changeEmpno, ename, job, mgr, hiredate, sal, comm, deptno));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("잘못된 번호입니다.");
		}
		showlist();
	}

	public void delete(int empno) {
		int updated = employeeDAO.delete(empno);

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		showlist();
	}

	public void deleteAll() {
		System.out.println("[게시물 전체 삭제]");
		int updated = employeeDAO.clear();

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

		EmployeeExec employeeExec = new EmployeeExec(new EmployeeDAO());
		employeeExec.showlist();

	}

}
