import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Peao extends Piece{
	public boolean isFirstTurn;
	
	private Image img_PeaoB, img_PeaoP;
	
	public Peao(boolean isBlack)
	{
		this.isBlack = isBlack;
		canJump = false;
		this.isFirstTurn = true;
		try {
			img_PeaoB = ImageIO.read(new File("Pecas/Pecas_1/b_peao.gif"));
			img_PeaoP = ImageIO.read(new File("Pecas/Pecas_1/p_peao.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}


	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		if(isBlack)
		{
			g.drawImage(img_PeaoB, positionX, positionY, squareWidth, squareWidth, null);
			
		}
		else
		{
			g.drawImage(img_PeaoB, positionX, positionY, squareWidth, squareWidth, null);
		}	
	}

	public boolean canMove(int x, int y) {
		if (isFirstTurn) {
			if (isBlack) {
				if ((y == 1 && x == 0) || (y == 2 && x == 0)) {
					return true;
				}
				 
				
			} else {
				if((y == -1 && x == 0) || (y == -2 && x == 0)) {
					return true;
				}
			}
		} else {
			if (isBlack) {
				return y == 1 && x == 0;
			} else {
				return y == -1 && x == 0;
			}
		}
		return false;
		
	}
	
	public boolean canCapture(int x, int y) {
		if(isBlack)
		{
			if((x == -1 || x == 1) && y == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if((x == -1 || x == 1) && y == -1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	public String toString() {
		if(isBlack) {
			return "peao-preto";
		} else {
			return "peao-branco";
		}
	}

}
