package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JPanel {

	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private Blackjack blackjack;
	private boolean bereit = false;

	public Gui(Blackjack blackjack) {
		this.blackjack = blackjack;
		init();
		bereit = true;
	}

	private void init() {
		frame = new JFrame("BlackJack");
		panel = new JPanel();
		label = new JLabel("Test");
//		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setBounds(0, 0, 1200, 1000);
		panel.setBounds(0, 0, 1200, 1000);
		label.setBounds(150, 300, 100, 100);
		panel.setBackground(Color.green);
		panel.setVisible(true);
		panel.add(label);
		frame.add(panel);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void render(Graphics g) {
		super.paintComponents(g);
		g = (Graphics) panel.getGraphics();
//		g.setColor(new Color(0).CYAN);
//		g.drawRect(20, 20, 40, 40);
		int x = 0;
		BufferedImage[] img = blackjack.gibTex();
//		g.drawImage(img[0], 20, 50, 125, 181, null);
//		g.drawImage(img[1], x += 20, 50, 125, 181, null);
		for (int i = 0; i < img.length; i++) {
			g.drawImage(img[i], x += 20, 50, 125, 181, this);

		}
		repaint();
	}

	public boolean isBereit() {
		return bereit;
	}

}
