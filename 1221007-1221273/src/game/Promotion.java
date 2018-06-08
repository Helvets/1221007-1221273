package game;

import static window.GameWindow.size;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import pieces.Bispo;
import pieces.Cavalo;
import pieces.Dama;
import pieces.Piece;
import pieces.Torre;


public class Promotion extends JPanel{
	public JPopupMenu popup;
	public Chess chess = Chess.getChess();
	public Piece[][] pieces = chess.getPieces();
	JPanel board=(JPanel) this.getParent();
	
	
	public Promotion(int i, int j, Color cor){
		
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
	
	public void mostrar(int i, int j) {
		int x =i*size;
		int y =j*size;
		popup.show(board, x,y);
	}

}
