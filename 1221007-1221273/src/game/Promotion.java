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
	private static Promotion promotion = null;
	JPanel board = (JPanel) Controller.getObserver();
	private Observed observed;
	public JPopupMenu popup;
	private Piece[][] pieces;
	protected static boolean flag;//caso o player nao selecione do popup menu a promocao
	protected int x;//caso o player nao selecione do popup menu a promocao
	protected int y;//caso o player nao selecione do popup menu a promocao
	
	public Promotion () {
		flag=false;
	}
	public Promotion(int i, int j, Color cor, Observer obs) {
		flag=true;
		x=i;
		y=j;
		observed = Controller.getObserved();  // pega o panel board pelo controlador
		pieces = observed.getPieces();
	    popup = new JPopupMenu();
	    ActionListener menuListener = new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	    	if (event.getActionCommand()=="Dama") pieces[i][j]= new Dama(cor);
	    	else if (event.getActionCommand()=="Torre") pieces[i][j]= new Torre(cor);
	    	else if (event.getActionCommand()=="Bispo") pieces[i][j]= new Bispo(cor);
	    	else if (event.getActionCommand()=="Cavalo") pieces[i][j]= new Cavalo(cor);
	    	flag=false;//o player selecionou do popup menu
	    	obs.notify(Chess.getChess());
	        System.out.println("Popup menu item [" + event.getActionCommand() + "] was selected.");
	      }
	    }
	    ;
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
	
	public void popupShow() {
		popup.show(board, x*size, y*size); //mostra no panel exatamente na casa da promocao
	}
	
}
