
public class Game {

	int[][] inputNumber = new int[9][9];
	int row;
	int column;
	long input;
	int num;
	int counter = 0;
	Backtracking b = new Backtracking();

	public Game() {

	}

	void newGame() {

		// RandomNumber random = new RandomNumber(9);
		SudokuGenerator generator = new SudokuGenerator();

		inputNumber = generator.nextBoard(55);

		new FilledBoard(inputNumber);

	}

}
