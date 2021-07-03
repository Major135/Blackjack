package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Gui extends JFrame implements ActionListener {

	private JFrame frame;
	private JLabel spielerLbl, dealerLbl, bankLbl, einsatzLbl, zählstrategie;
	private Blackjack blackjack;
	private boolean bereit = false;
	private JButton ziehen, stand, neuesSpiel, ausloeschen, einsatzBestaetigt;
	private Render render;
	private MouseListener mouse;
	private boolean bestaetigt = false;

	public Gui(Blackjack blackjack) {
		this.blackjack = blackjack;
		init();
		bereit = true;
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
		spielerLbl.setFont(new Font("Serif", Font.PLAIN, 24));
		dealerLbl = new JLabel();
		dealerLbl.setBounds(550, 250, 200, 20);
		dealerLbl.setFont(new Font("Serif", Font.PLAIN, 24));

		zählstrategie = new JLabel("<html>2,3,4,5,6 = +1 <br> 7,8,9 = 0 </br> <br>J,D,K,A = -1</br> <br></br><br>Aktueller Wert: 0</br></html>");
		zählstrategie.setBounds(800, 20, 300, 250);
		zählstrategie.setFont(new Font("Serif", Font.PLAIN, 24));
		zählstrategie.setOpaque(true);
		zählstrategie.setBackground(Color.lightGray);
		zählstrategie.setVisible(false);

		bankLbl = new JLabel("Bank: 1000$");
		bankLbl.setBounds(50, 850, 200, 50);
		bankLbl.setFont(new Font("Serif", Font.PLAIN, 36));
		einsatzLbl = new JLabel("Einsatz: 0$");
		einsatzLbl.setBounds(500,525, 200, 50);
		einsatzLbl.setFont(new Font("Serif", Font.PLAIN, 24));

		ziehen = new JButton("Karte Ziehen");
		ziehen.setBounds(850, 300, 200, 100);
		ziehen.addActionListener(this);
		ziehen.setVisible(true);

		stand = new JButton("Stehen bleiben");
		stand.addActionListener(this);
		stand.setBounds(850, 500, 200, 100);
		stand.setVisible(true);

		neuesSpiel = new JButton("Neues Spiel");
		neuesSpiel.setBounds(850, 800, 200, 100);
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
		ziehen.setEnabled(false);

		frame.add(spielerLbl);
		frame.add(dealerLbl);
		frame.add(zählstrategie);
		frame.add(einsatzLbl);
		frame.add(bankLbl);
		frame.add(ausloeschen);
		frame.add(stand);
		frame.add(neuesSpiel);
		frame.add(ziehen);
		frame.add(einsatzBestaetigt);
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
		if (bestaetigt) {
			for (Spieler sp : blackjack.getSpieler()) {
				sp.render(g);
			}
		}
		blackjack.render(g);
	}

	public boolean isBereit() {
		return bereit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ziehen == e.getSource()) {
			blackjack.karteZiehen();
		}
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
			ziehen.setEnabled(false);
			einsatzBestaetigt.setEnabled(true);
			ausloeschen.setEnabled(true);
			dealerLbl.setText("");
			spielerLbl.setText("");
			einsatzLbl.setText("Einsatz: 0$");
		}
		if (einsatzBestaetigt == e.getSource()) {
			stand.setEnabled(true);
			ziehen.setEnabled(true);
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
		ziehen.setEnabled(false);
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
