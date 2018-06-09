package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import control.Controller;
import control.Observed;
import control.Observer;
import pieces.Bispo;
import pieces.Cavalo;
import pieces.Dama;
import pieces.Piece;
import pieces.Torre;
import static window.GameWindow.*;

public class Promotion {
	
	JPanel board = (JPanel) Controller.getObserver();
	private Observed observed;
	public JPopupMenu popup;
	private Piece[][] pieces;
	private Observer obs;
	
	public Promotion(int i, int j, Color cor, Observer obs) {
		observed = Controller.getObserved();
		pieces = observed.getPieces();
		
	    popup = new JPopupMenu();
	    ActionListener menuListener = new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	    	if (event.getActionCommand()=="Dama") pieces[i][j]= new Dama(cor);
	    	else if (event.getActionCommand()=="Torre") pieces[i][j]= new Torre(cor);
	    	else if (event.getActionCommand()=="Bispo") pieces[i][j]= new Bispo(cor);
	    	else if (event.getActionCommand()=="Cavalo") pieces[i][j]= new Cavalo(cor);
	    	obs.notify(Chess.getChess());
	        System.out.println("Popup menu item [" + event.getActionCommand() + "] was selected.");
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
	
	public void popupShow(int i, int j) {
		popup.show(board, i*size, j*size);
	}
}
