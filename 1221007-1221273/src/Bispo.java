import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bispo extends Piece{
	
	public Bispo(boolean isBlack) {
		this.isBlack = isBlack;
		this.canJump = false;
		this.isHighlighted = false;
		this.isSelected = false;
		try {
			if (!isBlack)img_branco = ImageIO.read(new File("Pecas/Pecas_1/b_bispo.gif"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_1/p_bispo.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
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
