import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Cavalo extends Piece{

	public Cavalo(Color cor) {
		this.cor = cor;
		this.canJump = true;
		this.isHighlighted = false;
		this.isSelected = false;
		this.isFirstTurn = false;
		try {
			if (this.cor == Color.white)img_branco = ImageIO.read(new File("Pecas/Pecas_1/b_cavalo.gif"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_1/p_cavalo.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public boolean canMove(int x, int y) {
		return (Math.abs(x) == 2 && Math.abs(y)==1) || (Math.abs(x) == 1 && Math.abs(y)==2);
	}

	public boolean canCapture(int x, int y) {
		return (Math.abs(x) == 2 && Math.abs(y)==1) || (Math.abs(x) == 1 && Math.abs(y)==2);
	}
	public String toString() {
		if(this.cor == Color.black) {
			return "cavalo-preto";
		} else {
			return "cavalo-branco";
		}
	}
}
