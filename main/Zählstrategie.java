package main;

public class Zählstrategie {

	private Hand hand;
	private int zähler;

	public Zählstrategie(Hand hand) {
		this.hand = hand;
		System.out.println("Zählstrategie erstellt");

	}

	public int gibKartenWerte() {
		zähler = 0;
		for (int i = 0; i < hand.getHand().size(); i++) {
			if (hand.getHand().get(i).getWert() < 7 && !hand.isAssWert()) {
				zähler += 1;
				System.out.println("ZählStrategie KLEINER 7 (+1) " + zähler);
			}
			if (hand.getHand().get(i).getWert() > 9) {
				zähler -= 1;
				System.out.println("ZählStrategie Größer 9 (-1) " + zähler);
			}
			if (hand.isAssWert()) {
				zähler-=1;
				hand.setAssWert(false);
				System.out.println("ASSWERT (-1)" +zähler);
			}
		}
		return zähler;
	}

	public int getZähler() {
		return zähler;
	}

}
