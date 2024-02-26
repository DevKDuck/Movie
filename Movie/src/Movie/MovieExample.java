package Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MovieExample {

	static ArrayList<String> seatArr = new ArrayList(); // 좌석 1-1,1-2...5-4,5-5
	static HashMap<Integer, String> map = new HashMap<>(); // key - 예매번호,value - 좌석 ,ex) 30260332, 3-1
	static HashMap<String, Boolean> seatReserveMap = new HashMap<>(); // key - 예매번호,value - 좌석 ,ex) 30260332, 3-1
	static HashMap<String, Integer> seatMap = new HashMap<>();// key -좌석 ,value - 예매 가능여부(기본 false) ,ex) 3-1, false
	static Set<Integer> randomNumSet = new HashSet();

	public static void main(String[] args) {

		// seatArr, seatMap, seatReserveMap 세팅
		int idx = 0;
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 6; j++) {
				seatArr.add(Integer.toString(i) + "-" + Integer.toString(j));
				seatMap.put(Integer.toString(i) + "-" + Integer.toString(j), idx++);
				seatReserveMap.put(Integer.toString(i) + "-" + Integer.toString(j), false);
			}
		}

		// 티켓시스템실행
		TicketingSystem tiketing = new TicketingSystem();
		tiketing.TicketingSystem();

	}

}