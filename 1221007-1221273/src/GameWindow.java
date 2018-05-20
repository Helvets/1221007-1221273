import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameWindow extends JFrame implements MouseListener{
	public final int LARG_PADRAO = 400*2;
	public final int ALT_PADRAO = 300*2;
	public static final int size = 50;
//	public static final int BOARD_MARGIN = 50;
	int selectedSquareX = -1;
	int selectedSquareY = -1;
//	public static final int size = 50;
//	public static final int BOARD_MARGIN = 20;
	
	Chess xadrez = new Chess();
	
	public GameWindow () {
		xadrez.setLayout(null);
		xadrez.setBackground(Color.GREEN);
		//xadrez.iniChessBoard();
		add(xadrez);

		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_PADRAO/2;
		int y= sa/2 - ALT_PADRAO/2;
		setBounds(x,y,LARG_PADRAO, ALT_PADRAO);
		//setSize(size*8+BOARD_MARGIN*2, size*8+BOARD_MARGIN*3);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(this);
		}
	
	public void mouseClicked(MouseEvent e) {

		selectedSquareX = (e.getX() - 8) / size;
		selectedSquareY = (e.getY() - 30) / size;
		System.out.printf("%d ", selectedSquareX);
		System.out.printf("%d\n", selectedSquareY);
		if (selectedSquareX >= 0 && selectedSquareY >= 0 && selectedSquareX < 8 && selectedSquareY < 8) {
			if (!xadrez.selected.someoneIsSelected) {
				xadrez.pieces[selectedSquareX][selectedSquareY].isSelected = true;
				xadrez.moveList(selectedSquareX, selectedSquareY);
				xadrez.selected.i = selectedSquareX;
				xadrez.selected.j = selectedSquareY;
				xadrez.selected.someoneIsSelected = true;
			} else if (xadrez.pieces[selectedSquareX][selectedSquareY].isHighlighted) {
				xadrez.move(selectedSquareX, selectedSquareY);
				xadrez.ClearSelecction();
				xadrez.selected.someoneIsSelected = false;
			} else if ((xadrez.isBlackTurn && xadrez.pieces[selectedSquareX][selectedSquareY].cor == Color.black)
					|| (!xadrez.isBlackTurn && xadrez.pieces[selectedSquareX][selectedSquareY].cor == Color.white)) {
				xadrez.ClearSelecction();
				xadrez.pieces[selectedSquareX][selectedSquareY].isSelected = true;
				xadrez.moveList(selectedSquareX, selectedSquareY);
				xadrez.selected.i = selectedSquareX;
				xadrez.selected.j = selectedSquareY;
				xadrez.selected.someoneIsSelected = true;
			}
			xadrez.repaint();
		}

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent arg0) {
		
	}

}
