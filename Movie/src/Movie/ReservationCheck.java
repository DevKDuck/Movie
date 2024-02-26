package Movie;

import java.util.Scanner;

public class ReservationCheck {

	void ReservationCheck() {
		System.out.println("예매 번호를 입력해주세요.");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();

		int reservationNum = Integer.parseInt(s);

		MovieExample mE = new MovieExample();

		if (mE.map.containsKey(reservationNum) == true) {
			System.out.println("고객님이 예매하신 좌석은" + mE.map.get(reservationNum) + "입니다.");
		} else {
			System.out.println("입력하신 번호는 예약되어 있지 않습니다 .");
		}

	}

}