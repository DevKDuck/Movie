package Movie;

import java.util.Scanner;

public class TicketingSystem {

	void TicketingSystem() {
		System.out.println("**********************");
		System.out.println("*****영화 예매 시스템*****");
		System.out.println("**********************");

		System.out.println("1. 예매하기");
		System.out.println();
		System.out.println("2. 예매조회");
		System.out.println();
		System.out.println("3. 예매취소");

		Scanner scanner = new Scanner(System.in);
		String num = scanner.next();

		switch (num) {
		case "1":
			//예매하기 
			new SeatStatus().SeatStatus();
			break;
		case "2":
			//예매조회  
			new ReservationCheck().ReservationCheck();
			break;
		case "3":
			//예매취소
			new CancelReservation().CancelReservation();
			break;
		default:
			System.out.println("default");
			break;
		}
		//작업 후 시스템 재공지
		TicketingSystem();
	}
	
}