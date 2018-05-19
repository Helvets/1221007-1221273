import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Cavalo extends Piece{
	private Image img_CavaloB, img_CavaloP;
	
	public Cavalo(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = true;
		try {
			img_CavaloB = ImageIO.read(new File("Pecas/Pecas_1/b_cavalo.gif"));
			img_CavaloP = ImageIO.read(new File("Pecas/Pecas_1/p_cavalo.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}


	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		// TODO Auto-generated method stub
		if(isBlack)
		{
			g.drawImage(img_CavaloP, positionX, positionY, squareWidth, squareWidth, null);
			
		}
		else
		{
			g.drawImage(img_CavaloB, positionX, positionY, squareWidth, squareWidth, null);
		}	
	}

	public boolean canMove(int x, int y) {
		return (Math.abs(x) == 2 && Math.abs(y)==1) || (Math.abs(x) == 1 && Math.abs(y)==2);
	}

	public boolean canCapture(int x, int y) {
		return (Math.abs(x) == 2 && Math.abs(y)==1) || (Math.abs(x) == 1 && Math.abs(y)==2);
	}
	public String toString() {
		if(isBlack) {
			return "cavalo-preto";
		} else {
			return "cavalo-branco";
		}
	}
}
