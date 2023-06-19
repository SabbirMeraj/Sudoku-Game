import java.util.*;

public class SudokuGenerator {

	int[][] board;
	// static Sudoku s=new Sudoku();
	RandomNumber random = new RandomNumber(9);
	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 9;

	public SudokuGenerator() {
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
	}

	public int[][] nextBoard(int difficulty) {
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		nextCell(0, 0);
		makeHoles(difficulty);
		return board;

	}

	public boolean nextCell(int x, int y) {
		int nextX = x;
		int nextY = y;
		int[] toCheck = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

		for (int i = top - 1; i > 0; i--) {
			current = (int) random.randomNumberGenerator();
			tmp = toCheck[current];
			toCheck[current] = toCheck[i];
			toCheck[i] = tmp;
		}

		for (int i = 0; i < toCheck.length; i++) {
			if (legalMove(x, y, toCheck[i])) {
				board[x][y] = toCheck[i];
				if (x == 8) {
					if (y == 8)
						return true;
					else {
						nextX = 0;
						nextY = y + 1;
					}
				} else {
					nextX = x + 1;
				}
				if (nextCell(nextX, nextY))
					return true;
			}
		}
		board[x][y] = 0;
		return false;
	}

	private boolean legalMove(int x, int y, int current) {
		for (int i = 0; i < 9; i++) {
			if (current == board[x][i])
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (current == board[i][y])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if (x > 2)
			if (x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if (y > 2)
			if (y > 5)
				cornerY = 6;
			else
				cornerY = 3;
		for (int i = cornerX; i < 10 && i < cornerX + 3; i++)
			for (int j = cornerY; j < 10 && j < cornerY + 3; j++)
				if (current == board[i][j])
					return false;
		return true;
	}

	public void makeHoles(int dififculty) {
		int row;
		int column;

		for (int i = 0; i < dififculty; i++) {
			row = (int) random.randomNumberGenerator();
			column = (int) random.randomNumberGenerator();
			board[row][column] = 0;

		}
	}

}