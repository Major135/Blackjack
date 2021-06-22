package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spieler {
	protected String name;
	protected int spielerId;
	protected Hand hand;
	protected ArrayList<Deck> deck;
	protected Blackjack blackjack;
	protected int x, y;
	protected int anzahl;
	protected boolean stand;
	private int einsatz, geld, gesamteinsatz;
	private Z�hlstrategie z�hl;
	private int z�hlsumme;

	public Spieler(Blackjack blackjack, int pSpielerId, String pName, ArrayList<Deck> deck, int pAnzahl) {
		this.spielerId = pSpielerId;
		this.name = pName;
		this.deck = deck;
		this.blackjack = blackjack;
		this.anzahl = pAnzahl;
		init();
	}

	public void init() {
		hand = new Hand(this, deck);
		z�hl = new Z�hlstrategie(hand);
		y = 700;
		geld = 1000;
		gesamteinsatz = 0;
		ziehen(anzahl);
		stand = false;
	}

	public void zug() {
		while (hand.getSumme() < 17) {
			System.out.println(hand.getSumme());
			karteZiehen();
		}

	}

	public void einsatzMachen(int einsatz) {
		gesamteinsatz += einsatz;
		geld -= einsatz;
		System.out.println("GELD " + geld);
	}

	public void gesamtEinsatz() {
		System.out.println("Gesamtzeinsatz " + gesamteinsatz);
	}

	public void chipsGewonnen() {
		if (blackjack.isGewonnen()) {
			geld += gesamteinsatz * 2;
			gesamteinsatz = 0;
			System.out.println("Neuer Kontostand " + geld);
		}
		if (!blackjack.isGewonnen()) {
			gesamteinsatz = 0;
			System.out.println("Neuer Kontostand " + geld);
		}
	}


	public void updateLabel() {
		blackjack.updateLabel(spielerId, hand.getSumme());
	}

	public void render(Graphics g) {
		hand.render(g, y);
//		System.out.println("Spielerhand");
	}

	public void ziehen(int anzahl) {
//		System.out.println("ziehen");
		hand.erstelleHand(anzahl);
	}

	public void karteZiehen() {
		hand.assWert();
		if (!stand) {
			hand.karteDazuSpieler();

		} else {
			hand.karteDazuDealer();
		}
		hand.assWert();

	}

	public void neuesSpiel() {
		hand.hkloeschen();
		ziehen(anzahl);
	}

	public int handZ�hlen() {
		z�hlsumme = 0;
		z�hl.gibKartenWerte();
		return z�hlsumme += z�hl.getZ�hler();
	}

	public int getSpielerId() {
		return spielerId;
	}

	public int getSumme() {
		return hand.getSumme();
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public boolean isStand() {
		return stand;
	}

	public void setStand(boolean stand) {
		this.stand = stand;
	}

	public int getZ�hlsumme() {
		return z�hlsumme;
	}
}
