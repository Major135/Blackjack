package main;

import java.util.ArrayList;

public class Dealer extends Spieler {

	public Dealer(Blackjack blackjack, int pSpielerId, String pName, ArrayList<Deck> deck, int pAnzahl) {
		super(blackjack, pSpielerId, pName, deck, pAnzahl);
//		System.out.println("Dealerhand");
		y = 50;
		anzahl = 1;
	}

}
