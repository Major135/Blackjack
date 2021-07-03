package main;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Karten> karte;
	private Ablagestapel ablage;
	private Blackjack blackjack;
	private int deckid;
	private boolean voll;
	private Texturen tex;
	private String[] bez = { "Herz", "Kreuz", "Karo", "Pik" };

	public Deck(Blackjack blackjack, int deckid, Texturen tex) {
		this.blackjack = blackjack;
		this.deckid = deckid;
		this.tex = tex;
		init();
		mischeln();
	}

	private void init() {
		int x = 40;
		int y = 100;
		karte = new ArrayList<Karten>();
		ablage = new Ablagestapel(this);
		int ctr = 0;
		for (int i = 10; i > 1; i--) {
			karte.add(new Karten("Herz", "Herz " + i, i, deckid, tex.getHerz(ctr), x += 20, y));
			ctr++;
		}
		for (int i = 10; i > 1; i--) {
			karte.add(new Karten("Kreuz", "Kreuz " + i, i, deckid, tex.getKreuz(ctr), x += 20, y));
			ctr++;
		}
		for (int i = 10; i > 1; i--) {
			karte.add(new Karten("Karo", "Karo " + i, i, deckid, tex.getKaro(ctr), x += 20, y));
			ctr++;
		}
		for (int i = 10; i > 1; i--) {
			karte.add(new Karten("Pik", "Pik " + i, i, deckid, tex.getPik(ctr), x += 20, y));
			ctr++;
		}
		// Bube
		int alla = 36;
		for (int i = 0; i < bez.length; i++) {
			karte.add(new Karten(bez[i], "ASS", 11, deckid, tex.getBube(alla), x += 20, y));
			alla += 4;

		}
		alla = 37;
		for (int i = 0; i < bez.length; i++) {
			karte.add(new Karten(bez[i], "Bube", 10, deckid, tex.getBube(alla), x += 20, y));
			alla += 4;

		}
		alla = 38;
		for (int i = 0; i < bez.length; i++) {
			karte.add(new Karten(bez[i], "König", 10, deckid, tex.getBube(alla), x += 20, y));
			alla += 4;

		}
		alla = 39;
		for (int i = 0; i < bez.length; i++) {
			karte.add(new Karten(bez[i], "Dame", 10, deckid, tex.getBube(alla), x += 20, y));
			alla += 4;

		}
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
//		for (Karten k : karte) {
//			System.out.println("Dein Kartenwert: " + k.getWert());
//			System.out.println("Dein Kartenmuster: " + k.getMuster());
//			System.out.println("Dein KartenName: " + k.getName() + "" + k.getMuster());
//		}

	}

	public void pruefeVoll() {
		if (karte.isEmpty()) {
//			System.out.println("KARTENLEEER");
			for (int i = 0; i < ablage.getAblage().size(); i++) {
				karte.add(ablage.getAblage(i));   
			}
		}
	}

	public void deleteKarte(int pos) {
		if (karte.size() >= pos) {
			karte.remove(pos);
		}
	}

	public void auffuellen(Karten karten) {
		ablage.auffuellen(karten);
	}

	public int getDeckid() {
		return deckid;
	}

	public Karten getKarte(int pos) {
		return karte.get(pos);
	}

	public ArrayList<Karten> getKarten() {
		return karte;
	}

}
