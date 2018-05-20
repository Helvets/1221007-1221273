import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Rei extends Piece {
	public Rei(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = true;
		this.isHighlighted = false;
		this.isSelected = false;
		try {
			if (!isBlack) img_branco = ImageIO.read(new File("Pecas/Pecas_1/b_rei.gif"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_1/p_rei.gif"));

			
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
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
