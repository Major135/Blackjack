package main;

public class Karten {

	private String muster;
	private String name;
	private int wert;
	private int deckid;

	public Karten(String pMuster, String pName, int pWert, int pDeckId) {
		this.muster = pMuster;
		this.name = pName;
		this.wert = pWert;
		this.deckid = pDeckId;
	}

	public String getMuster() {
		return muster;
	}

	public String getName() {
		return name;
	}

	public int getWert() {
		return wert;
	}

	public int getDeckid() {
		return deckid;
	}

	public void setWert(int wert) {
		if (name == "ASS" && wert == 11 || wert == 1) {
			this.wert = wert;
		}
	}

}
