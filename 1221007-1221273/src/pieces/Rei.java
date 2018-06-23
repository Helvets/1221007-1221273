package pieces;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rei extends Piece {
	public Rei(Color cor) {
		this.cor = cor;
		this.canJump = true;
		this.isHighlighted = false;
		this.isSelected = false;
		this.isFirstMove=true;
		try {
			if (this.cor == Color.white) img_branco = ImageIO.read(new File("Pecas/Pecas_2/CyanK.png"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_2/PurpleK.png"));

			
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
		if(this.cor == Color.black) {
			return "rei-preto";
		} else {
			return "rei-branco";
		}
	}
}
