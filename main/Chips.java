package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Chips {
	private BufferedImage tex;
	private static int width, height; //Klassenattribute
	private int x, y;
	private Rectangle hitbox;
	private int wert;

	public Chips(BufferedImage tex, int x, int y, int wert) {
		this.tex = tex;
		this.wert = wert;
		this.x = x;
		this.y = y;
		width = 200;
		height = 175;
		hitbox = new Rectangle(x, y, width, height);
	}


	public void render(Graphics g) {
		g.drawImage(tex, x, y, width, height, null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(hitbox);
	}

	public int trefferBoxGetroffen(int x, int y) {
		Rectangle rect = new Rectangle(x, y, 1, 1);
		if (hitbox.intersects(rect)) {
//			System.out.println("Getroffen");
			return wert;
		}
		return 0;
	}

	public int getWert() {
		return wert;
	}
}