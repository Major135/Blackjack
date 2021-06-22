package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Karten {

	private String muster;
	private String name;
	private int wert;
	private int deckid;
	private BufferedImage tex;
	private int x, y, width, height;

	public Karten(String pMuster, String pName, int pWert, int pDeckId, BufferedImage ptex, int x, int y) {
		this.muster = pMuster;
		this.name = pName;
		this.wert = pWert;
		this.deckid = pDeckId;
		this.tex = ptex;
		this.x = x;
		this.y = y;
		this.width = 125;
		this.height = 181;
	
	}

	public void render(Graphics g) {
		g.drawImage(tex, x, y, width, height, null);
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

	public BufferedImage getTex() {
		return tex;
	}

	public void setTex(BufferedImage tex) {
		this.tex = tex;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
