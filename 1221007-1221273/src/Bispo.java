import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bispo extends Piece{
	private Image img_BispoB, img_BispoP;
	
	public Bispo(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = false;
		try {
			img_BispoB = ImageIO.read(new File("Pecas/Pecas_1/b_bispo.gif"));
			img_BispoP = ImageIO.read(new File("Pecas/Pecas_1/p_bispo.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		if(isBlack)
		{
			g.drawImage(img_BispoP, positionX, positionY, squareWidth, squareWidth, null);
		}
		else
		{
			g.drawImage(img_BispoB, positionX, positionY, squareWidth, squareWidth, null);
		}	
	}

	public boolean canMove(int x, int y) {
		return (Math.abs(x) == Math.abs(y)) && !(x==0 && y ==0);
	}

	public boolean canCapture(int x, int y) {
		return (Math.abs(x) == Math.abs(y)) && !(x==0 && y ==0);
	}
	public String toString() {
		if(isBlack) {
			return "bispo-preto";
		} else {
			return "bispo-branco";
		}
	}

}
