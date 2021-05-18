package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Blackjack {

	private ArrayList<Deck> deck;
	private ArrayList<Spieler> spieler;
	private ArrayList<Chips> chips;
	private Gui gui;
	private Texturen texturen;
	private static Graphics g;

	public static void main(String[] args) {
		new Blackjack();
	}

	public Blackjack() {
		init();
		neuesSpiel();
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
//		gui.repaint();
	}

	private void init() {
		gui = new Gui(this);
		deck = new ArrayList<Deck>();
		spieler = new ArrayList<Spieler>();
		chips = new ArrayList<Chips>();
		texturen = new Texturen(this);

	}

	private void neuesSpiel() {
		deck.add(new Deck(this, 0));
	}

	public BufferedImage[] gibTex() {
		return texturen.getBuffi();
	}
}
