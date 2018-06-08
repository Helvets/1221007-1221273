package pieces;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bispo extends Piece{
	
	public Bispo(Color cor) {
		this.cor = cor;
		this.canJump = false;
		this.isHighlighted = false;
		this.isSelected = false;
		
		//Tratamento de Exce��o
		try {
			if (this.cor == Color.white)img_branco = ImageIO.read(new File("Pecas/Pecas_1/b_bispo.gif"));
			else img_preto = ImageIO.read(new File("Pecas/Pecas_1/p_bispo.gif"));

		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public boolean canMove(int x, int y) {
		return (Math.abs(x) == Math.abs(y)) && !(x==0 && y ==0); //Retorna se o movimento � valido ou n�o
	}

	public boolean canCapture(int x, int y) {
		return (Math.abs(x) == Math.abs(y)) && !(x==0 && y ==0); //Retorna se uma pe�a pode ser capturada ou n�o
	}
	public String toString() { //Identifica a pe�a
		if(this.cor == Color.black) {
			return "bispo-preto";
		} else {
			return "bispo-branco";
		}
	}

}
