package Movie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class SeatStatus {
	
	void SeatStatus() {
		printSeats();
	}

	void printSeats() {
		// 좌석 현황 출력
		MovieExample mE = new MovieExample();
		System.out.println("************좌석 현황************");
		for (int i = 0; i < mE.seatArr.size(); i++) {
			if (i % 5 == 0) {
				System.out.println();
			}
			System.out.print("[" + mE.seatArr.get(i) + "]" + " ");
		}
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println();
		System.out.println("좌석을 예매해주세요. 예) 1-1");
		System.out.println("이미 예매된 좌석은 예매라고 표시됩니다.");

		Scanner scanner = new Scanner(System.in);
		String seatNum = scanner.next();
		seatTicketing(seatNum);
	}

	void seatTicketing(String seatNum) {
		MovieExample mE = new MovieExample();

		if (mE.seatReserveMap.get(seatNum) == true) {
			// 예메할 수 없다면
			System.out.println("이미 예약된 좌석입니다.");
		} else {
			System.out.println("예매 가능합니다. 예매하시겠습니까? ");
			System.out.println("네(1),  아니오(2),  초기화면(0)  하나를 입력해주세요.");
			Scanner scanner = new Scanner(System.in);
			String ticketingNum = scanner.next();

			switch (ticketingNum) {
			case "1":

				mE.seatReserveMap.put(seatNum, true);
				final int randomNum = new TicketingNumber().randomNum();
				mE.map.put(randomNum, seatNum);
				int i = mE.seatMap.get(seatNum);

				mE.seatArr.set(i, "예매");

				System.out.println("예매가 완료되었습니다.");
				System.out.println("예매한 좌석번호" + seatNum + "/" + "예매번호" + randomNum);
				System.out.println("감사합니다.");

				break;
			case "2":
				printSeats();
				break;
			case "0":
				break;
			default:
				break;
			}

		}
	}

}
