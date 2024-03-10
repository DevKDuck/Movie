package Management;

import java.util.Scanner;

public class MainMenu {

	private Scanner scanner = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.부서관리 | 2.사원관리 | 3.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch (menuNo) {
		case "1" -> departManager();
		case "2" -> employeeManager();
		case "3" -> exit();
		}
	}
	
	private void departManager() {
		DepartmentExec departmentExec = new DepartmentExec(new DepartmentDAO());
		departmentExec.showlist();
	}
	
	private void employeeManager() {
		EmployeeExec employeeExec = new EmployeeExec(new EmployeeDAO());
		employeeExec.showlist();
	}
	
	private void exit() {
		System.out.println("시스템이 종료되었습니다. ");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new MainMenu().mainMenu();
	}

}
