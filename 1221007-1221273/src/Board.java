import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;

public class Board extends JPanel {
	public static final int size = 50;
	Piece pieces[][] = new Piece[8][8];
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//Desenha retangulos
		for(int i = 0; i < 8; i++) {
			
			for(int j =0; j < 8; j++) {
				Rectangle2D tile = new Rectangle2D.Double(i*size, j*size, size, size);
				
				if ((i+j) % 2 == 0) //Caso o quadrado for par, pinta-se de branco
				{
					g2d.setPaint(Color.WHITE);
				}
				else //Caso for impar, pinta-se de preto
				{
					g2d.setPaint(Color.BLACK);
				}
				
				g2d.fill(tile);	
				
				//Desenha pecas
				if(pieces[i][j].toString() != "vazio" || pieces[i][j].isHighlighted )
				{
					pieces[i][j].drawYourself(g, i*size, j*size, size);
					
				}
				if(pieces[i][j].isHighlighted)
				{
					pieces[i][j].drawYourself(g, i*size, j*size, size);
					
				}
			}
			
		}
	}
}
