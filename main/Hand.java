package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Hand {
	private ArrayList<Karten> handkarten;
	private Spieler spieler;
	private ArrayList<Deck> deck;

	public Hand(Spieler spieler, ArrayList<Deck> deck) {
		this.spieler = spieler;
		this.deck = deck;
		init();
		erstelleHand();
	}

	private void init() {
		handkarten = new ArrayList<Karten>();
	}

	private void erstelleHand() {
		for (int i = 0; i < 2; i++) {
			handkarten.add(deck.get(0).getKarte(i));
//			System.out.println(handkarten.get(i).getWert());
			System.out.println(handkarten.get(i).getName());
		}
	}

	public void render(Graphics g) {
		int x = 220;
		for (Karten hk : handkarten) {
			g.drawImage(hk.getTex(), x+=20, 400, 125, 181, null);
		}
	}

	private void karteDazu() {
		// Zum Kartenziehen
	}

}
