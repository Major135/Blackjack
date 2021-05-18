package main;

import java.util.ArrayList;

public class Hand {
	private Spieler spieler;
	private ArrayList<Karten> handkarten;

	public Hand(Spieler spieler) {
		this.spieler = spieler;
		init();
	}

	private void init() {
		handkarten = new ArrayList<Karten>();
		
	}
}
