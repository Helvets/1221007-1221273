import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Queen extends Piece{
	
	public Queen(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = false;
	}
	
	@Override
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		// TODO Auto-generated method stub
		int[] xPointsLeft = {(positionX+(int)(squareWidth*1.0/4.0)), (positionX+(int)(squareWidth*1.0/4.0)),
				(positionX+(int)(squareWidth*1.75/4.0))}; 
		int[] yPointsLeft = {(positionY+(int)(squareWidth*3.0/5.0)), (positionY+(int)(squareWidth*1.0/5.0)),
				(positionY+(int)(squareWidth*3.0/5.0))};
		int[] xPointsRight = {(positionX+(int)(squareWidth*1.75/4.0)+squareWidth/6), ((positionX+(int)(squareWidth*1.0/4.0)+
				squareWidth/2)), ((positionX+(int)(squareWidth*1.0/4.0)+
						squareWidth/2))};
		int[] yPointsRight = {(positionY+(int)(squareWidth*3.0/5.0)), (positionY+(int)(squareWidth*3.0/5.0)),
				(positionY+(int)(squareWidth*1.0/5.0))};
		
		if(isBlack) {
			ImageIcon queen = new ImageIcon("BQueen.png");
			g.drawImage(queen.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		} else {
			ImageIcon queen = new ImageIcon("WQueen.png");
			g.drawImage(queen.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
//		g.fillRect(positionX+(int)(squareWidth*1.0/4.0), 
//				positionY+(int)(squareWidth*3.0/5.0), 
//				squareWidth/2, squareWidth/5);
//		g.fillPolygon(xPointsLeft, yPointsLeft, 3);
//		g.fillRect(positionX+(int)(squareWidth*1.75/4.0), (positionY+(int)(squareWidth*1.0/5.0)), squareWidth/6, squareWidth/2);
//		g.fillPolygon(xPointsRight, yPointsRight, 3);
//		
	}

	@Override
	public boolean canMove(int x, int y) {
		// TODO Auto-generated method stub
		return ((Math.abs(x)==Math.abs(y)) || x == 0 || y == 0) && !(x == 0 && y == 0); 
	}

	@Override
	public boolean canCapture(int x, int y) {
		// TODO Auto-generated method stub
		return ((Math.abs(x)==Math.abs(y)) || x == 0 || y == 0) && !(x == 0 && y == 0);
	}
	public String toString() {
		if(isBlack) {
			return "black-queen";
		} else {
			return "white-queen";
		}
	}

}
