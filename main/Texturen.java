package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Texturen {

	public BufferedImage backside, chip50, chip100, chip500;
	public BufferedImage h10, h9, h8, h7, h6, h5, h4, h3, h2, ah, kh, bh, qh; // Herzkarten
	public BufferedImage k10, k9, k8, k7, k6, k5, k4, k3, k2, ak, bk, kk, qk;// Kreuzkarten
	public BufferedImage ka10, ka9, ka8, ka7, ka6, ka5, ka4, ka3, ka2, aka, bka, kka, qka;// Karokarten
	public BufferedImage p10, p9, p8, p7, p6, p5, p4, p3, p2, ap, bp, kp, qp;// Pikkarten
	private BufferedImage[] buffi = { h10, h9, h8, h7, h6, h5, h4, h3, h2, ah, bh, kh, qh, k10, k9, k8, k7, k6, k5, k4,
			k3, k2, ak, bk, kk, qk, ka10, ka9, ka8, ka7, ka6, ka5, ka4, ka3, ka2, aka, bka, kka, qka, p10, p9, p8, p7,
			p6, p5, p4, p3, p2, ap, bp, kp, qp };
	private char[] bez = { 'a', 'b', 'k', 'q' };
	private Blackjack blackjack;
	private BufferedImage z�hlstrategie;


	public Texturen(Blackjack blackjack) {
		this.blackjack = blackjack;
		holTexturen();
	}

// ASS , BUBE ; K�NIG ; DAME! 10 ,23 ,36, 49
	public void holTexturen() {
//		System.out.println("Texturen holen");
		String speicher;
		URL pic_url = null;
		int z�hler = 0;
		try {
			pic_url = this.getClass().getClassLoader().getResource("karten/backside.png");
			backside = ImageIO.read(pic_url);
			pic_url = this.getClass().getClassLoader().getResource("karten/wuerfel.png");
			z�hlstrategie = ImageIO.read(pic_url);
			pic_url = this.getClass().getClassLoader().getResource("karten/50.png");
			chip50 = ImageIO.read(pic_url);
			pic_url = this.getClass().getClassLoader().getResource("karten/100.png");
			chip100 = ImageIO.read(pic_url);
			pic_url = this.getClass().getClassLoader().getResource("karten/500.png");
			chip500 = ImageIO.read(pic_url);
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "h.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 9;
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "k.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 18;
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "ka.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 27;
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "p.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 36;
			// Herz Bildkarten
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "h.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 40;
			// Kreuz Bildkarten
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "k.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 44;
			// Karo Bildkarten
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "ka.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				z�hler++;
			}
			z�hler = 48;
			// Pik Bildkarten
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "p.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[z�hler] = ImageIO.read(pic_url);
				if (z�hler < 52) {
					z�hler++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 9-12 H 22-25 K 35-38 KA 48-51 Pik
	public BufferedImage getHerz(int pos) {
		return buffi[pos];
	}

	public BufferedImage getKreuz(int pos) {
		return buffi[pos];
	}

	public BufferedImage getKaro(int pos) {
		return buffi[pos];
	}

	public BufferedImage getPik(int pos) {
		return buffi[pos];
	}

	public BufferedImage getBube(int pos) {
		return buffi[pos];
	}

	public BufferedImage getAss(int pos) {
		return buffi[pos];
	}

	public BufferedImage getDame(int pos) {
		return buffi[pos];
	}

	public BufferedImage getK�nig(int pos) {
		return buffi[pos];
	}

	// Nichts
	public BufferedImage[] getBuffi() {
		return buffi;
	}

	public void setBuffi(BufferedImage[] buffi) {
		this.buffi = buffi;
	}

	public BufferedImage getBackside() {
		return backside;
	}

	public BufferedImage getChip50() {
		return chip50;
	}

	public BufferedImage getChip100() {
		return chip100;
	}

	public BufferedImage getChip500() {
		return chip500;
	}

	public BufferedImage getZ�hlstrategie() {
		return z�hlstrategie;
	}


}
