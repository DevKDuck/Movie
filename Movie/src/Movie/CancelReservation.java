package Movie;

import java.util.Scanner;

public class CancelReservation {

	void CancelReservation() {
		System.out.println("예매 번호를 입력해주세요.");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();

		int reservationNum = Integer.parseInt(str);

		MovieExample mE = new MovieExample();

		if (mE.map.containsKey(reservationNum) == true) {
			System.out.println("고객님이 예매하신 좌석은" + mE.map.get(reservationNum) + "입니다.");
		} else {
			System.out.println("입력하신 번호는 예약되어 있지 않습니다 .");
		}

		System.out.println("예매를 취소하시겠니까?");
		System.out.println("네(1), 아니오(2) 중 하나를 입력해주세요. ");

		String s = scanner.next();

		switch (s) {
		case "네", "1":

			String seatNum = mE.map.get(reservationNum);
			System.out.println(seatNum);
			System.out.println("예매가 취소되었습니다. ");
			mE.seatArr.set(mE.seatMap.get(seatNum), seatNum);

			mE.seatReserveMap.put(seatNum, false);
			mE.map.remove("s");

			break;

		case "아니오", "2":
			break;
		default:

			System.out.println("잘못된 입력입니다.");
			break;
		}

	}
}
