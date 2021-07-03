package main;

import java.util.ArrayList;

public class Ablagestapel {
	private Deck deck;
	private ArrayList<Karten> ablage;
	
	public Ablagestapel(Deck deck) {
			this.deck = deck;
			ablage = new ArrayList<Karten>();
	}
	
	public void auffuellen(Karten k) {
			ablage.add(k);
//			System.out.println("SIZE"+ablage.size());
	}

	public ArrayList<Karten> getAblage() {
		return ablage;
	}
	
	public Karten getAblage(int pos) {
		return ablage.get(pos);
	}
	
	
}
