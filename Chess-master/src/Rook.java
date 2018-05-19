import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Rook extends Piece{
	
	public Rook(boolean isBlack) {
		this.isBlack = isBlack;
		canJump = false;
	}

	@Override
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		// TODO Auto-generated method stub

		if(isBlack)
		{
			ImageIcon rook = new ImageIcon("BRook.png");
			g.drawImage(rook.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
		else
		{
			ImageIcon rook = new ImageIcon("WRook.png");
			g.drawImage(rook.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
//		g.fillRect(positionX+(int)(squareWidth*3.0/10.0), 
//				positionY+squareWidth/7, 
//				(int)(squareWidth/2.5), (int)(squareWidth/1.5));
//		g.fillRect(positionX+(int)(squareWidth*1.0/4.0), 
//				positionY+(int)(squareWidth*3.0/5.0), 
//				squareWidth/2, squareWidth/5);
		
	}

	@Override
	public boolean canMove(int x, int y) {
		// TODO Auto-generated method stub
		return (x==0 || y==0) && !(x==0 && y==0);
	}

	@Override
	public boolean canCapture(int x, int y) {
		// TODO Auto-generated method stub
		return (x==0 || y==0) && !(x==0 && y==0);
	}
	public String toString() {
		if(isBlack) {
			return "black-rook";
		} else {
			return "white-rook";
		}
	}
	
	
}


