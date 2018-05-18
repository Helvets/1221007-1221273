import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;


public class Board extends JPanel {
	private Image i;
	
	private Image img_ReiB, img_ReiP;
	private Image img_DamaB, img_DamaP;
	private Image img_CavaloB, img_CavaloP;
	private Image img_PeaoB, img_PeaoP;
	private Image img_TorreB, img_TorreP;
	private Image img_BispoB, img_BispoP;
	
	public Board () {
		try {
				i = ImageIO.read(new File("Pecas/Pecas_1/b_rei.gif"));
				img_ReiB = ImageIO.read(new File("Pecas/Pecas_1/b_rei.gif"));
				img_DamaB = ImageIO.read(new File("Pecas/Pecas_1/b_dama.gif"));
				img_CavaloB = ImageIO.read(new File("Pecas/Pecas_1/b_cavalo.gif"));
				img_PeaoB = ImageIO.read(new File("Pecas/Pecas_1/b_peao.gif"));
				img_BispoB = ImageIO.read(new File("Pecas/Pecas_1/b_bispo.gif"));
				img_TorreB = ImageIO.read(new File("Pecas/Pecas_1/b_torre.gif"));
				
				img_ReiP = ImageIO.read(new File("Pecas/Pecas_1/p_rei.gif"));
				img_DamaP = ImageIO.read(new File("Pecas/Pecas_1/p_dama.gif"));
				img_CavaloP = ImageIO.read(new File("Pecas/Pecas_1/p_cavalo.gif"));
				img_PeaoP = ImageIO.read(new File("Pecas/Pecas_1/p_peao.gif"));
				img_BispoP = ImageIO.read(new File("Pecas/Pecas_1/p_bispo.gif"));
				img_TorreP = ImageIO.read(new File("Pecas/Pecas_1/p_torre.gif"));
			}
		
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //oq é isso?
		Graphics2D g2d = (Graphics2D) g;
		
		double size = 50;
		
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
				
			}
			
		}
		

		

		//Desenha peças	
		
		//========== Ideia =============
		/* Inicializa um vetor de Pecas com todas as peças já organizadas
		 * Cria-se um for que vai passando pelo vetor
		 * Desenha cada peça em sua devida localização
		 * 
		 * 
		 * String Nome_pecas[] = {"img_TorreP",};
		
		
		for(int i = 0; i< 8; i++)
		{
			
		}
		
		g.drawImage(img_TorreP, 10, 10,null);
		g.drawImage(img_CavaloP, 10 * 50, 10, null);
		g.drawImage(img_BispoP, 10* 100, 10, null);
		
		
		
		
		g.drawImage(img_CavaloP, (int)size, img_CavaloP.getHeight(null) - 25,null);
		g.drawImage(i,i.getWidth(null) -25, i.getHeight(null) - 25,null);*/
		
		
	

	}
	
}
