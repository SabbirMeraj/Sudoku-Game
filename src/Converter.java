
public class Converter {

	public Converter() {

	}

	public static int stringToInt(String str) {
		int length = sizeOfString(str);
		int number = 0;
		int ch;

		for (int i = 0; i < length; i++) {
			ch = str.charAt(i) - 48;
			number = number + ch * pow(10, length - i);

		}

		return number;
	}

	public static String intToString(int num) {
		int length = sizeOfInteger(num);
		char reminder;
		String str = "";

		char[] temp = new char[length];

		for (int i = length; i > 0; i--) {
			reminder = (char) ((num % 10) + 48);
			temp[i - 1] = reminder;
			num = num / 10;
		}

		for (int i = 0; i <= length - 1; i++) {
			str += temp[i];
		}

		return str;

	}

	public static int sizeOfInteger(int num) {

		int count = 1;

		num = num / 10;

		while (num != 0) {
			count++;

			num = num / 10;
		}
		return count;
	}

	public static int sizeOfString(String str) {
		int i = 0;
		try {
			while (true) {
				str.charAt(i);
				i++;
			}
		} catch (IndexOutOfBoundsException e) {
			return i;
		}
	}

	private static int pow(int base, int power) {
		int num = 1;
		for (int i = power - 1; i > 0; i--) {
			num = num * base;
		}

		return num;
	}

}
