package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spieler {
	private String name;
	private int spielerId;
	private Hand hand;
	private ArrayList<Deck> deck;
	private Blackjack blackjack;

	public Spieler(Blackjack blackjack, int pSpielerId, String pName, ArrayList<Deck> deck) {
		this.spielerId = pSpielerId;
		this.name = pName;
		this.deck = deck;
		this.blackjack = blackjack;
		hand = new Hand(this, deck);
	}

	public void render(Graphics g) {
		hand.render(g);
	}

	public void ziehen(int anzahl) {
		// Hand zieht
	}

}
