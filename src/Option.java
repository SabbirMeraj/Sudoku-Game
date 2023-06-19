
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Option {

	public Option() {

	}

	public void selectOption() {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		JButton button1 = new JButton("Game");
		JButton button2 = new JButton("Solver");
		JButton button3 = new JButton("Quit");

		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.setSize(700, 600);
		frame.setVisible(true);

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Game game = new Game();
				game.newGame();
			}

		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new Board(1);

			}
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
	}
}
