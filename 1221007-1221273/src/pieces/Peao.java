package pieces;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Peao extends Piece{
	
	public Peao(Color cor) {
		this.cor = cor;
		this.canJump = false;
		this.isHighlighted = false;
		this.isSelected= false;
		this.isFirstMove = true;
		
		try {
			if (this.cor == Color.white)img_branco = ImageIO.read(new File("Pecas/Pecas_1/b_peao.gif"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_1/p_peao.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public boolean canMove(int x, int y) {
		if (isFirstMove) {
			if (this.cor == Color.white) {
				if ((y == 1 && x == 0) || (y == 2 && x == 0)) {
					return true;
				}
				 
				
			} else {
				if((y == -1 && x == 0) || (y == -2 && x == 0)) {
					return true;
				}
			}
		} else {
			if (this.cor == Color.white) {
				return y == 1 && x == 0;
			} else {
				return y == -1 && x == 0;
			}
		}
		return false;
		
	}
	
	public boolean canCapture(int x, int y) {
		if(this.cor == Color.white)
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
		if(this.cor == Color.black) {
			return "peao-preto";
		} else {
			return "peao-branco";
		}
	}

}
