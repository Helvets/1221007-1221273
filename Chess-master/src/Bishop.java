import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bishop extends Piece{

	public Bishop(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = false;
	}
	
	@Override
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		// TODO Auto-generated method stub
		int[] xPoints = {(positionX+(int)(squareWidth*1.0/4.0)), (positionX+(int)(squareWidth*2.0/4.0)),
				(positionX+(int)(squareWidth*3.0/4.0))};
		int[] yPoints = {(positionY+(int)(squareWidth*3.0/5.0)), (positionY+squareWidth/7), 
				(positionY+(int)(squareWidth*3.0/5.0))};
		if(isBlack)
		{
			ImageIcon bishop = new ImageIcon("BBishop.png");
			g.drawImage(bishop.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
		else
		{
			ImageIcon bishop = new ImageIcon("WBishop.png");
			g.drawImage(bishop.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
//		g.fillRect(positionX+(int)(squareWidth*1.0/4.0), 
//				positionY+(int)(squareWidth*3.0/5.0), 
//				squareWidth/2, squareWidth/5);
//		g.fillPolygon(xPoints, yPoints, 3);
		
	}

	@Override
	public boolean canMove(int x, int y) {
		// TODO Auto-generated method stub
		return (Math.abs(x) == Math.abs(y)) && !(x==0 && y ==0);
	}

	@Override
	public boolean canCapture(int x, int y) {
		// TODO Auto-generated method stub
		return (Math.abs(x) == Math.abs(y)) && !(x==0 && y ==0);
	}
	public String toString() {
		if(isBlack) {
			return "black-bishop";
		} else {
			return "white-bishop";
		}
	}

}
