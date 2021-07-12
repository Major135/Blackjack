package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Gui extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JLabel spielerLbl, dealerLbl, bankLbl, einsatzLbl, zählstrategie;
	private Blackjack blackjack;
	private boolean bereit = false;
	private JButton stand, neuesSpiel, ausloeschen, einsatzBestaetigt;
	private Render render;
	private MouseListener mouse;
	private boolean bestaetigt = false;
	private int width, height;
	private int x, y;
	private Rectangle hitbox;

	private Rectangle rect;

	public Gui(Blackjack blackjack) {
		this.blackjack = blackjack;
		init();
//		this.x = x;
//		this.y = y;
		x = 910;
		y = 295;
		bereit = true;
		width = 240;
		height = 175;
		hitbox = new Rectangle(x, y, width, height);
	}

	private void init() {
		render = new Render(this);
		frame = new JFrame("Blackjack");
		mouse = new MouseListener(blackjack);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBounds(0, 0, 1200, 1000);
		frame.getContentPane().setBackground(Color.green);
		hinzufuegenJComponent();
		frame.add(render);
		frame.addMouseListener(mouse);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
	}

	private void hinzufuegenJComponent() {
		spielerLbl = new JLabel();
		spielerLbl.setBounds(550, 620, 200, 20);
		spielerLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		dealerLbl = new JLabel();
		dealerLbl.setBounds(550, 250, 200, 20);
		dealerLbl.setFont(new Font("Arial", Font.PLAIN, 24));
		zählstrategie = new JLabel("", SwingConstants.CENTER);

		zählstrategie.setBounds(850, 20, 300, 250);
		zählstrategie.setFont(new Font("Arial", Font.PLAIN, 32));
		zählstrategie.setOpaque(true);
		zählstrategie.setBackground(Color.lightGray);
		zählstrategie.setVisible(false);

		bankLbl = new JLabel("Bank: 8000$");
		bankLbl.setBounds(50, 870, 250, 50);
		bankLbl.setFont(new Font("Arial", Font.PLAIN, 36));
		einsatzLbl = new JLabel("Einsatz: 0$");
		einsatzLbl.setBounds(500, 525, 200, 50);
		einsatzLbl.setFont(new Font("Arial", Font.PLAIN, 24));


		stand = new JButton("Stehen bleiben");
		stand.addActionListener(this);
		stand.setBounds(950, 520, 200, 100);
		stand.setVisible(true);

		neuesSpiel = new JButton("Neues Spiel");
		neuesSpiel.setBounds(950, 720, 200, 100);
		neuesSpiel.addActionListener(this);
		neuesSpiel.setVisible(true);

		einsatzBestaetigt = new JButton("Bestätigen");
		einsatzBestaetigt.setBounds(50, 600, 200, 100);
		einsatzBestaetigt.addActionListener(this);
		einsatzBestaetigt.setVisible(true);

		ausloeschen = new JButton("Einsatz AUSLÖSCHEN");
		ausloeschen.setBounds(50, 720, 200, 100);
		ausloeschen.addActionListener(this);
		ausloeschen.setVisible(true);

		neuesSpiel.setEnabled(false);
		stand.setEnabled(false);

		frame.add(spielerLbl);
		frame.add(dealerLbl);
		frame.add(einsatzLbl);
		frame.add(bankLbl);
		frame.add(ausloeschen);
		frame.add(einsatzBestaetigt);
		frame.add(zählstrategie);
		frame.add(stand);
		frame.add(neuesSpiel);

	}

	private class Render extends JLabel {

		private static final long serialVersionUID = 1L;
		private Gui gui;

		public Render(Gui gui) {
			this.gui = gui;
		}

		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Bilder werden angezeigt
			gui.render(g);
			repaint();

		}

	}

	private void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (bestaetigt) {
			for (Spieler sp : blackjack.getSpieler()) {
				sp.render(g);
			}
		}
//		g2d.draw(hitbox);
		blackjack.render(g);
	}

	public boolean isBereit() {
		return bereit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (stand == e.getSource()) {
			blackjack.stehenBleiben();
			stehenBleibenButtonsDisable();
			blackjack.zähleKarten();
		}
		if (neuesSpiel == e.getSource()) {
//			System.out.println("Neues Spiel");
			blackjack.neuesSpiel();
			neuesSpiel.setEnabled(false);
			stand.setEnabled(false);
			einsatzBestaetigt.setEnabled(true);
			ausloeschen.setEnabled(true);
			dealerLbl.setText("");
			spielerLbl.setText("");
			einsatzLbl.setText("Einsatz: 0$");
		}
		if (einsatzBestaetigt == e.getSource()) {
			stand.setEnabled(true);
			ausloeschen.setEnabled(false);
			einsatzBestaetigt.setEnabled(false);
			bestaetigt = true;
			blackjack.einsatzBestaetigt();
		}
		if (ausloeschen == e.getSource()) {
			blackjack.einsatzAusloeschen();
		}
	}

	public void stehenBleibenButtonsDisable() {
		neuesSpiel.setEnabled(true);
		stand.setEnabled(false);
	}

	public void trefferBoxGetroffen(int x, int y) {
		rect = new Rectangle(x - 10, y - 30, 1, 1);
		if (hitbox.intersects(rect)) {
			blackjack.karteZiehen();
		}
	}

	public JLabel getSpielerLbl() {
		return spielerLbl;
	}

	public JLabel getDealerLbl() {
		return dealerLbl;
	}

	public JLabel getBankLbl() {
		return bankLbl;
	}

	public JLabel getEinsatzLbl() {
		return einsatzLbl;
	}

	public boolean isBestaetigt() {
		return bestaetigt;
	}

	public void setBestaetigt(boolean enable) {
		this.bestaetigt = enable;
	}

	public JLabel getZählstrategie() {
		return zählstrategie;
	}

}
