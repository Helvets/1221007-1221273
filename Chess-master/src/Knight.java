import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Knight extends Piece{
	
	public Knight(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = true;
	}
	
	@Override
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		// TODO Auto-generated method stub
		if(isBlack)
		{
			ImageIcon knight = new ImageIcon("BKnight.png");
			g.drawImage(knight.getImage(), positionX, positionY, squareWidth, squareWidth, null);
			
		}
		else
		{
//			g.setColor(Color.white);
			ImageIcon knight = new ImageIcon("WKnight.png");
			g.drawImage(knight.getImage(), positionX, positionY, squareWidth, squareWidth, null);
		}
//		g.fillRect(positionX+(int)(squareWidth*4.75/10.0), 
//				positionY+squareWidth/7, 
//				(int)(squareWidth/3.5), (int)(squareWidth/1.5));
//		g.fillRect(positionX+(int)(squareWidth*3.5/10.0), 
//				positionY+(int)(squareWidth*3.0/5.0), 
//				squareWidth/2, squareWidth/5);
//		g.fillOval(positionX+(int)(squareWidth*2.5/10.0), positionY+squareWidth/7, 
//				squareWidth/2, squareWidth/4);
		
	}

	@Override
	public boolean canMove(int x, int y) {
		// TODO Auto-generated method stub
		return (Math.abs(x) == 2 && Math.abs(y)==1) || (Math.abs(x) == 1 && Math.abs(y)==2);
	}

	@Override
	public boolean canCapture(int x, int y) {
		// TODO Auto-generated method stub
		return (Math.abs(x) == 2 && Math.abs(y)==1) || (Math.abs(x) == 1 && Math.abs(y)==2);
	}
	public String toString() {
		if(isBlack) {
			return "black-knight";
		} else {
			return "white-knight";
		}
	}
}
