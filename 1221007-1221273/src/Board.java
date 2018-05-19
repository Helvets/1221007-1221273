import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;

public class Board extends JPanel {
	public static final int size = 50;
	Piece pieces[][] = new Piece[8][8];

	public void paintComponent(Graphics g) {
		super.paintComponent(g); //oq é isso?
		Graphics2D g2d = (Graphics2D) g;
		
		//Desenha retangulos
		for(int i = 0; i < 8; i++) {
			
			for(int j =0; j < 8; j++) {
				Rectangle2D tile = new Rectangle2D.Double(i*size, j*size, size, size);
				
				if ((i+j) % 2 == 0)
				{
					g2d.setPaint(Color.WHITE);
				}
				else
				{
					g2d.setPaint(Color.BLACK);
				}
				
				g2d.fill(tile);	
				//Desenha pecas
				if(pieces[i][j] != null)
				{
					pieces[i][j].drawYourself(g, i*size, 
							j*size, size);
					
				}
			}
			
		}
	}
		
		public void iniChessBoard()
		{
			for(int i = 0; i<8; i++)
			{
				for(int j = 0; j<8; j++)
				{
					if(j == 1)
					{
						pieces[i][j] = new Peao(true);
					}
					else if(j == 6)
					{
						pieces[i][j] = new Peao(false);
					}
					else
					{
						pieces[i][j] = null;
					}
				}
			}
			pieces[4][0] = new Rei(true);
			pieces[4][7] = new Rei(false);
			pieces[0][0] = new Torre(true);
			pieces[7][0] = new Torre(true);
			pieces[0][7] = new Torre(false);
			pieces[7][7] = new Torre(false);
			pieces[3][0] = new Dama(true);
			pieces[3][7] = new Dama(false);
			pieces[2][0] = new Bispo(true);
			pieces[5][0] = new Bispo(true);
			pieces[2][7] = new Bispo(false);
			pieces[5][7] = new Bispo(false);
			pieces[1][0] = new Cavalo(true);
			pieces[6][0] = new Cavalo(true);
			pieces[1][7] = new Cavalo(false);
			pieces[6][7] = new Cavalo(false);
		}
}
