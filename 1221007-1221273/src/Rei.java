import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Rei extends Piece {
	private Image img_PeaoB, img_PeaoP;
	public Rei(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = true;
		try {
			img_PeaoB = ImageIO.read(new File("Pecas/Pecas_1/b_rei.gif"));
			img_PeaoP = ImageIO.read(new File("Pecas/Pecas_1/p_rei.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		if (isBlack) {
			g.drawImage(img_PeaoB, positionX, positionY, squareWidth, squareWidth, null);
		} else {
			g.drawImage(img_PeaoP, positionX, positionY, squareWidth, squareWidth, null);
		}
				
	}

	public boolean canMove(int x, int y) {
		return ((x>=-1 && x<=1 && y>=-1 && y<=1) && !(x == 0 && y == 0));
	}


	public boolean canCapture(int x, int y) {
		return ((x>=-1 && x<=1 && y>=-1 && y<=1) && !(x == 0 && y == 0));
	}
	public String toString() {
		if(isBlack) {
			return "rei-preto";
		} else {
			return "rei-branco";
		}
	}
}
