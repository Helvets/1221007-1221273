import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class King extends Piece {
	public King(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = true;
	}
	
	@Override
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		// TODO Auto-generated method stub
		if (isBlack) {
			ImageIcon king = new ImageIcon("BKing.png");
			g.drawImage(king.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		} else {
			ImageIcon king = new ImageIcon("WKing.png");
			g.drawImage(king.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
		
		
//		g.fillRect(positionX+(int)(squareWidth*4.0/10.0), 
//				positionY+(int)(squareWidth/6.5), 
//				squareWidth/5, (int)(squareWidth/2.0));
//		g.fillRect(positionX+(int)(squareWidth*3.0/10.0), positionY+(int)(squareWidth*1.0/5.0), 
//				(int)(squareWidth/2.35), (int)(squareWidth/5.5));
//		g.fillOval(positionX+(int)(squareWidth*1.0/4.0), 
//				positionY+(int)(squareWidth*2.0/5.0), 
//				squareWidth/2, squareWidth/2);
		
	}

	@Override
	public boolean canMove(int x, int y) {
		// TODO Auto-generated method stub
		return ((x>=-1 && x<=1 && y>=-1 && y<=1) && !(x == 0 && y == 0));
	}

	@Override
	public boolean canCapture(int x, int y) {
		// TODO Auto-generated method stub
		return ((x>=-1 && x<=1 && y>=-1 && y<=1) && !(x == 0 && y == 0));
	}
	public String toString() {
		if(isBlack) {
			return "black-king";
		} else {
			return "white-king";
		}
	}
}
