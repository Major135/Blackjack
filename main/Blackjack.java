package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Blackjack {

	private ArrayList<Deck> deck;
	private ArrayList<Spieler> spieler;
	private ArrayList<Chips> chips;
	private Gui gui;
	private Texturen texturen;
	private static Graphics g;
	private Karten k;
	private Deck pDeck;

	public static void main(String[] args) {
		new Blackjack();
	}

	public Blackjack() {
		init();
		neuesSpiel();
		neuerSpieler();
		while (true) {
			if (gui.isBereit()) {
				update();
				break;
			}
		}
		System.out.println("Break");

	}

	public void update() {
		gui.render(g);
	}

	private void init() {
		deck = new ArrayList<Deck>();
		gui = new Gui(this);
		spieler = new ArrayList<Spieler>();
		chips = new ArrayList<Chips>();
		texturen = new Texturen(this);
	}

	private void neuesSpiel() {
		deck.add(new Deck(this, 0, texturen));
		pDeck = deck.get(0);
	}

	private void neuerSpieler() {
		spieler.add(new Spieler(this, 0, "Hans", deck));
	}

	public Spieler getSpieler(int pos) {
		return spieler.get(pos);
	}

	public BufferedImage[] gibTex() {
		return texturen.getBuffi();
	}

	public ArrayList<Karten> gibBild() {
		return pDeck.getKarten();
	}

	public Deck getDeck(int pos) {
		return deck.get(pos);
	}

}
