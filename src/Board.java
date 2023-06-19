import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Board {
	JTextField[][] field = new JTextField[9][9];
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton button1 = new JButton("Solve");
	JButton button2 = new JButton("Quit");
	int[][] newArray = new int[9][9];
	int[][] mainArray = new int[9][9];
	Backtracking ba = new Backtracking();
	Color color;
	int flag;

	public Board(int flag) {

		this.flag = flag;
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

				Font font1 = new Font("SansSerif", Font.BOLD, 20);

				frame.setResizable(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				panel.setLayout(new GridLayout(9, 9));

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						ba.array[i][j] = newArray[i][j];
					}
				}
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						field[i][j] = new JTextField();
						JTextField jtf = field[i][j];
						field[i][j].setFont(font1);
						int row = i;
						int column = j;
						if ((i >= 0 && i <= 2) || i >= 6 && i <= 8) {
							if ((j >= 0 && j <= 2) || (j >= 6 && j <= 8)) {
								field[i][j].setBackground(Color.LIGHT_GRAY);
							}

							else {
								field[i][j].setBackground(Color.WHITE);

							}

						} else {
							if (j >= 3 && j <= 5) {
								field[i][j].setBackground(Color.LIGHT_GRAY);

							}

							else {
								field[i][j].setBackground(Color.WHITE);
								// color=Color.WHITE;
							}
						}

						/*
						 * int num=1; if((jtf.getText())!=null){
						 * num=Integer.parseInt(jtf.getText()); }
						 */

						field[i][j].addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								// if(solver.checkRow(row, num) &&
								// solver.checkColumn(column, num) &&
								// solver.checkBlock(row, column, num)){
								try {
									// System.out.println(column);

									if (field[row][column].getBackground() != Color.RED) {
										color = field[row][column].getBackground();
										// System.out.println(field[row][column].getBackground());
									}

									String str = jtf.getText();

									if (str.length() == 1 && (str.charAt(0) > 48 && str.charAt(0) < 58)) {
										newArray[row][column] = Converter.stringToInt(jtf.getText());
										// test
										// System.out.println(newArray[row][column]);
										// solver.array[row][column]=newArray[row][column];

										if (ba.checkRow(row, newArray[row][column])
												&& ba.checkColumn(column, newArray[row][column])
												&& ba.checkBlock(row, column, newArray[row][column])) {

											/*
											 * for (int i = 0; i < 9; i++) { for
											 * (int j = 0; j < 9; j++) {
											 * System.out.print(ba.array[i][j] +
											 * " "); }
											 * 
											 * System.out.println(); }
											 */

											ba.array[row][column] = Converter.stringToInt(jtf.getText());

											// newArray[row][column]=solver.array[row][column];
											// System.out.println("YEs");
											field[row][column].setBackground(color);
											// System.out.println("Yes");
											// System.out.println(ba.array);
											// System.out.println(field[row][column].getBackground());
										}
										// }

										else {
											if (field[row][column].getBackground() != Color.RED) {
												color = field[row][column].getBackground();
												field[row][column].setBackground(Color.RED);

												JOptionPane.showMessageDialog(null, "Invalid Input");
												// solver.array[row][column]=0;
												// System.out.println(field[row][column].getBackground());
											}

										}
									} else {
										field[row][column].setBackground(Color.RED);
										JOptionPane.showMessageDialog(null, "Invalid Input. Enter Number 1-9");
									}

								} catch (NumberFormatException err) {

								}

							}
						});
						panel.add(field[i][j]);
					}
				}

				// frame.setLayout(new GridBagLayout());
				// frame.add(button1);
				// frame.add(button2);
				/*
				 * frame.add(button2, BorderLayout.AFTER_LINE_ENDS);
				 * frame.setLayout(new GridBagLayout()); GridBagConstraints gbc
				 * = new GridBagConstraints(); gbc.gridx = 0; gbc.gridy = 1;
				 * gbc.weightx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
				 */
				// frame.add(button2, BorderLayout.AFTER_LINE_ENDS);
				// frame.add(button1,BorderLayout.AFTER_LINE_ENDS);
				// gbc.gridy++;
				// frame.add(button2, gbc);
				// gbc.gridy++;
				// frame.setLayout(new BorderLayout());
				// JLabel label=new JLabel();
				// frame.add(label);
				// frame.add(new MenuPane(), BorderLayout.SOUTH);

				frame.pack();
				frame.setSize(1280, 720);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.add(panel);
				frame.add(new MenuPane(), BorderLayout.AFTER_LINE_ENDS);

			}

		});
	}

	public class MenuPane extends JPanel {

		public MenuPane() {
			setBorder(new EmptyBorder(4, 4, 4, 4));
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(button1, gbc);
			gbc.gridy++;
			add(button2, gbc);
			gbc.gridy++;

			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (flag == 1) {
						new Solver(newArray, field);
					}

					if (flag == 2) {
						new Solver(mainArray, field);
					}
					// sudoku.in(newArray,field);
					// sudoku.input();
				}
			});

			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});

		}
	}

}