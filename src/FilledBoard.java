
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FilledBoard extends Board {
	String str;

	public FilledBoard(int[][] array1) {
		super(2);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException ex) {
				} catch (IllegalAccessException ex) {
				} catch (UnsupportedLookAndFeelException ex) {
				}

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						newArray[i][j] = array1[i][j];
						mainArray[i][j] = array1[i][j];
						ba.array[i][j] = array1[i][j];
					}
				}
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (newArray[i][j] != 0) {
							str = Converter.intToString(newArray[i][j]);
							field[i][j].setText(str);
							field[i][j].setEditable(false);

						}
					}
				}

			}

		}

		);
	}

}
