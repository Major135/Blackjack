package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Blackjack {

	private ArrayList<Deck> deck;
	private ArrayList<Spieler> spieler;
	private Chips[] chips = new Chips[3];
	private Zählstrategie zähl;
	private Gui gui;
	private Texturen texturen;
	private static Graphics g;
	private Karten k;
	private Deck pDeck;
	private Dealer dealer;
	private boolean gewonnen, zählsichtbar;
	private int zwischenSumme;

	public static void main(String[] args) {
		new Blackjack();
	}

	public Blackjack() {
		init();
		spielStart();
	}

	private void init() {
		texturen = new Texturen(this);
		deck = new ArrayList<Deck>();
		chips[0] = new Chips(texturen.getChip50(), 50, 0, 50);
		chips[1] = new Chips(texturen.getChip100(), 50, 200, 100);
		chips[2] = new Chips(texturen.getChip500(), 50, 400, 500);
		zähl = new Zählstrategie(null, this, texturen.getEinstellungen(), 1000, 20);
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
			gui.setBestaetigt(false);
		}
		schließeZähl();
	}

	public void mousePressed(int x, int y) {
		if (!gui.isBestaetigt()) {
			for (int i = 0; i < chips.length; i++) {
				setEinsatzLabel(spieler.get(1).einsatzMachen(chips[i].trefferBoxGetroffen(x, y)));
				setBankLabel(spieler.get(1).getGeld());
			}
		}else {
			zähl.trefferBoxGetroffen(x, y);
		}

	}

	public void updateLabel(int pId, int pSumme) {
		if (gui.isBestaetigt()) {
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
			gewonnenLabel();
			extracted();
		}
		setBankLabel(spieler.get(1).getGeld());
	}

//STRG 1 für Extracted
	private void extracted() {
		setGewonnen(true);
		spieler.get(1).chipsGewonnen();
	}

	private void gewonnenLabel() {
		gui.getDealerLbl().setText("Verloren " + String.valueOf(spieler.get(0).getSumme()));
		gui.getSpielerLbl().setText("Gewonnen " + String.valueOf(spieler.get(1).getSumme()));
	}

	public void render(Graphics g) {
		for (int i = 0; i < chips.length; i++) {
			chips[i].render(g);
		}
		if (!gui.isBestaetigt()) {
			g.drawImage(texturen.getBackside(), 485, 50, 125, 181, null);
			g.drawImage(texturen.getBackside(), 485, 700, 125, 181, null);
			g.drawImage(texturen.getBackside(), 505, 700, 125, 181, null);
		}
		zähl.render(g);
	}

	public ArrayList<Spieler> getSpieler() {
		return spieler;
	}

	public void zähleKarten() {
		System.out.println("Vorheriges Kartenzählen : " + zwischenSumme);
		for (Spieler sp : spieler) {
			zwischenSumme += sp.handZählen();
		}
		System.out.println("Kartenzählen : " + zwischenSumme);
		if (zählsichtbar == true) {
			updateZähl();
		}

	}

	public void einsatzBestaetigt() {
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

	private void setEinsatzLabel(int summe) {
		gui.getEinsatzLbl().setText("Einsatz: " + String.valueOf(summe) + "$");
	}

	private void setBankLabel(int summe) {
		gui.getBankLbl().setText("Bank: " + String.valueOf(summe) + "$");
	}

	public void einsatzAusloeschen() {
		spieler.get(1).setGeld(spieler.get(1).getGeld() + spieler.get(1).getGesamteinsatz());
		spieler.get(1).setGesamteinsatz(0);
		setBankLabel(spieler.get(1).getGeld());
		setEinsatzLabel(spieler.get(1).getGesamteinsatz());
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

	public void updateZähl() {
		String text = "<html>2,3,4,5,6 = +1 <br> 7,8,9 = 0 </br> <br>J,D,K,A = -1</br> <br></br><br>Aktueller Wert:";
		gui.getZählstrategie().setText(text + zwischenSumme);
		gui.getZählstrategie().setVisible(true);
		zählsichtbar = true;
	}

	private void schließeZähl() {
		gui.getZählstrategie().setVisible(false);
		zählsichtbar = false;
	}

}
