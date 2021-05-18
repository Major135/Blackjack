package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Texturen {

	public BufferedImage h10, h9, h8, h7, h6, h5, h4, h3, h2, ah, kh, bh, qh; // Herzkarten
	public BufferedImage k10, k9, k8, k7, k6, k5, k4, k3, k2, ak, bk, kk, qk;// Kreuzkarten
	public BufferedImage ka10, ka9, ka8, ka7, ka6, ka5, ka4, ka3, ka2, aka, bka, kak, qka;// Karokarten
	public BufferedImage p10, p9, p8, p7, p6, p5, p4, p3, p2, ap, bp, kp, qp;// Pikkarten
	private BufferedImage[] buffi = { h10, h9, h8, h7, h6, h5, h4, h3, h2, ah, kh, bh, qh, k10, k9, k8, k7, k6, k5, k4,
			k3, k2, ak, bk, kk, qk, ka10, ka9, ka8, ka7, ka6, ka5, ka4, ka3, ka2, aka, bka, kak, qka, p10, p9, p8, p7,
			p6, p5, p4, p3, p2, ap, bp, kp, qp };
	private char[] bez = { 'a', 'b', 'k', 'q' };
	private Blackjack blackjack;

	public Texturen(Blackjack blackjack) {
		this.blackjack = blackjack;
		holTexturen();
	}

	public void holTexturen() {
		System.out.println("Texturen holen");
		String speicher;
		URL pic_url = null;
		int zähler = 0;
		try {
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "h.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=9;
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "k.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=18;
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "ka.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=27;
			for (int i = 10; i > 1; i--) {
				speicher = "karten/" + i + "p.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=36;
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "h.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=40;
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "k.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=44;
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "ka.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				zähler++;
			}
			zähler=48;
			for (int i = 0; i < bez.length; i++) {
				speicher = "karten/" + bez[i] + "p.png";
				pic_url = this.getClass().getClassLoader().getResource(speicher);
				buffi[zähler] = ImageIO.read(pic_url);
				if (zähler < 52) {
					zähler++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BufferedImage[] getBuffi() {
		return buffi;
	}
	public void setBuffi(BufferedImage[] buffi) {
		this.buffi = buffi;
	}
}
