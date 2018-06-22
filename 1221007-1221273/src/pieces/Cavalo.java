package pieces;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cavalo extends Piece{

	public Cavalo(Color cor) {
		this.cor = cor;
		this.canJump = true;
		this.isHighlighted = false;
		this.isSelected = false;

		try {
			if (this.cor == Color.white)img_branco = ImageIO.read(new File("Pecas/Pecas_2/CyanN.png"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_2/PurpleN.png"));

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
