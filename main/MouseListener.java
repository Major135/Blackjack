package main;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
	private Blackjack blackjack;

	public MouseListener(Blackjack blackjack) {
		this.blackjack = blackjack;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
//		System.out.println("X "+x+"Y "+y);
		blackjack.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {


	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
