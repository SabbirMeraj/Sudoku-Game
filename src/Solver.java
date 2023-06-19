
import java.util.ArrayList;

import javax.swing.JTextField;

public class Solver {
	int counter;
	long startTime;
	long timeAfter;
	ArrayList<Integer> list;
	private static int[][] a;

	public Solver() {

	}

	public Solver(int[][] t, JTextField[][] textField) {
		counter = 0;
		// startTime=System.currentTimeMillis();
		a = t;
		list = new ArrayList<Integer>();

		for (int h = 0; h < 10; h++) {
			if (complete()) {
				print(textField);
				break;
			}

			int[][] tmp = copy(a);
			singelNaked();
			if (tmp == a) {
				print(textField);
				break;
			}

			basic();

		}

		// if the puzzle is not solved, the backtracking method is used.
		if (!complete()) {

			Backtracking b = new Backtracking();
			try {

				b.in(a, textField);
			} catch (Exception e) {
			}

			// a = b.puzzle;

			// counter = counter + b.counter;
		}

		// Solution prints out.
		/*
		 * for(int i=0;i<9;i++){ for(int j=0;j<9;j++){
		 * textField[i][j].setText(Integer.toString(a[i][j])); } }
		 */
	}

	public void basic() {

		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {

			temp = new ArrayList<Integer>();
			for (int j = 0; j < 9; j++) {

				temp.add(a[i][j]);
			}

			temp = checkArray(temp);
			for (int j = 0; j < 9; j++) {

				a[i][j] = temp.get(j);

			}

		}

		// this will check every COLUMN in the matrix a
		for (int i = 0; i < 9; i++) {

			temp = new ArrayList<Integer>();
			for (int j = 0; j < 9; j++) {

				temp.add(a[j][i]);
			}

			temp = checkArray(temp);
			for (int j = 0; j < 9; j++) {

				a[j][i] = temp.get(j);
			}
		}

		// this will check every BOX checkBox(0,0); checkBox(0,3);
		// checkBox(0,6);
		// checkBox(3,0); checkBox(3,3); checkBox(3,6); checkBox(6,0);
		// checkBox(6,3); checkBox(6,6);

	}

	public ArrayList<Integer> checkArray(ArrayList<Integer> row) {

		int i = 0; // i counts the empty cells.
		list = resetList();

		for (int j = 0; j < row.size(); j++) {
			if (row.get(j) == 0) {

				i += 1;

			} else {
				list.remove(list.indexOf(row.get(j)));

			}
			if (i > 1) {
				break;

			}
		}

		if (list.size() == 1) {
			row.set(row.indexOf(0), list.get(0));

		}

		return row;

	}

	public void checkBox(int row, int col) {
		ArrayList<Integer> ret = getBox(row, col);
		if (ret.size() == 1) {

			row = (row / 3) * 3;
			col = (col / 3) * 3;
			for (int r = 0; r < 3; r++) {

				for (int c = 0; c < 3; c++) {
					if (a[row + r][col + c] == 0) {

						a[row + r][col + c] = ret.get(0);

					}
				}
			}

		}
	}

	public ArrayList<Integer> getBox(int row, int col) {
		ArrayList<Integer> ret = resetList();

		row = (row / 3) * 3;
		col = (col / 3) * 3;

		for (int r = 0; r < 3; r++) {

			for (int c = 0; c < 3; c++) {
				counter++;
				if (a[row + r][col + c] != 0) {
					ret.remove(ret.indexOf(a[row + r][col + c]));

				}
			}

		}

		return ret;
	}

	public ArrayList<Integer> getRow(int row) {
		ArrayList<Integer> ret = resetList();
		for (int c = 0; c < 9; c++) {

			counter++;
			if (!(a[row][c] == 0)) {

				ret.remove(ret.indexOf(a[row][c]));
			}

		}
		return ret;
	}

	public ArrayList<Integer> getColumn(int col) {
		ArrayList<Integer> ret = resetList();
		for (int r = 0; r < 9; r++) {

			counter++;
			if (a[r][col] != 0) {

				ret.remove(ret.indexOf(a[r][col]));
			}

		}
		return ret;
	}

	// This method behaves exactly as naked single method.

	public void singelNaked() {
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				if (a[i][j] == 0) {

					ArrayList<Integer> l1 = getRow(i); // get possible missing
														// numbers in this ROW

					ArrayList<Integer> l2 = getColumn(j); // get possible
															// numbers in this
															// COLUMN
					ArrayList<Integer> l3 = getBox(i, j); // get possible
															// numbers in this
															// BOX
					ArrayList<Integer> tmp = new ArrayList<Integer>();

					for (int m = 0; m < 9; m++) {

						counter++;
						if (l1.contains(m + 1) &&

								l2.contains(m + 1) &&

								l3.contains(m + 1)) {

							tmp.add(m + 1);
						}

					}
					if (tmp.size() == 1) {

						a[i][j] = tmp.get(0);

					}

				}
			}

		}
	}

	private boolean complete() {
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				if (a[i][j] == 0) {
					return false;

				} else {

				}
			}

		}
		return true;
	}

	public int[][] copy(int[][] a) {
		int[][] tmp = new int[9][9];
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				tmp[i][j] = a[i][j];
			}
		}

		return tmp;
	}

	private ArrayList<Integer> resetList() {

		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.add(1);
		tmp.add(2);
		tmp.add(3);

		tmp.add(4);
		tmp.add(5);

		tmp.add(6);
		tmp.add(7);
		tmp.add(8);

		tmp.add(9);

		return tmp;
	}

	void print(JTextField[][] textField) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j].setText(Integer.toString(a[i][j]));
			}
		}
		// timeAfter= System.currentTimeMillis();
		// System.out.println("Total time is: " + (timeAfter - startTime) + "
		// Millis.");

		// System.out.println("Nunber of comparisons: " + counter);

	}

}
