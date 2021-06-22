package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Blackjack {

	private ArrayList<Deck> deck;
	private ArrayList<Spieler> spieler;
	private Chips[] chips = new Chips[3];
	private Gui gui;
	private Texturen texturen;
	private static Graphics g;
	private Karten k;
	private Deck pDeck;
	private Dealer dealer;
	private boolean gewonnen;
	private int zwischenSumme;

	public static void main(String[] args) {
		new Blackjack();
	}

	public Blackjack() {
		init();
		spielStart();
		System.out.println("Break");
	}

	private void init() {
		texturen = new Texturen(this);
		deck = new ArrayList<Deck>();
		chips[0] = new Chips(texturen.getChip50(), 50, 0, 50);
		chips[1] = new Chips(texturen.getChip100(), 50, 200, 100);
		chips[2] = new Chips(texturen.getChip500(), 50, 400, 500);
		spieler = new ArrayList<Spieler>();
		gui = new Gui(this);
		zwischenSumme = 0;
	}

	private void neuerSpieler() {
		spieler.add(new Spieler(this, 1, "Hans", deck, 2));
	}

	private void spielStart() {
		deck.add(new Deck(this, 0, texturen));
		pDeck = deck.get(0);
		dealer = new Dealer(this, 0, "Dealer", deck, 1);
		spieler.add(dealer);
		neuerSpieler();

	}

	public void neuesSpiel() {
		for (Spieler sp : spieler) {
			sp.neuesSpiel();
			sp.setStand(false);
			gui.setEnable(false);
		}
	}

	public void mousePressed(int x, int y) {
		for (int i = 0; i < chips.length; i++) {
			spieler.get(1).einsatzMachen(chips[i].trefferBoxGetroffen(x, y));
		}

	}

	public void updateLabel(int pId, int pSumme) {
		if (gui.isEnable()) {
			if (pId == 0) {
				setDealerLabel(pSumme);
			} else {
				setSpielerLabel(pSumme);
			}
		}
	}

	private void ermittleSieger() {
		if (spieler.get(0).getSumme() <= 21 && spieler.get(0).getSumme() > spieler.get(1).getSumme()) {
			gui.getDealerLbl().setText("Gewonnen " + String.valueOf(spieler.get(0).getSumme()));
			gui.getSpielerLbl().setText("Verloren " + String.valueOf(spieler.get(1).getSumme()));
			setGewonnen(false);
			spieler.get(1).chipsGewonnen();
		}
		if (spieler.get(0).getSumme() == spieler.get(1).getSumme()) {
			gui.getDealerLbl().setText("Unentschieden " + String.valueOf(spieler.get(0).getSumme()));
			gui.getSpielerLbl().setText("Unentschieden " + String.valueOf(spieler.get(1).getSumme()));
			spieler.get(1).chipsGewonnen();
		}
		if (spieler.get(0).getSumme() < spieler.get(1).getSumme() && spieler.get(1).getSumme() <= 21
				|| spieler.get(0).getSumme() > 21) {
			gui.getDealerLbl().setText("Verloren " + String.valueOf(spieler.get(0).getSumme()));
			gui.getSpielerLbl().setText("Gewonnen " + String.valueOf(spieler.get(1).getSumme()));
			setGewonnen(true);
			spieler.get(1).chipsGewonnen();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < chips.length; i++) {
			chips[i].render(g);
		}
		if (!gui.isEnable()) {
			g.drawImage(texturen.getBackside(), 485, 50, 125, 181, null);
			g.drawImage(texturen.getBackside(), 485, 700, 125, 181, null);
			g.drawImage(texturen.getBackside(), 505, 700, 125, 181, null);
		}
	}

	public ArrayList<Spieler> getSpieler() {
		return spieler;
	}

	public void zähleKarten() {
		System.out.println("Vorheriges Kartenzählen : "+zwischenSumme);
		for (Spieler sp : spieler) {
			zwischenSumme += sp.handZählen();
		}
		System.out.println("Kartenzählen : "+zwischenSumme);
		
	}

	public void einsatzBestaetigt() {
		spieler.get(1).gesamtEinsatz();
		spieler.get(0).updateLabel();
		spieler.get(1).updateLabel();
	}

	public Deck getDeck(int pos) {
		return deck.get(pos);
	}

	public Dealer getDealer(int pos) {
		return dealer;
	}

	public void karteZiehen() {
		spieler.get(1).karteZiehen();
	}

	private void setSpielerLabel(int summe) {
		gui.getSpielerLbl().setText(String.valueOf(summe));
	}

	private void setDealerLabel(int summe) {
		gui.getDealerLbl().setText(String.valueOf(summe));
	}

	public void stehenBleiben() {
		// Ab hier zieht der Dealer und es können keine Karten mehr gezogen werden
		spieler.get(1).setStand(true);
		spieler.get(0).setStand(false);
		spieler.get(0).zug();
		ermittleSieger();
	}

	public boolean isGewonnen() {
		return gewonnen;
	}

	public void setGewonnen(boolean gewinner) {
		this.gewonnen = gewinner;
	}

}
