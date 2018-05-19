import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Dama extends Piece{
	private Image img_DamaB, img_DamaP;
	
	public Dama(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = false;
		try {
			img_DamaB = ImageIO.read(new File("Pecas/Pecas_1/b_dama.gif"));
			img_DamaP = ImageIO.read(new File("Pecas/Pecas_1/p_dama.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		if(isBlack) {
			g.drawImage(img_DamaP, positionX, positionY, squareWidth, squareWidth, null);
		} else {
			g.drawImage(img_DamaB, positionX, positionY, squareWidth, squareWidth, null);
		}	
	}
	public boolean canMove(int x, int y) {
		return ((Math.abs(x)==Math.abs(y)) || x == 0 || y == 0) && !(x == 0 && y == 0); 
	}

	public boolean canCapture(int x, int y) {
		return ((Math.abs(x)==Math.abs(y)) || x == 0 || y == 0) && !(x == 0 && y == 0);
	}
	public String toString() {
		if(isBlack) {
			return "dama-preta";
		} else {
			return "dama-branca";
		}
	}

}
