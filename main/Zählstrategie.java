package main;

public class Z�hlstrategie {

	private Hand hand;
	private int z�hler;

	public Z�hlstrategie(Hand hand) {
		this.hand = hand;
		System.out.println("Z�hlstrategie erstellt");

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
				z�hler-=1;
				hand.setAssWert(false);
				System.out.println("ASSWERT (-1)" +z�hler);
			}
		}
		return z�hler;
	}

	public int getZ�hler() {
		return z�hler;
	}

}
