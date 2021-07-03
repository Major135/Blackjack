package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Z�hlstrategie {

	private Hand hand;
	private int z�hler;
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle hitbox;
	private BufferedImage tex;
	private Rectangle rect;
	private Blackjack blackjack;
	private boolean da = false;

	public Z�hlstrategie(Hand hand, Blackjack blackjack, BufferedImage tex, int x, int y) {
		this.hand = hand;
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.blackjack = blackjack;
		width = 75;
		height = 75;
		hitbox = new Rectangle(x, y, width, height);
//		System.out.println("Z�hlstrategie erstellt");

	}

	public int gibKartenWerte() {
		z�hler = 0;
		for (int i = 0; i < hand.getHand().size(); i++) {
			if (hand.getHand().get(i).getWert() < 7 && !hand.isAssWert()) {
				z�hler += 1;
				System.out.println("Z�hlStrategie KLEINER 7 (+1) " + z�hler);
			}
			if (hand.getHand().get(i).getWert() > 9) {
				z�hler -= 1;
				System.out.println("Z�hlStrategie Gr��er 9 (-1) " + z�hler);
			}
			if (hand.isAssWert()) {
				z�hler -= 1;
				hand.setAssWert(false);
				System.out.println("ASSWERT (-1)" + z�hler);
			}
		}
		return z�hler;
	}

	public void trefferBoxGetroffen(int x, int y) {
		rect = new Rectangle(x-10, y-30, 1, 1);
		da = true;
		if (hitbox.intersects(rect)) {
			blackjack.updateZ�hl();
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex, x, y, width, height, null);
		Graphics2D g2d = (Graphics2D) g;
//		g2d.draw(hitbox); // Hitbox von Z�hlstrategie Button
		if (da) {
//			g2d.draw(rect);  // Mousecursor 
		}
	}

	public int getZ�hler() {
		return z�hler;
	}

}
