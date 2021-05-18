package main;

public class Spieler {
	private String name;
	private int spielerId;
	private Hand hand;

	public Spieler(int pSpielerId, String pName) {
		this.spielerId = pSpielerId;
		this.name = pName;
		hand = new Hand(this);
	}

	public void ziehen(int anzahl) {
		// Hand zieht
	}

}
