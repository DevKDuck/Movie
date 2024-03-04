package Board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//게시판을 관리 클래스 

import java.util.Scanner;

public class BoardExample10 {
	private static int num = 3; //인덱스
	private static ArrayList<Board> boardArr =  new ArrayList<Board>(); // board 게시글 배열
	
	//Field
	private Scanner scanner = new Scanner(System.in);
	//할일 : 게시물 정보를 저장 할 수 있는 배열을 선언한다 
	
	
	void BoardExample() {
		setList();
		//예시글 설정
	}
	
	
	public void setList() {
		Board board = new Board(1, "게시판에 오신 것을 환영합니다.", "게시판에 오신 것을 환영합니다.", "winter","2024.01.27");
//		board.setBno(1);
//		board.setBcontent("게시판에 오신 것을 환영합니다.");
//		board.setBwriter("winter");
//		board.setBtitle("게시판에 오신 것을 환영합니다.");
//		board.setBdate("2024.01.27");
		
		boardArr.add(board);
		
		Board board2 = new Board(2, "올 겨울은 많이 춥습니다.","올 겨울은 많이 춥습니다.","winter","2024.01.27");
//		board2.setBno(2);
//		board2.setBcontent("올 겨울은 많이 춥습니다.");
//		board2.setBwriter("winter");
//		board2.setBtitle("올 겨울은 많이 춥습니다.");
//		board2.setBdate("2024.01.27");
		boardArr.add(board2);
	}
	
	//Constructor
	
	//Method		
	public void list() {
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------------------------------------------");
		

		

		for (Board arr: boardArr) {
			System.out.printf("%-6s%-12s%-16s%-40s \n", 
					arr.getBno(),arr.getBwriter(), arr.getBdate(), arr.getBtitle());
		}
		 
       //할일 : 배열에 저장된 것을 출력할 수 있게 기능 추가한다
		
		mainMenu();
	}
	
	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			case "1" -> create();
			case "2" -> read();
			case "3" -> clear();
			case "4" -> exit();
			default -> list();
		}
	}	
	
	public void create() {
		System.out.println("[새 게시물 입력]");
		
		//할일 : 제목 입력
		System.out.print("제목: ");
		String title = scanner.nextLine();
		
		//할일 : 내용 입력		
		System.out.print("내용: "); 	
		String content = scanner.nextLine();

		//할일 : 작성자 입력
		System.out.print("작성자: ");
		String writer = scanner.nextLine();
		
		//할일 : 보조 메뉴 출력
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		
		
		if(menuNo.equals("1")) {
			//할일 : 입력된 정보를 기준으로 객체를 생성하여 배열에 저장 하는 기능을 구현한다 
			Board board = new Board(num++, title, content, writer, nowDate());
//			board.setBno(num++);
//			board.setBtitle(title);
//			board.setBwriter(writer);
//			board.setBcontent(content);
//			board.setBdate(nowDate());
//			
			
			//boardArr에 추가하는것 까지 완료
			boardArr.add(board);
		}
		
		//게시물 목록 출력
		list();
	}
	
	//현재 날짜 생성
	public String nowDate() {
		LocalDate now = LocalDate.now();
		  // 포맷 정의 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");      
		// 포맷 적용     
		return now.format(formatter);     
	}
	
	public void read() {
		System.out.println("[게시물 읽기]");
		System.out.print("bno: ");
		//게시물 번호 입력 
		int bno = Integer.parseInt(scanner.nextLine());
		
		//할일 : 입력된 게시물 번호를 이용하여 게시물 목록에서 자료를 찾아 출력한다
		//
		//보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		
		switch(menuNo) {
			case "1" -> update(bno);
			case "2" -> delete(bno);
			case "3" -> list();
			default -> System.out.println("입력하신 번호가 잘못되었습니다");
		}	
		
		System.out.println();

		//게시물 목록 출력
		list();
		
	}
	
	public void update(int idx) {
		//수정 내용 입력 받기
		System.out.println("[수정 내용 입력]");
		System.out.print("제목: "); 	
		//할일 : 제목 입력
		String title = scanner.nextLine();
		
		System.out.print("내용: "); 	
		//할일 : 내용 입력
		String content = scanner.nextLine();
		System.out.print("작성자: "); 	
		//할일 : 작성자 입력
		String writer = scanner.nextLine();
		
		
		
		//보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		//할일 : 입력된 정보를 기준으로 게시물 정보 수정한다  
		
		if(menuNo.equals("1")) {
			//할일 : 입력된 정보를 기준으로 객체를 생성하여 배열에 저장 하는 기능을 구현한다 
			Board board = new Board(idx, title, content, writer, nowDate());
//			board.setBno(idx);
//			board.setBtitle(title);
//			board.setBwriter(writer);
//			board.setBcontent(content);
//			board.setBdate(nowDate());
			
			//boardArr에 추가하는것 까지 완료
			boardArr.set(idx-1,board);
		}
		
		//게시물 목록 출력
		list();
	}
	
	public void delete(int idx) {
		//할일 : 게시물 정보 삭제
		boardArr.remove(idx-1);
		
		for (Board arr: boardArr) {
			if (idx-1 < arr.getBno()) {
				arr.setBno(arr.getBno() - 1);
				num--;
			}
		}// 인덱스가 자신보다 높으면 bno-1 하기, num 인덱스 맞추기
		  
		//게시물 목록 출력		
		list();
	}
		
	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		//할일 : 게시물 전체 삭제 기능 구현 
		boardArr.clear();
		num = 1;
		
		//게시물 목록 출력
		list();
	}	
	public void exit() {
		System.exit(0);
	}
	

	
	public static void main(String[] args) {
		BoardExample10 boardExample = new BoardExample10();
		boardExample.setList();
		boardExample.list();
		
	}
}
