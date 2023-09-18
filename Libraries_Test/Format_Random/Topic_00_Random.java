package Format_Random;

import java.util.Random;

public class Topic_00_Random {

	public static void main(String[] args) {
		Random rand = new Random();
		rand.nextInt(999999);
		System.out.println("Random: " + rand.nextInt(999999));

	}

}
