import javax.swing.JTextField;

public class Backtracking {

	int row, column;
	int[][] array = new int[9][9];
	long start;
	long end;
	int count = 0;

	public Backtracking() {
		start = System.currentTimeMillis();
	}

	void in(int[][] array1, JTextField[][] textField) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				array[i][j] = array1[i][j];
			}
		}

		// System.out.println("Start" + System.nanoTime());
		if (input()) {

			print(textField);
			// System.out.println("End" + System.nanoTime());
		} else {
			System.out.println("Solution does not exist");
			print(textField);
		}
	}

	boolean in(int[][] array1) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				array[i][j] = array1[i][j];
			}
		}
		if (input())
			return true;
		else
			return false;
	}

	boolean input() {
		if (solver(array))
			return true;

		else
			return false;
	}

	boolean solver(int array[][]) {
		end = System.currentTimeMillis();
		count++;
		if (end - start > 2000)
			return false;
		int currentRow, currentColumn;
		if (!emptygrid(array)) {
			return true;
		}

		for (int num = 1; num < 10; num++) {

			if (checkRow(row, num) && checkColumn(column, num) && checkBlock(row, column, num)) {
				currentRow = row;
				currentColumn = column;
				array[currentRow][currentColumn] = num;

				if (solver(array)) {
					return true;
				} else {
					array[currentRow][currentColumn] = 0;
					row = currentRow;
					column = currentColumn;
				}
			}

		}

		return false;
	}

	boolean emptygrid(int array[][]) {
		for (row = 0; row < 9; row++) {
			for (column = 0; column < 9; column++) {
				if (array[row][column] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	boolean checkRow(int row, int num) {
		for (int column = 0; column < 9; column++) {
			if (array[row][column] == num) {
				return false;
			}
		}
		return true;
	}

	boolean checkColumn(int column, int num) {
		for (int row = 0; row < 9; row++) {
			if (array[row][column] == num) {
				return false;
			}
		}
		return true;
	}

	boolean checkBlock(int startRow, int startColumn, int num) {

		if (startRow >= 0 && startRow <= 2) {
			startRow = 0;
		}

		if (startRow >= 3 && startRow <= 5) {
			startRow = 3;
		}

		if (startRow >= 6 && startRow <= 8) {
			startRow = 6;
		}

		if (startColumn >= 0 && startColumn <= 2) {
			startColumn = 0;
		}

		if (startColumn >= 3 && startColumn <= 5) {
			startColumn = 3;
		}

		if (startColumn >= 6 && startColumn <= 8) {
			startColumn = 6;
		}
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (array[row + startRow][column + startColumn] == num) {
					return false;
				}
			}
		}

		return true;
	}

	void print(JTextField[][] textField) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j].setText(Integer.toString(array[i][j]));
			}
		}

		// System.out.println("Total time is: " + (end - start) + " Millis.");

		// System.out.println("Nunber of comparisons: " + count);

	}

}
