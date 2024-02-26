package Movie;

public class TicketingNumber {
	int randomNum() {
		MovieExample mE = new MovieExample();
		while (true) {
			int randomNum = (int) (Math.random() * 100000000);
			if (!mE.randomNumSet.contains(randomNum)) {
				return randomNum;
			}
		}
	}

}
