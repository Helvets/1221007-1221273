import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Torre extends Piece{
	
	public Torre(Color cor) {
		this.cor = cor;
		this.canJump = false;
		this.isHighlighted = false;
		this.isSelected = false;
		try {
			if (this.cor == Color.white) img_branco = ImageIO.read(new File("Pecas/Pecas_1/b_torre.gif"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_1/p_torre.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}


	public boolean canMove(int x, int y) {
		return (x==0 || y==0) && !(x==0 && y==0);
	}

	public boolean canCapture(int x, int y) {
		return (x==0 || y==0) && !(x==0 && y==0);
	}
	public String toString() {
		if(this.cor == Color.black) {
			return "torre-preta";
		} else {
			return "torre-branca";
		}
	}
	
	
}


