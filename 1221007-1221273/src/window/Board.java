package window;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import control.*;
import pieces.*;
import static window.GameWindow.*;
import window.GameWindow.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import java.awt.event.ActionEvent;


public class Board extends JPanel implements Observer{

	private Piece[][] pieces;
	private Observed observed;
	public JPopupMenu popup;
	
	public Board() {
		Controller.getController().register(this);
		observed = Controller.getObserved();
		pieces = observed.getPieces();
	}
	
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

	public void Promotion(int i, int j, Color cor) {
	    popup = new JPopupMenu();
	    ActionListener menuListener = new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	    	if (event.getActionCommand()=="Dama") {
	    		pieces[i][j]= new Dama(cor);
	    	}
	    	if (event.getActionCommand()=="Torre") {
	    		pieces[i][j]= new Torre(cor);
	    	}
	    	if (event.getActionCommand()=="Bispo") {
	    		pieces[i][j]= new Bispo(cor);
	    	}
	    	if (event.getActionCommand()=="Cavalo") {
	    		pieces[i][j]= new Cavalo(cor);
	    	}
	        System.out.println("Popup menu item [" + event.getActionCommand() + "] was selected.");
	        repaint();
	      }
	    };
	    JMenuItem item;
	    if (cor==Color.black) popup.add(item = new JMenuItem("Dama", new ImageIcon("Pecas/Pecas_1/p_dama.gif")));
	    else popup.add(item = new JMenuItem("Dama", new ImageIcon("Pecas/Pecas_1/b_dama.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(menuListener);
	    if (cor==Color.black) popup.add(item = new JMenuItem("Torre", new ImageIcon("Pecas/Pecas_1/p_torre.gif")));
	    else popup.add(item = new JMenuItem("Torre", new ImageIcon("Pecas/Pecas_1/b_torre.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(menuListener);
	    if (cor==Color.black) popup.add(item = new JMenuItem("Cavalo", new ImageIcon("Pecas/Pecas_1/p_Cavalo.gif")));
	    else popup.add(item = new JMenuItem("Cavalo", new ImageIcon("Pecas/Pecas_1/b_Cavalo.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(menuListener);
	    if (cor==Color.black) popup.add(item = new JMenuItem("Bispo", new ImageIcon("Pecas/Pecas_1/p_Bispo.gif")));
	    else popup.add(item = new JMenuItem("Bispo", new ImageIcon("Pecas/Pecas_1/b_Bispo.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(menuListener);
	    popup.setLabel("Justification");
	    popup.setBorder(new BevelBorder(BevelBorder.RAISED));
	    
	}
	public void notify(Observed o) {
		repaint();
	}
	public void notifyPromotion(Observed o, int i, int j, Color cor) {
		Promotion(i, j, cor);
		int x =i*size;
		int y =j*size;
		popup.show(Board.this, x, y);
	}
	
}
