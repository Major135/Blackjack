package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Blackjack {

	private ArrayList<Deck> deck;
	private ArrayList<Spieler> spieler;
	private ArrayList<Chips> chipliste;
	private Chips[] chips = new Chips[3];
	private Z�hlstrategie z�hl;
	private Gui gui;
	private Texturen texturen;
	private static Graphics g;
	private Karten k;
	private Deck pDeck;
	private Dealer dealer;
	private boolean gewonnen, z�hlsichtbar;
	private int zwischenSumme;
	private int x1, y50, y100, y500;
	private Ablagestapel ablage;
	private int allIn;

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
		chipliste = new ArrayList<Chips>();
		spieler = new ArrayList<Spieler>();
		z�hl = new Z�hlstrategie(null, this, texturen.getZ�hlstrategie(), 1000, 50);
		gui = new Gui(this);
		zwischenSumme = 0;
		y50 = 425;
		y100 = 425;
		y500 = 425;
		x1 = 485;
	}

	private void spielStart() {
		deck.add(new Deck(this, 0, texturen));
		pDeck = deck.get(0);
		dealer = new Dealer(this, 0, "Dealer", deck, 1);
		spieler.add(dealer);
		neuerSpieler();

	}

	private void neuerSpieler() {
		spieler.add(new Spieler(this, 1, "Hans", deck, 2));
	}

	public void neuesSpiel() {
		for (Spieler sp : spieler) {
			sp.neuesSpiel();
			sp.setStand(false);
			gui.setBestaetigt(false);
		}
		schlie�eZ�hl();
		chipClear();
	}

	public void mousePressed(int x, int y) {
		if (!gui.isBestaetigt()) {
			for (int i = 0; i < chips.length; i++) {
				if (chips[i].trefferBoxGetroffen(x, y) == 50 && spieler.get(1).getGeld() >= 50) {
					chipliste.add(new Chips(texturen.getChip50(), x1 - 100, y50 -= 10, 0));

				}
				if (chips[i].trefferBoxGetroffen(x, y) == 100 && spieler.get(1).getGeld() >= 100) {
					chipliste.add(new Chips(texturen.getChip100(), x1 - 25 + 20, y100 -= 10, 0));
				}
				if (chips[i].trefferBoxGetroffen(x, y) == 500 && spieler.get(1).getGeld() >= 500) {
					chipliste.add(new Chips(texturen.getChip500(), x1 + 50 + 40, y500 -= 10, 0));
				}
				setEinsatzLabel(spieler.get(1).einsatzMachen(chips[i].trefferBoxGetroffen(x, y)));
				setBankLabel(spieler.get(1).getGeld());
			}
			if (!chipliste.isEmpty()) {
				chipliste.get(chipliste.size() - 1).setHeight(105);
				chipliste.get(chipliste.size() - 1).setWidth(120);
			}
		} else {
			z�hl.trefferBoxGetroffen(x, y);
			gui.trefferBoxGetroffen(x, y);
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
		if (spieler.get(1).getSumme() == 21) {
			gewonnen();
		}
		if (spieler.get(0).getSumme() <= 21 && spieler.get(0).getSumme() > spieler.get(1).getSumme()
				|| spieler.get(0).getSumme() <= 21 && spieler.get(1).getSumme() > 21) {
			verloren();
		}
		if (spieler.get(0).getSumme() == spieler.get(1).getSumme() && spieler.get(1).getSumme() < 21) {
			unentschieden();
			einsatzAusloeschen();
		}
		if (spieler.get(0).getSumme() < spieler.get(1).getSumme() && spieler.get(1).getSumme() <= 21) {
			gewonnen();
		}
		if (spieler.get(0).getSumme() > 21 && spieler.get(1).getSumme() <= 21) {
			gewonnen();
		}
		if (spieler.get(0).getSumme() > 21 && spieler.get(1).getSumme() > 21) {
			unentschieden();
			einsatzAusloeschen();
		}
		setBankLabel(spieler.get(1).getGeld());
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < chips.length; i++) {
			chips[i].render(g);
		}
		for (Chips chips : chipliste) {
			chips.render(g);
		}
		if (!gui.isBestaetigt()) {
			g.drawImage(texturen.getBackside(), 485, 50, 125, 181, null);
			g.drawImage(texturen.getBackside(), 485, 700, 125, 181, null);
			g.drawImage(texturen.getBackside(), 505, 700, 125, 181, null);
		}
		if (spieler.get(1).getGeld() == 0) {
			g.drawImage(texturen.getBank(), 25, 855, 180, 75, null);
		} else if (spieler.get(1).getGeld() < 1000) {
			g.drawImage(texturen.getBank(), 25, 855, 225, 75, null);
		} else if (spieler.get(1).getGeld() < 10000) {
			g.drawImage(texturen.getBank(), 25, 855, 250, 75, null);
		}
		if (spieler.get(1).getGeld() >= 10000) {
			g.drawImage(texturen.getBank(), 25, 855, 275, 75, null);
		}
		z�hl.render(g);
		switch (zwischenSumme) {
		case 2 , 3: {
			g.drawImage(texturen.getX2(), 300, 870, 50, 50, null);
			break;
		}
		case 4 , 5: {
			g.drawImage(texturen.getX3(), 300, 870, 50, 50, null);
			break;
		}
		case 6 , 7: {
			g.drawImage(texturen.getX4(), 300, 870, 50, 50, null);
			break;
		}
		case 8 , 9: {
			g.drawImage(texturen.getX5(), 300, 870, 50, 50, null);
			break;
		}
		default:
			g.drawImage(texturen.getX1(), 300, 870, 50, 50, null);
			break;
		}
		double rotationRequired = Math.toRadians(90);
		g2d.rotate(rotationRequired, 1000 + 125 / 2, 320 + 181 / 2);
		g2d.translate(950, 320);
		g2d.drawImage(texturen.getBackside(), null, 0, 0);
		g2d.dispose();

	}

	public ArrayList<Spieler> getSpieler() {
		return spieler;
	}

	public void z�hleKarten() {
		System.out.println("Vorheriges Kartenz�hlen : " + zwischenSumme);
		for (Spieler sp : spieler) {
			zwischenSumme += sp.handZ�hlen();
		}
		System.out.println("Kartenz�hlen : " + zwischenSumme);
		if (z�hlsichtbar == true) {
			updateZ�hl();
		}

	}

	public void chipClear() {
		chipliste.clear();
		y50 = 425;
		y100 = 425;
		y500 = 425;
		x1 = 485;
	}

	public void einsatzBestaetigt() {
		spieler.get(0).updateLabel();
		spieler.get(1).updateLabel();
	}

	private void unentschieden() {
		gui.getDealerLbl().setText("Unentschieden " + String.valueOf(spieler.get(0).getSumme()));
		gui.getSpielerLbl().setText("Unentschieden " + String.valueOf(spieler.get(1).getSumme()));
	}

	private void verloren() {
		gui.getDealerLbl().setText("Gewonnen " + String.valueOf(spieler.get(0).getSumme()));
		gui.getSpielerLbl().setText("Verloren " + String.valueOf(spieler.get(1).getSumme()));
		setGewonnen(false);
		spieler.get(1).chipsGewonnen();
	}

//STRG 1 f�r Extracted
	private void gewonnen() {
		gui.getDealerLbl().setText("Verloren " + String.valueOf(spieler.get(0).getSumme()));
		gui.getSpielerLbl().setText("Gewonnen " + String.valueOf(spieler.get(1).getSumme()));
		setGewonnen(true);
		spieler.get(1).chipsGewonnen();
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
		chipClear();
	}

	public void stehenBleiben() {
		// Ab hier zieht der Dealer und es k�nnen keine Karten mehr gezogen werden
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

	public void updateZ�hl() {
		String text = "<html>2,3,4,5,6 = +1 <br> 7,8,9 = 0 </br> <br>J,D,K,A = -1</br> <br></br><br>Aktueller Wert:";
		gui.getZ�hlstrategie().setText(text + zwischenSumme);
		gui.getZ�hlstrategie().setVisible(true);
		z�hlsichtbar = true;
	}

	private void schlie�eZ�hl() {
		gui.getZ�hlstrategie().setVisible(false);
		z�hlsichtbar = false;
	}

	public void setZwischenSumme(int zwischenSumme) {
		this.zwischenSumme = zwischenSumme;
	}

}
