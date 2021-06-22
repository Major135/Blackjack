package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JFrame implements ActionListener {

	private JFrame frame;
	private JLabel spielerLbl;
	private JLabel dealerLbl;
	private Blackjack blackjack;
	private boolean bereit = false;
	private JButton ziehen;
	private JButton stand;
	private JButton neuesSpiel;
	private JButton einsatzBestaetigt;
	private Render render;
	private MouseListener mouse;
	private boolean enable = false;;

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
		spielerLbl.setBounds(580, 620, 100, 20);
		dealerLbl = new JLabel();
		dealerLbl.setBounds(580, 250, 100, 20);

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
		neuesSpiel.setEnabled(false);
		stand.setEnabled(false);
		ziehen.setEnabled(false);

		frame.add(spielerLbl);
		frame.add(dealerLbl);
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
		if (enable) {
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
			neuesSpiel.setEnabled(true);
			ziehen.setEnabled(false);
			blackjack.zähleKarten();
		}
		if (neuesSpiel == e.getSource()) {
			System.out.println("Neues Spiel");
			blackjack.neuesSpiel();
			neuesSpiel.setEnabled(false);
			stand.setEnabled(false);
			ziehen.setEnabled(false);
			einsatzBestaetigt.setEnabled(true);
			dealerLbl.setText("");
			spielerLbl.setText("");
		}
		if (einsatzBestaetigt == e.getSource()) {
			stand.setEnabled(true);
			ziehen.setEnabled(true);
			einsatzBestaetigt.setEnabled(false);
			enable = true;
			blackjack.einsatzBestaetigt();
		}

	}

	public JLabel getSpielerLbl() {
		return spielerLbl;
	}

	public JLabel getDealerLbl() {
		return dealerLbl;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
