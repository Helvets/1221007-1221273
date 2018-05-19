import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Torre extends Piece{
	private Image img_TorreB, img_TorreP;
	public Torre(boolean isBlack) {
		this.isBlack = isBlack;
		canJump = false;
		try {
			img_TorreB = ImageIO.read(new File("Pecas/Pecas_1/b_torre.gif"));
			img_TorreP = ImageIO.read(new File("Pecas/Pecas_1/p_torre.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		if(isBlack)
		{
			g.drawImage(img_TorreP, positionX, positionY, squareWidth, squareWidth, null);
		}
		else
		{
			g.drawImage(img_TorreB, positionX, positionY, squareWidth, squareWidth, null);
		}	
	}

	public boolean canMove(int x, int y) {
		return (x==0 || y==0) && !(x==0 && y==0);
	}

	public boolean canCapture(int x, int y) {
		return (x==0 || y==0) && !(x==0 && y==0);
	}
	public String toString() {
		if(isBlack) {
			return "torre-preta";
		} else {
			return "torre-branca";
		}
	}
	
	
}


