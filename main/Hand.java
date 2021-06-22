package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Hand {
	private ArrayList<Karten> handkarten;
	private Spieler spieler;
	private ArrayList<Deck> deck;
	private int counter = 2;
	private int summe = 0;
	private boolean assWert = false;


	public Hand(Spieler spieler, ArrayList<Deck> deck) {
		this.spieler = spieler;
		this.deck = deck;
		init();
	}

	private void init() {
		handkarten = new ArrayList<Karten>();

	}

	public void erstelleHand(int anz) {
		for (int i = 0; i < anz; i++) {
			handkarten.add(deck.get(0).getKarte(0));
			deck.get(0).deleteKarte(0);
//			System.out.println(handkarten.get(i).getWert());
			System.out.println(handkarten.get(i).getName());
		}
		berechneSumme();
	}

	public void render(Graphics g, int y) {
		int x = 485;
		for (Karten hk : handkarten) {
			g.drawImage(hk.getTex(), x += 20, y, 125, 181, null);
		}
	}

	public void karteDazuSpieler() {
		if (summe < 21) {
			summe = 0;
			handkarten.add(deck.get(0).getKarte(0));
			berechneSumme();
			System.out.println("SpielerSumme" + summe);
			deck.get(0).deleteKarte(0);
			counter++;
		}
	}
	
	public void karteDazuDealer() {
		if (summe < 17 || summe > 21) {
			summe = 0;
			handkarten.add(deck.get(0).getKarte(0));
			berechneSumme();
			System.out.println("DealerSumme" + summe);
			deck.get(0).deleteKarte(0);
			counter++;
		}
	}
	
	
	private void berechneSumme() {
		for (Karten hk : handkarten) {
			summe += hk.getWert();
		}
		spieler.updateLabel();
	}

	public void assWert() {
		for (Karten hk : handkarten) {
			if (hk.getWert() == 11 && summe > 21 && !spieler.isStand()) {
				hk.setWert(1);
				System.out.println("Wert verändert für Spieler");
				summe = 0;
				berechneSumme();
				assWert = true;
			}
			if (hk.getWert() == 11 && summe > 21 && spieler.isStand()) {
				hk.setWert(1);
				System.out.println("Wert verändert für Dealer");
				summe = 0;
				berechneSumme();
				assWert = true;
			}
		}
	}

	public void hkloeschen() {
		handkarten.clear();
		summe = 0;
	}

	public int getSumme() {
		return summe;
	}
	
	public ArrayList<Karten> getHand() {
		return handkarten;
	}

	public boolean isAssWert() {
		return assWert;
	}

	public void setAssWert(boolean assWert) {
		this.assWert = assWert;
	}

}
