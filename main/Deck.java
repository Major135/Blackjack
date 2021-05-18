package main;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Karten> karte;
	private Blackjack blackjack;
	private int deckid;
	private boolean voll;

	public Deck(Blackjack blackjack, int deckid) {
		this.blackjack = blackjack;
		this.deckid = deckid;
		init();
//		mischeln();
	}

	private void init() {
		karte = new ArrayList<Karten>();
		for (int i = 2; i < 11; i++) {
			karte.add(new Karten("Pik", "Pik " + i, i, deckid));
		}
		for (int i = 2; i < 11; i++) {
			karte.add(new Karten("Karo", "Karo " + i, i, deckid));
		}
		for (int i = 2; i < 11; i++) {
			karte.add(new Karten("Herz", "Herz " + i, i, deckid));
		}
		for (int i = 2; i < 11; i++) {
			karte.add(new Karten("Kreuz", "Kreuz " + i, i, deckid));
		}
		// Bube
		karte.add(new Karten("Pik", "Bube", 10, deckid));
		karte.add(new Karten("Karo", "Bube", 10, deckid));
		karte.add(new Karten("Herz", "Bube", 10, deckid));
		karte.add(new Karten("Kreuz", "Bube", 10, deckid));
		// Dame
		karte.add(new Karten("Pik", "Dame", 10, deckid));
		karte.add(new Karten("Karo", "Dame", 10, deckid));
		karte.add(new Karten("Herz", "Dame", 10, deckid));
		karte.add(new Karten("Kreuz", "Dame", 10, deckid));
		// König
		karte.add(new Karten("Pik", "König", 10, deckid));
		karte.add(new Karten("Karo", "König", 10, deckid));
		karte.add(new Karten("Herz", "König", 10, deckid));
		karte.add(new Karten("Kreuz", "König", 10, deckid));
		// Ass - ozial 4 Life by JU
		karte.add(new Karten("Pik", "ASS", 11, deckid));
		karte.add(new Karten("Karo", "ASS", 11, deckid));
		karte.add(new Karten("Herz", "ASS", 11, deckid));
		karte.add(new Karten("Kreuz", "ASS", 11, deckid));

	}

	public void mischeln() {
		Karten sdKarte;
		for (int i = 0; i < 200; i++) {
			int a = (int) (Math.random() * 52);
			int b = (int) (Math.random() * 52);
			while (a == b) {
				a = (int) (Math.random() * 52);
				b = (int) (Math.random() * 52);
			}
			sdKarte = karte.get(a);
			karte.set(a, karte.get(b));
			karte.set(b, sdKarte);
		}
		for (Karten k : karte) {
//			System.out.println("Dein Kartenwert: " + k.getWert());
//			System.out.println("Dein Kartenmuster: " + k.getMuster());
			System.out.println("Dein KartenName: " + k.getName() + "" + k.getMuster());
		}

	}

	public void pruefeVoll() {
		if (karte.size() > 1) {
			voll = true;
		} else {
			voll = false;
			auffuellen();
		}
	}

	public void auffuellen() {
			//Vom Ablagestapel auffüllen ins richtige Deck
	}

	public int getDeckid() {
		return deckid;
	}
}























