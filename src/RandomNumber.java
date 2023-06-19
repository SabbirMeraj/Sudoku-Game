
import java.util.Date;

public class RandomNumber {

	int size;
	Date date = new Date();
	long a = 252103917;
	long c = size + 1;
	long previous = date.getTime();

	public RandomNumber(int size) {
		this.size = size;
	}

	public long randomNumberGenerator() {

		long r = a * previous + c;
		previous = r;
		// System.out.println( Math.abs(r)%size);
		return Math.abs(r) % size;

	}

}
